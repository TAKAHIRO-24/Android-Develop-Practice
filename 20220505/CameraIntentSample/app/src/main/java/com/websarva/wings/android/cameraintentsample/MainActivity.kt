package com.websarva.wings.android.cameraintentsample

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    //ストレージに保存された画像のURI。
    private var _imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //親クラスの同名メソッドの呼び出し。
        super.onActivityResult(requestCode, resultCode, data)
        //カメラアプリからの戻りでかつ撮影成功の場合。
        if (requestCode == 200 && resultCode == RESULT_OK) {
            //画像を表示するImageViewを取得。
            val ivCamera = findViewById<ImageView>(R.id.ivCamera)
            //プロパティの画像URIをImageViewに設定。
            ivCamera.setImageURI(_imageUri)
        }
    }

    //画像部分がタップされたときの処理メソッド。
    fun onCameraImageClick(view: View) {

        //日時データを「yyyyMMddHHmmss」の形式に整形するフォーマッタを生成。
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        //現在の日時を取得。
        val now = Date()
        //取得した日時データを「yyyyMMddHHmmss」形式に整形した文字列を生成。
        val nowStr = dateFormat.format(now)
        //ストレージに格納する画像のファイル名を生成。ファイル名の一意を確保するためにタイムスタンプの値を利用。
        val fileName = "CameraIntentSamplePhoto_${nowStr}.jpg"

        //ContentValuesオブジェクトを生成。
        val values = ContentValues()
        //画像ファイル名を設定。
        values.put(MediaStore.Images.Media.TITLE, fileName)
        //画像ファイルの種類を設定。
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")

        //ContentResolverを使ってURIオブジェクトを生成。
        _imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //Intentオブジェクトを生成。
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //Extra情報として_imageUriを設定。
        intent.putExtra(MediaStore.EXTRA_OUTPUT, _imageUri)

        //アクティビティを起動。
        startActivityForResult(intent, 200)
    }
}