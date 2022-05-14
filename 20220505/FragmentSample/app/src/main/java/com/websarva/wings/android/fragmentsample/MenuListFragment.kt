package com.websarva.wings.android.fragmentsample

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MenuListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //フラグメントで表示する画面をXMLファイルからインフレートする。
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        //画面部品ListViewを取得。
        val lvMenu = view.findViewById<ListView>(R.id.lvMenu)

        //SimpleAdapterで使用するMutableListオブジェクトを用意。
        val menuList: MutableList<MutableMap<String, String>> = GetMenuList()

        //SimpleAdapter第４引数from用データの用意。
        val from = arrayOf("name", "price")
        //SimpleAdapter第５引数to用データの用意。
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        //SimpleAdapterを生成。
        val adapter = SimpleAdapter(activity, menuList, android.R.layout.simple_list_item_2, from, to)
        //アダプタの登録。
        lvMenu.adapter = adapter

        //リスナの登録。
        lvMenu.onItemClickListener = ListItemClickListener()

        //インフレートされた画面を戻り値として返す。
        return view
    }

    //リストがタップされた時の処理が記述されたメンバクラス。
    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            //タップされた行のデータを取得。SimpleAdapterでは１行分のデータはMutableMap型。
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            //定食名と金額を取得。
            val menuName = item["name"]
            val menuPrice = item["price"]
            //インテントオブジェクトを生成。
            val intent2MenuThanks = Intent(activity, MenuThanksActivity::class.java)
            //第２画面に送るデータを格納。
            intent2MenuThanks.putExtra("menuName", menuName)
            intent2MenuThanks.putExtra("menuPrice", menuPrice)
            //第２画面の起動。
            startActivity(intent2MenuThanks)
        }
    }

    //メニュー登録
    private fun GetMenuList(): MutableList<MutableMap<String, String>> {
        //MutableListオブジェクトを用意。
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        //「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        var menu = mutableMapOf<String, String>("name" to "から揚げ定食", "price" to "800円")
        menuList.add(menu)
        //「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, String>("name" to "ハンバーグ定食", "price" to "850円")
        menuList.add(menu)
        //「生姜焼き定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, String>("name" to "生姜焼き定食", "price" to "850円")
        menuList.add(menu)
        //「ステーキ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, String>("name" to "ステーキ定食", "price" to "1000円")
        menuList.add(menu)
        //「野菜炒め定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, String>("name" to "野菜炒め定食", "price" to "750円")
        menuList.add(menu)
        //「とんかつ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, String>("name" to "とんかつ定食", "price" to "900円")
        menuList.add(menu)
        //「ミンチかつ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, String>("name" to "ミンチかつ定食", "price" to "850円")
        menuList.add(menu)
        //「チキンカツ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, String>("name" to "チキンカツ定食", "price" to "900円")
        menuList.add(menu)
        //「コロッケ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, String>("name" to "コロッケ定食", "price" to "850円")
        menuList.add(menu)
        //「回鍋肉定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, String>("name" to "回鍋肉定食", "price" to "750円")
        menuList.add(menu)
        //「麻婆豆腐定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf<String, String>("name" to "麻婆豆腐定食", "price" to "800円")
        menuList.add(menu)

        return menuList
    }
}