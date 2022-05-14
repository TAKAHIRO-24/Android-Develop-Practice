package com.websarva.wings.android.lifecyclesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //onCreate()実行時ログ
        Log.i("LifeCycleSample", "Main onCreate() called.")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        //onStart()実行時ログ
        Log.i("LifeCycleSample", "Main onStart() called.")
        super.onStart()
    }

    public override fun onRestart() {
        //onRestart()実行時ログ
        Log.i("LifeCycleSample", "Main onRestart() called.")
        super.onRestart()
    }

    public override fun onResume() {
        //onResume()実行時ログ
        Log.i("LifeCycleSample", "Main onResume() called.")
        super.onResume()
    }

    public override fun onPause() {
        //onPause()実行時ログ
        Log.i("LifeCycleSample", "Main onPause() called.")
        super.onPause()
    }

    public override fun onStop() {
        //onStop()実行時ログ
        Log.i("LifeCycleSample", "Main onStop() called.")
        super.onStop()
    }

    public override fun onDestroy() {
        //onDestroy()実行時ログ
        Log.i("LifeCycleSample", "Main onDestroy() called.")
        super.onDestroy()
    }

    //「次の画面を表示」ボタンがタップされた時の処理。
    fun onButtonClick(view: View) {
        //インテントオブジェクトを用意。
        val intent = Intent(this@MainActivity, SubActivity::class.java)
        //アクティビティを起動
        startActivity(intent)
    }
}