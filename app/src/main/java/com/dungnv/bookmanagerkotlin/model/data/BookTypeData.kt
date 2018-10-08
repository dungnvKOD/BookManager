package com.dungnv.bookmanagerkotlin.model.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.dungnv.bookmanagerkotlin.base.IdRandom
import com.dungnv.myapp.model.Entity.BookType
import com.dungnv.myapp.model.data.MyDatabase

class BookTypeData(context: Context) {
    private val data = MyDatabase(context)
    private var database: SQLiteDatabase? = null

    companion object {
        private const val TAG = "BookTypeData"
    }


    private fun open() {
        if (database == null || !database!!.isOpen) {
            database = data.open()
        }
    }

    private fun close() {
        if (database != null || database!!.isOpen) {
            database!!.close()
            database = null
        }
    }

    fun insertBookType(bookType: BookType): Boolean {
        open()
//        var id: String? = IdRandom().randomId("B")

        val value = ContentValues()
        value.put(MyDatabase.TLS_IDTHELOAI, bookType.idBookType)
        value.put(MyDatabase.TLS_TENLOAISACH, bookType.nameType)
        value.put(MyDatabase.TLS_MOTA, bookType.description)
        value.put(MyDatabase.TLS_VITRI, bookType.location)
        val check = database!!.insert(MyDatabase.TB_THE_LOAI_SACH, null, value)

        close()
        return check.toInt() != -1
    }

    fun remove(id: String): Boolean {
        open()
        val check = database!!.delete(MyDatabase.TB_THE_LOAI_SACH, "${MyDatabase.TLS_IDTHELOAI}=?", arrayOf(id))


        close()
        return check.toInt() == 1
    }

    fun getBookType(): ArrayList<BookType>? {
        open()
        val bookTypeList: ArrayList<BookType>? = ArrayList()
        val sql = "SELECT * FROM ${MyDatabase.TB_THE_LOAI_SACH}"
        var cursor: Cursor? = null
//        if (Constants.isDebug) Log.d(TAG, "...DUNG...: ${cursor!!.count}")

        cursor = database!!.rawQuery(sql, null)

        val idBookTypeIndex = cursor!!.getColumnIndex(MyDatabase.TLS_IDTHELOAI)
        val nameTypeIndex = cursor.getColumnIndex(MyDatabase.TLS_TENLOAISACH)
        val descriptionIndex = cursor.getColumnIndex(MyDatabase.TLS_MOTA)
        val locationIndex = cursor.getColumnIndex(MyDatabase.TLS_VITRI)

        if (cursor == null) {
            return bookTypeList
        }

        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val idNameBookType = cursor.getString(idBookTypeIndex)
            val nameBookType = cursor.getString(nameTypeIndex)
            val description = cursor.getString(descriptionIndex)
            val location = cursor.getInt(locationIndex)

            val bookType = BookType(idNameBookType, nameBookType, description, location)
            bookTypeList!!.add(bookType)
            cursor.moveToNext()
        }
        close()
        return bookTypeList
    }

    fun getNameBookType(): ArrayList<String>? {
        open()
        val nameBookTypeList: ArrayList<String>? = ArrayList()
        val sql = "SELECT * FROM ${MyDatabase.TB_THE_LOAI_SACH}"
        var cursor: Cursor? = null
//        if (Constants.isDebug) Log.d(TAG, "...DUNG...: ${cursor!!.count}")

        cursor = database!!.rawQuery(sql, null)


        val nameTypeIndex = cursor.getColumnIndex(MyDatabase.TLS_TENLOAISACH)

        if (cursor == null) {
            return nameBookTypeList
        }

        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val nameBookType = cursor.getString(nameTypeIndex)
            nameBookTypeList!!.add(nameBookType)
            cursor.moveToNext()
        }
        close()
        return nameBookTypeList
    }

    fun getIdBookType(name: String): String? {
        open()
        var idBookType: String? = null
        val sql = "SELECT * FROM ${MyDatabase.TB_THE_LOAI_SACH} WHERE ${MyDatabase.TLS_TENLOAISACH}='$name'"
        var cursor: Cursor? = null
        cursor = database!!.rawQuery(sql, null)
        val nameTypeIndex = cursor.getColumnIndex(MyDatabase.TLS_IDTHELOAI)
        if (cursor == null) {
            return idBookType
        }

        if (cursor != null && cursor.moveToFirst()) {
            idBookType = cursor.getString(nameTypeIndex)
        }

        close()
        return idBookType
    }

    fun update(id: String, name: String, location: Int, mota: String): Boolean {
        open()
        val values = ContentValues()
        values.put(MyDatabase.TLS_TENLOAISACH, name)
        values.put(MyDatabase.TLS_MOTA, mota)
        values.put(MyDatabase.TLS_VITRI, location)

        val check = database!!.update(MyDatabase.TB_THE_LOAI_SACH, values, "${MyDatabase.TLS_IDTHELOAI}=?", arrayOf(id))
        close()
        return check == 1
    }

    fun getNameById(id: String): String? {
        open()
        var nameBookType: String? = null
        val sql = "SELECT * FROM ${MyDatabase.TB_THE_LOAI_SACH} WHERE ${MyDatabase.TLS_IDTHELOAI}='$id'"
        var cursor: Cursor? = null
        cursor = database!!.rawQuery(sql, null)
        Log.e("111", cursor!!.count.toString())
        val nameTypeIndex = cursor.getColumnIndex(MyDatabase.TLS_TENLOAISACH)

        if (cursor == null) {
            return nameBookType
        }

        if (cursor != null && cursor.moveToFirst()) {
            nameBookType = cursor.getString(nameTypeIndex)
        }
        close()
        return nameBookType
    }
}