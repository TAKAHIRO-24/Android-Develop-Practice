package com.websarva.wings.android.intentsamplw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //画面部品ListViewを取得
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        //メニューリストに追加
        val menuList = addMenuList()

        //SimpleAdapter第４引数from用データの用意
        val from = arrayOf("name", "price")
        //SimpleAdapter第５引数to用データの用意
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        //SimpleAdapterを生成
        val adapter = SimpleAdapter(
            this@MainActivity
            , menuList
            , android.R.layout.simple_list_item_2
            , from
            , to)
        //アダプタの登録
        lvMenu.adapter = adapter
    }

    //メニューリストに追加
    private fun addMenuList(): MutableList<MutableMap<String, String>> {
        //SimpleAdapterで使用するMutableListオブジェクトを用意
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        //「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        var menu = mutableMapOf("name" to "から揚げ定食", "price" to "800円")
        menuList.add(menu)
        //「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to "850円")
        menuList.add(menu)
        //「生姜焼き定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to "850円")
        menuList.add(menu)
        //「ステーキ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ステーキ定食", "price" to "1000円")
        menuList.add(menu)
        //「野菜炒め定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to "750円")
        menuList.add(menu)
        //「とんかつ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "とんかつ定食", "price" to "900円")
        menuList.add(menu)
        //「ミンチかつ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to "850円")
        menuList.add(menu)
        //「チキンカツ定食」のデータを格納するMapオブジェクトの用意とmutableListへのデータ登録
        menu = mutableMapOf("name" to "チキンカツ定食", "price" to "900円")
        menuList.add(menu)
        //「コロッケ定食」のデータを格納するMapオブジェクトの用意とmutableListへのデータ登録
        menu = mutableMapOf("name" to "コロッケ定食", "price" to "850円")
        menuList.add(menu)
        //「回鍋肉定食」のデータを格納するMapオブジェクトの用意とmutableListへのデータ登録
        menu = mutableMapOf("name" to "回鍋肉定食", "price" to "750円")
        menuList.add(menu)
        //「麻婆豆腐定食」のデータを格納するMapオブジェクトの用意とmutalbeListへのデータ登録
        menu = mutableMapOf("name" to "麻婆豆腐定食", "price" to "800円")
        menuList.add(menu)

        return menuList
    }
}