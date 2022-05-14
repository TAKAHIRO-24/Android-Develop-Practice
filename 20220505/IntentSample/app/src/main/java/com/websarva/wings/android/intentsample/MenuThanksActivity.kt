package com.websarva.wings.android.intentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MenuThanksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)

        //リスト画面から渡されたデータを取得。
        val menuName = intent.getStringExtra("menuName")
        val menuPrice = intent.getStringExtra("menuPrice")
        //定食名と金額を表示させるTextViewを取得。
        val tvMenuName = findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = findViewById<TextView>(R.id.tvMenuPrice)
        //TextViewに定食名と金額を表示。
        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice

//        //「リストへ戻る」ボタンを取得（onBackButtonClick()と同等の処理をリスナで行う場合）
//        val btBack = findViewById<Button>(R.id.btBack)
//        //リスナを登録。
//        btBack.setOnClickListener(ButtonClickListener())
    }

    fun onBackButtonClick(view: View) {
        finish()
    }

//    //ボタンがクリックされた際の処理（onBackButtonClick()と同等の処理リスナで行う場合）
//    private inner class ButtonClickListener : View.OnClickListener {
//        override fun onClick(view: View) {
//            finish()
//        }
//    }
}