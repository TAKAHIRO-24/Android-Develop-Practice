package com.websarva.wings.android.databasesample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.text.StringBuilder

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    //クラス内のprivate定数を宣言するためにcompanion objectブロックとする。
    companion object {
        //データベースファイル名の定数。
        private const val DATABASE_NAME = "cocktailmemo.db"
        //バージョン情報の定数。
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        //テーブル作成用SQL文字列の作成。
        val sb = StringBuilder().also {
            it.append("CREATE TABLE cocktailmemos (")
            it.append("_id INTEGER PRIMARY KEY,")
            it.append("name TEXT,")
            it.append("note TEXT")
            it.append(");")
        }
        val sql = sb.toString()

        //SQLの実行。
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}