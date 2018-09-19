package com.dungnv.myapp.model.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dungnv.myapp.model.Entity.User

class UserData(context: Context) {

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
        return index.toInt() != -1
    }

    fun getUsers(): ArrayList<User> {
        open()
        val users: ArrayList<User> = ArrayList()
        val sql = "SELECT *FROM ${MyDatabase.TB_USER}"
        val cursor = database!!.query(sql, null, null, null, null, null, null)
        val emailIndex = cursor.getColumnIndex(MyDatabase.U_USERNAME)
        val passIndex = cursor.getColumnIndex(MyDatabase.U_PASS)
        val nameIndex = cursor.getColumnIndex(MyDatabase.U_NAME)
        val phoneNumberIndex = cursor.getColumnIndex(MyDatabase.U_PHONEME)

        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val email = cursor.getString(emailIndex)
            val pass = cursor.getString(passIndex)
            val name = cursor.getString(nameIndex)
            val phoneNumber = cursor.getString(phoneNumberIndex)

            val user = User(email, pass, name, phoneNumber)
            users.add(user)
            cursor.moveToNext()
        }
        close()
        return users


    }


}