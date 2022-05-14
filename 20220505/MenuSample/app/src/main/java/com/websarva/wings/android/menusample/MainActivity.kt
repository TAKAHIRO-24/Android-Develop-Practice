package com.websarva.wings.android.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    //リストビューに表示するリストデータ
    private var _menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
    //SimpleAdapterの第４引数fromに使用するプロパティ
    private var _from = arrayOf("name", "price")
    //SimpleAdapterの第５引数toに使用するプロパティ
    private var _to = intArrayOf(R.id.tvMenuNameRow, R.id.tvMenuPriceRow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //定食メニューListオブジェクトをprivateメソッドを利用して用意し、プロパティに格納。
        _menuList = createTeishokuList()
        //画面部品ListViewを取得。
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        //SimpleAdapterを生成。
        val adapter = SimpleAdapter(this@MainActivity, _menuList, R.layout.row, _from, _to)
        //アダプタの登録。
        lvMenu.adapter = adapter
        //リストタップのリスナクラス登録。
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    //オプションメニュー作成
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //オプションメニュー用xmlファイルをインフレイト
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return true
    }

    //オプションメニュー選択時処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //戻り値用の変数を初期値trueで用意。
        var returnVal = true
        //選択されたメニューのIDのR値による処理の分岐。
        when(item.itemId) {
            //定食メニューが選択された場合の処理。
            R.id.menuListOptionTeishoku -> {
                //定食メニューリストデータの生成。
                _menuList = createTeishokuList()
            }
            //カレーメニューが選択された場合の処理。
            R.id.menuListOptionCurry -> {
                //カレーメニューリストデータの生成。
                _menuList = createCurryList()
            }
            //それ以外
            else -> {
                //親クラスの同名メソッドを呼び出し、その戻り値をreturnValとする。
                returnVal = super.onOptionsItemSelected(item)
            }
        }
        //画面部品ListViewを取得。
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        //SimpleAdapterを選択されたメニューデータで生成。
        val adapter = SimpleAdapter(this@MainActivity, _menuList, R.layout.row, _from, _to)
        //アダプタの登録。
        lvMenu.adapter = adapter

        return returnVal
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            //タップされた行のデータを取得。SimpleAdapterでは１行分のデータはMutableMap型
            val item = parent.getItemAtPosition(position) as MutableMap<String, Any>
            //定食名と金額を取得。
            val menuName = item["name"] as String
            val menuPrice = item["price"] as Int
            //インデントオブジェクトを生成。
            val intent2MenuThanks = Intent(this@MainActivity, MenuThanksActivity::class.java)
            //第２画面に送るデータを格納。
            intent2MenuThanks.putExtra("menuName", menuName)
            intent2MenuThanks.putExtra("menuPrice", "${menuPrice}円")
            //第２画面の起動。
            startActivity(intent2MenuThanks)
        }

    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        //定食メニューリスト用のListオブジェクトを用意。
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        //「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        var menu = mutableMapOf<String, Any>("name" to "から揚げ定食"
                                            ,"price" to 800
                                            ,"desc" to "若鳥のから揚げにサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)
        //「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "ハンバーグ定食"
                                        ,"price" to 850
                                        ,"desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)
        //「生姜焼き定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "生姜焼き定食"
                                        ,"price" to 850
                                        ,"desc" to "生姜焼きにサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)
        //「ステーキ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "ステーキ定食"
                                        ,"price" to 1000
                                        ,"desc" to "ステーキ定食にサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)
        //「野菜炒め定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "野菜炒め定食"
                                        ,"price" to 750
                                        ,"desc" to "野菜炒めにサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)
        //「とんかつ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "とんかつ定食"
                                        ,"price" to 900
                                        ,"desc" to "とんかつにサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)
        //「ミンチかつ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "ミンチかつ定食"
                                        ,"price" to 850
                                        ,"desc" to "ミンチかつにサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)
        //「チキンカツ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "チキンカツ定食"
                                        ,"price" to 900
                                        ,"desc" to "チキンカツにサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)
        //「コロッケ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "コロッケ定食"
                                        ,"price" to 850
                                        ,"desc" to "コロッケにサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)
        //「回鍋肉定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "回鍋肉定食"
                                        ,"price" to 750
                                        ,"desc" to "回鍋肉にサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)
        //「麻婆豆腐定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "麻婆豆腐定食"
                                        ,"price" to 800
                                        ,"desc" to "麻婆豆腐にサラダ、ご飯とお味噌汁がつきます。")
        menuList.add(menu)

        return menuList
    }

    private fun createCurryList(): MutableList<MutableMap<String, Any>> {
        //定食メニューリスト用のListオブジェクトを用意。
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        //「ビーフカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        var menu = mutableMapOf<String, Any>("name" to "ビーフカレー"
                                            ,"price" to 520
                                            ,"desc" to "特選スパイスをきかせた国産ビーフ100%のカレーです。")
        menuList.add(menu)
        //「ポークカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "ポークカレー"
                                        ,"price" to 420
                                        ,"desc" to "特選スパイスをきかせた国産ポーク100%のカレーです。")
        menuList.add(menu)
        //「キーマカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "キーマカレー"
                                        ,"price" to 850
                                        ,"desc" to "特選スパイスをきかせた国産キーマ肉100%のカレーです。")
        menuList.add(menu)
        //「チキンカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "チキンカレー"
                                        ,"price" to 1000
                                        ,"desc" to "特選スパイスをきかせた国産チキン100%のカレーです。")
        menuList.add(menu)
        //「バターチキンカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "バターチキンカレー"
                                        ,"price" to 750
                                        ,"desc" to "特選スパイスをきかせた国産バーターチキン100%のカレーです。")
        menuList.add(menu)
        //「カツカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "カツカレー"
                                        ,"price" to 900
                                        ,"desc" to "特選スパイスをきかせた国産カツ100%のカレーです。")
        menuList.add(menu)
        //「スープカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "スープカレー"
                                        ,"price" to 850
                                        ,"desc" to "特選スパイスをきかせた国産スープ100%のカレーです。")
        menuList.add(menu)
        //「シーフードカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "シーフードカレー"
                                        ,"price" to 900
                                        ,"desc" to "特選スパイスをきかせた国産シーフード100%のカレーです。")
        menuList.add(menu)
        //「グリーンカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "グリーンカレー"
                                        ,"price" to 850
                                        ,"desc" to "特選スパイスをきかせた国産グリーン100%のカレーです。")
        menuList.add(menu)
        //「サグパニール」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "サグニパール"
                                        ,"price" to 750
                                        ,"desc" to "特選スパイスをきかせた国産ほうれん草100%のカレーです。")
        menuList.add(menu)
        //「マッサマンカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "マッサマンカレー"
                                        ,"price" to 800
                                        ,"desc" to "特選スパイスをきかせた国産マッサマン100%のカレーです。")
        menuList.add(menu)
        //「ドライカレー」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "ドライカレー"
                                        ,"price" to 600
                                        ,"desc" to "特選スパイスをきかせた国産水100%のカレーです。")
        menuList.add(menu)
        //「チャナマサラ」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, Any>("name" to "チャナマサラ"
                                        ,"price" to 900
                                        ,"desc" to "特選スパイスをきかせた国産ヒヨコマメ100%のカレーです。")
        menuList.add(menu)

        return menuList
    }
}