package com.dungnv.bookmanagerkotlin.base

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dungnv.myapp.model.data.MyDatabase
import org.jetbrains.annotations.NotNull

abstract class BaseDatabase<T>(context: Context) : IDatabase<T> {
    private var data: MyDatabase = MyDatabase(context)
    var database: SQLiteDatabase? = null


    @NotNull
    override fun clone() {
        if (database!!.isOpen) {
            database!!.close()
        }

    }

    @NotNull
    override fun open() {
        if (database == null || !database!!.isOpen) {
            database = data.open()
        }
    }


}