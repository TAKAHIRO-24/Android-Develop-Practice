package com.websarva.wings.android.listviewsample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ListViewオブジェクトを取得。
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        //リストビューに表示するリストデータを作成。
        var menuList = mutableListOf<String>(
             "から揚げ定食", "ハンバーグ定食", "生姜焼き定食", "ステーキ定食"
            ,"野菜炒め定食", "とんかつ定食", "ミンチかつ定食", "チキンカツ定食"
            ,"コロッケ定食", "回鍋肉定食", "麻婆豆腐定食", "青椒肉絲定食"
            ,"焼き魚定食", "焼肉定食"
        )
        //アダプタオブジェクトを生成。
        val adapter = ArrayAdapter(this@MainActivity
                                    ,android.R.layout.simple_list_item_1
                                    ,menuList)
        //リストビューにアダプタオブジェクトを設定。
        lvMenu.adapter = adapter

        //リストにリスナーを設定。
        lvMenu.onItemClickListener = ListItemClickLitener()
    }

    private inner class ListItemClickLitener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            //タップされた定食名を取得。
            val item = parent.getItemAtPosition(position) as String
            //トーストで表示する文字列を生成。
            val show = "あなたが選んだ定食：" + item
            //トーストの表示。
            Toast.makeText(this@MainActivity, show, Toast.LENGTH_LONG).show()
        }

    }
}