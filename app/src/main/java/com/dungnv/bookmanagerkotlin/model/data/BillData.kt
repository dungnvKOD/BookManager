package com.dungnv.bookmanagerkotlin.model.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.dungnv.bookmanagerkotlin.base.BaseDatabase
import com.dungnv.bookmanagerkotlin.base.IdRandom
import com.dungnv.bookmanagerkotlin.common.Constants
import com.dungnv.myapp.model.Entity.Bill
import com.dungnv.myapp.model.data.MyDatabase

class BillData(context: Context) : BaseDatabase<Bill>(context) {

    //ok
    override fun insertItem(t: Bill): Boolean {
        open()

        val values = ContentValues()
        values.put(MyDatabase.HD_MAHOADON, t.idInvoice)
        values.put(MyDatabase.HD_NGAYMUA, t.purchasing)
        val check = database!!.insert(MyDatabase.TB_HOA_DON, null, values)
        clone()
        return check.toInt() != -1
    }

    //ok
    override fun updateItem(t: Bill): Boolean {
        open()
        val values = ContentValues()
        values.put(MyDatabase.HD_NGAYMUA, t.purchasing)
        //TODO...
        clone()
        return false
    }

    //ok
    override fun deleteItem(id: String): Boolean {
        open()
        val check = database!!.delete(MyDatabase.TB_HOA_DON, "${MyDatabase.HD_MAHOADON}=?", arrayOf(id))
        clone()
        return check == 1
    }


    override fun getAllItem(): ArrayList<Bill>? {
        open()
        val bills: ArrayList<Bill> = ArrayList()

        val sql = "SELECT *FROM ${MyDatabase.TB_HOA_DON}"
        val cursor: Cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()

        while (!cursor.isAfterLast) {

            val id = cursor.getString(cursor.getColumnIndex(MyDatabase.HD_MAHOADON))
            val purchasing = cursor.getString(cursor.getColumnIndex(MyDatabase.HD_NGAYMUA))
            val bill: Bill = Bill(id, purchasing)
            bills.add(bill)
            cursor.moveToNext()

        }
        clone()
        return bills
    }

    override fun getItemById(t: Bill): Bill? {
        open()
        //TODO....

        clone()
        return null
    }
}