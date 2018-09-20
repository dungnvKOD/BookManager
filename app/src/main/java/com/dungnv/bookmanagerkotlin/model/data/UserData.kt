package com.dungnv.myapp.model.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.dungnv.bookmanagerkotlin.common.Constants
import com.dungnv.myapp.model.Entity.User

class UserData(context: Context) {
    companion object {
        val TAG = "UserData"
    }

    private var data: MyDatabase = MyDatabase(context)
    private var database: SQLiteDatabase? = null

    private fun open() {
        if (database == null || !database!!.isOpen) {
            database = data.open()
        }
    }

    private fun close() {
        if (database != null || database!!.isOpen) {
            database!!.close()
        }
    }

    fun insertUser(user: User): Boolean {
        open()
        val value = ContentValues()
        value.put(MyDatabase.U_NAME, user.name)
        value.put(MyDatabase.U_PASS, user.password)
        value.put(MyDatabase.U_USERNAME, user.userName)
        value.put(MyDatabase.U_PHONEME, user.phoneNumber)
        val index = database!!.insert(MyDatabase.TB_USER, null, value)
        close()
        Log.d(TAG, index.toString())
        return index.toInt() != -1
    }


    /**
    query tim kiem user voi username = tham so truyen vao

    1: ten bang
    2 : array cot can lay du lieu
    3: ten cot dung de query
    4 : gia tri can tim
    5,6,7 : null. la cac cau lenh xap xep dieu kien...
     */


    fun checkRegister(userName: String): Boolean {
        open()

        val cursor = database!!.query(MyDatabase.TB_USER, arrayOf(MyDatabase.U_USERNAME), "${MyDatabase.U_USERNAME}=?", arrayOf(userName), null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            return false
            close()
        }
        close()
        return true

    }

    fun getUserByEmail(email: String): User? {
        open()
        var user: User? = null

        val columnt = arrayOf(MyDatabase.U_USERNAME, MyDatabase.U_PASS, MyDatabase.U_NAME, MyDatabase.U_PHONEME)

        val cursor = database!!.query(MyDatabase.TB_USER, columnt, "${MyDatabase.U_USERNAME}=?", arrayOf(email), null, null, null)

        if (cursor != null && cursor.moveToFirst()) {
            val email = cursor.getString(cursor.getColumnIndex(MyDatabase.U_USERNAME))
            val pass = cursor.getString(cursor.getColumnIndex(MyDatabase.U_PASS))
            val name = cursor.getString(cursor.getColumnIndex(MyDatabase.U_NAME))
            val phoneNumber = cursor.getString(cursor.getColumnIndex(MyDatabase.U_PHONEME))
            user = User(email, pass, name, phoneNumber)
            if (Constants.isDebug) Log.d(TAG, "...")
        }

        close()
        if (Constants.isDebug) Log.d(TAG, cursor.count.toString())

        return user
    }

    fun getAllUser(): ArrayList<User> {
        open()
        var users: ArrayList<User> = ArrayList()
        val sql = "SELECT * FROM ${MyDatabase.TB_USER}"
        var cursor: Cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()
        while (cursor != null && !cursor.isAfterLast) {
            val email = cursor.getString(cursor.getColumnIndex(MyDatabase.U_USERNAME))
            val pass = cursor.getString(cursor.getColumnIndex(MyDatabase.U_PASS))
            val name = cursor.getString(cursor.getColumnIndex(MyDatabase.U_NAME))
            val phone = cursor.getString(cursor.getColumnIndex(MyDatabase.U_PHONEME))
            val user = User(email, pass, name, phone)
            users.add(user)
            cursor.moveToNext()
        }
        if (Constants.isDebug) Log.d(TAG, users.size.toString() + "......")
        close()
        return users
    }

    fun remove(email: String): Boolean {
        open()
        val check = database!!.delete(MyDatabase.TB_USER, "${MyDatabase.U_USERNAME}=?", arrayOf(email))
        close()
        return check == 1

    }


}