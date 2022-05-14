package com.websarva.wings.android.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}