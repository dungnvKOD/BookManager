package com.dungnv.bookmanagerkotlin.model.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.dungnv.bookmanagerkotlin.base.IdRandom
import com.dungnv.myapp.model.Entity.Book
import com.dungnv.myapp.model.data.MyDatabase

class BookData(val context: Context) {
    private val data: MyDatabase = MyDatabase(context)
    private var database: SQLiteDatabase? = null

    private fun open() {
        if (database == null || !database!!.isOpen) {
            database = data.open()

        }
    }

    private fun close() {
        if (database!!.isOpen) {
            database!!.close()
        }
    }

    fun insertBook(book: Book, key: String): Boolean {
        open()
        val value: ContentValues = ContentValues()
        val id: String = IdRandom().randomId("S")
        value.put(MyDatabase.S_MASACH, id)
        value.put(MyDatabase.S_GIABIA, book.price)
        value.put(MyDatabase.S_NXB, book.nxb)
        value.put(MyDatabase.S_SOLUONG, book.quanlity)
        value.put(MyDatabase.S_TIEU_DE, book.title)
        value.put(MyDatabase.S_TAC_GIA, book.author)
        value.put(MyDatabase.MATHELOAI_SACH, key)

        val check = database!!.insert(MyDatabase.TB_SACH, null, value)
        close()
        return check.toInt() != -1
    }

    fun getBook(): ArrayList<Book>? {
        open()
        val books: ArrayList<Book>? = ArrayList()
        val sql = "SELECT * FROM ${MyDatabase.TB_SACH}"
        var cursor: Cursor? = null

        cursor = database!!.rawQuery(sql, null)

        val idBookIndex = cursor!!.getColumnIndex(MyDatabase.S_MASACH)
        val giaIndex = cursor!!.getColumnIndex(MyDatabase.S_GIABIA)
        val nXBIndex = cursor!!.getColumnIndex(MyDatabase.S_NXB)
        val soLuongIndex = cursor!!.getColumnIndex(MyDatabase.S_SOLUONG)
        val tieuDeIndex = cursor!!.getColumnIndex(MyDatabase.S_TIEU_DE)
        val tacGiaIndex = cursor!!.getColumnIndex(MyDatabase.S_TAC_GIA)


        if (cursor == null) {
            return books
        }

        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val bookTypeData = BookTypeData(context)

            val id = cursor.getString(idBookIndex)
            val gia = cursor.getFloat(giaIndex)
            val nxb = cursor.getString(nXBIndex)
            val soLuong = cursor.getInt(soLuongIndex)
            val tieuDe = cursor.getString(tieuDeIndex)
            val tacGia = cursor.getString(tacGiaIndex)
            val idLoai = cursor.getString(cursor.getColumnIndex(MyDatabase.MATHELOAI_SACH))

//            var maTheLoaiSach = bookTypeData.getNameById(idLoai)
//            Log.d("111", maTheLoaiSach.toString())

            val book: Book = Book(id, tieuDe, gia, soLuong, nxb, tacGia, idLoai)
            books!!.add(book)
            cursor.moveToNext()
        }
        close()
        return books
    }

    fun remove(id: String): Boolean {
        open()
        val check = database!!.delete(MyDatabase.TB_SACH, "${MyDatabase.S_MASACH}=?", arrayOf(id))

        close()
        return check.toInt() == 1
    }

    fun bookUpdate(book: Book) {
        open()
        val values = ContentValues()
        values.put(MyDatabase.S_TIEU_DE, book.title)
        values.put(MyDatabase.S_GIABIA, book.price)
        database!!.update(MyDatabase.TB_SACH, values, "${MyDatabase.S_MASACH}=?", arrayOf(book.idBook))
        close()
    }
}