package com.websarva.wings.android.asyncsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import androidx.core.os.HandlerCompat
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
            val postExecutor = WeatherInfoPostExecutor()
            //Handlerオブジェクトを生成した元スレッドで処理を行う。
            _handler.post(postExecutor)
        }
    }

    //非同期でお天気情報を取得した後にUIスレッドでその情報を表示するためのクラス。
    private inner class WeatherInfoPostExecutor() : Runnable {
        @UiThread //UIスレッドで実行されることがコンパイラによって保証
        override fun run() {
            //ここにUIスレッドで行う処理コードを記述。
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