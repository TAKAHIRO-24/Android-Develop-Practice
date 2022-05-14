package com.websarva.wings.android.lifecyclesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.i("LifeCycleSample", "Sub onCreate() called.")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
    }

    public override fun onStart() {
        //onStart()実行時ログ
        Log.i("LifeCycleSample", "Sub onStart() called.")
        super.onStart()
    }

    public override fun onRestart() {
        //onRestart()実行時ログ
        Log.i("LifeCycleSample", "Sub onRestart() called.")
        super.onRestart()
    }

    public override fun onResume() {
        //onResume()実行時ログ
        Log.i("LifeCycleSample", "Sub onResume() called.")
        super.onResume()
    }

    public override fun onPause() {
        //onPause()実行時ログ
        Log.i("LifeCycleSample", "Sub onPause() called.")
        super.onPause()
    }

    public override fun onStop() {
        //onStop()実行時ログ
        Log.i("LifeCycleSample", "Sub onStop() called.")
        super.onStop()
    }

    public override fun onDestroy() {
        //onDestroy()実行時ログ
        Log.i("LifeCycleSample", "Sub onDestroy() called.")
        super.onDestroy()
    }

    //「前の画面を表示」ボタンがタップされた時の処理。
    fun onButtonClick(view: View) {
        //このアクティビティの終了。
        finish()
    }
}