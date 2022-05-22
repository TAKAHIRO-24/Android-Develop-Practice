package com.websarva.wings.android.asyncsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import androidx.core.os.HandlerCompat
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    //クラス内のprivate定数を宣言するためにcompanion objectブロックとする。
    companion object {
        //ログに記載するタグ用の文字列。
        private const val DEBUG_TAG = "AsyncSample"
        //お天気情報のURL。
        private const val WEATHERINFO_URL = "https://api.openweathermap.org/data/2.5/weather?lang=ja"
        //お天気APIにアクセスするためのAPIキー。
        private const val APP_ID = "d583bf57a715d35a864ad92c44623b30"
    }
    //リストビューに表示させるリストデータ
    private var _list: MutableList<MutableMap<String, String>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ListViewに都道府県リストとリスナを設定
        _list = createList()
        val lvCityList = findViewById<ListView>(R.id.lvCityList)
        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)
        val adapter = SimpleAdapter(this@MainActivity, _list, android.R.layout.simple_list_item_1, from, to)
        lvCityList.adapter = adapter
        lvCityList.onItemClickListener = ListItemClickListener()
    }

    //お天気情報の取得処理を行うメソッド。
    @UiThread //UIスレッドで実行されることがコンパイラによって保証
    private fun receiveWeatherInfo(urlFull: String) {
        //元スレッドでHandlerオブジェクトを生成。
        val handler = HandlerCompat.createAsync(mainLooper)
        //非同期で天気情報を取得するインスタンス生成。
        val backgroudReceiver = WeatherInfoBackgroudReceiver(handler, urlFull)
        //マルチスレッド処理
        val excuteService = Executors.newSingleThreadExecutor()
        //マルチスレッド処理（別スレッドでの処理）実行
        excuteService.submit(backgroudReceiver)
    }

    //非同期でお天気情報APIにアクセスするためのクラス。
    private inner class WeatherInfoBackgroudReceiver(handler: Handler, url: String) : Runnable {
        //ハンドラオブジェクト。
        private val _handler = handler
        //お天気情報を取得するURL。
        private val _url = url

        @WorkerThread //Workerスレッドで実行されることがコンパイラによって保証
        override fun run() {
            //ここにWeb APIにアクセスするコードを記述
            //天気情報サービスから取得したJSON文字列。天気情報が格納されている。
            var result = ""
            //URLオブジェクト生成。
            val url = URL(_url)
            //URLオブジェクトからHttpURLConnectionオブジェクトを取得。
            val con = url.openConnection() as? HttpURLConnection
            //conがnullではない場合
            con?.let{
                try {
                    //接続に使ってもより時間を設定。
                    it.connectTimeout = 1000
                    //データ取得に使ってもよい時間。
                    it.readTimeout = 1000
                    //HTTP接続メソッドをGETに設定。
                    it.requestMethod = "GET"
                    //接続。
                    it.connect()

//                    //ステータスコード取得
//                    val resCode = it.responseCode
//                    val resMsg = it.responseMessage

                    //HttpURLConnectionオブジェクトからレスポンスデータを取得。
                    val stream = it.inputStream
                    //レスポンスデータであるInputStreamを文字列に変換。
                    result = is2String(stream)
                    //InputStreamオブジェクトを解放。
                    stream.close()
                }
                catch (ex: SocketTimeoutException) {
                    Log.w(DEBUG_TAG, "通信タイムアウト", ex)
                }
                //HttpURLConnectionオブジェクトを解放。
                it.disconnect()
            }

            //非同期処理完了後の処理
            val postExecutor = WeatherInfoPostExecutor(result)
            //Handlerオブジェクトを生成した元スレッドで処理を行う。
            _handler.post(postExecutor)
        }

        private fun is2String(stream: InputStream): String {
            val sb = StringBuilder()
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var line = reader.readLine()
            while (line != null) {
                sb.append(line)
                line = reader.readLine()
            }
            reader.close()
            return sb.toString()

//            //この一行でOK?
//            val dummy = stream.bufferedReader().use { it.readText() }
        }
    }

    //非同期でお天気情報を取得した後にUIスレッドでその情報を表示するためのクラス。
    private inner class WeatherInfoPostExecutor(result: String) : Runnable {
        //取得したお天気情報JSON文字列。
        private val _result = result

        @UiThread //UIスレッドで実行されることがコンパイラによって保証
        override fun run() {
            //ここにUIスレッドで行う処理コードを記述。

//レスポンスJSONデータは以下の通り。
//            {
//                "coord": {
//                "lon": -122.08,
//                "lat": 37.39
//            },
//                "weather": [
//                {
//                    "id": 800,
//                    "main": "Clear",
//                    "description": "clear sky",
//                    "icon": "01d"
//                }
//                ],
//                "base": "stations",
//                "main": {
//                "temp": 282.55,
//                "feels_like": 281.86,
//                "temp_min": 280.37,
//                "temp_max": 284.26,
//                "pressure": 1023,
//                "humidity": 100
//            },
//                "visibility": 10000,
//                "wind": {
//                "speed": 1.5,
//                "deg": 350
//            },
//                "clouds": {
//                "all": 1
//            },
//                "dt": 1560350645,
//                "sys": {
//                "type": 1,
//                "id": 5122,
//                "message": 0.0139,
//                "country": "US",
//                "sunrise": 1560343627,
//                "sunset": 1560396563
//            },
//                "timezone": -25200,
//                "id": 420006353,
//                "name": "Mountain View",
//                "cod": 200
//            }

            //ルートJSONオブジェクトを生成。
            val rootJSON = JSONObject(_result)
            //都市名文字列を取得。
            val cityName = rootJSON.getString("name")
            //緯度経度情報JSONオブジェクトを取得。
            val coordJSON = rootJSON.getJSONObject("coord")
            //緯度情報文字列を取得。
            val latitude = coordJSON.getString("lat")
            //経度情報文字列を取得。
            val longitude = coordJSON.getString("lon")
            //天気情報JSON配列オブジェクトを取得。
            val weatherJSONArray = rootJSON.getJSONArray("weather")
            //現在の天気情報JSONオブジェクトを取得。
            val weatherJSON = weatherJSONArray.getJSONObject(0)
            //現在の天気情報文字列を取得。
            val weather = weatherJSON.getString("description")
            //画面に表示する「〇〇の天気」文字列を生成。
            val telop = "${cityName}の天気"
            //天気の詳細情報を表示する文字列を生成。
            val desc = "現在は${weather}です。\n緯度は${latitude}度で経度は${longitude}度です。"
            //天気情報を表示するTextViewを取得。
            val tvWeatherTelop = findViewById<TextView>(R.id.tvWeatherTelop)
            val tvWeatherDesc = findViewById<TextView>(R.id.tvWeatherDesc)
            //天気情報を表示。
            tvWeatherTelop.text = telop
            tvWeatherDesc.text = desc
        }
    }

    //リストがタップされたときの処理が記述されたリスナクラス。
    private inner class ListItemClickListener: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = _list.get(position)
            val q = item.get("q")
            q?.let {
                val urlFull = "${WEATHERINFO_URL}&q=${q}&appid=${APP_ID}"
                receiveWeatherInfo(urlFull)
            }
        }
    }

    //リストビューに表示させる天気ポイントリストデータを生成するメソッド。
    private fun createList() : MutableList<MutableMap<String, String>> {
        var list: MutableList<MutableMap<String, String>> = mutableListOf()

        var city = mutableMapOf("name" to "大阪", "q" to "Osaka")
        list.add(city)
        city = mutableMapOf("name" to "神戸", "q" to "Kobe")
        list.add(city)
        city = mutableMapOf("name" to "京都", "q" to "Kyoto")
        list.add(city)
        city = mutableMapOf("name" to "大津", "q" to "Otu")
        list.add(city)
        city = mutableMapOf("name" to "奈良", "q" to "Nara")
        list.add(city)
        city = mutableMapOf("name" to "和歌山", "q" to "Wakayama")
        list.add(city)
        city = mutableMapOf("name" to "姫路", "q" to "Himeji")
        list.add(city)

        return list
    }

}