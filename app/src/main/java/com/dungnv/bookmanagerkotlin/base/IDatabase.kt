package com.dungnv.bookmanagerkotlin.base

interface IDatabase<T> {

    fun clone()
    fun open()

    fun insertItem(t: T): Boolean
    fun updateItem(t: T): Boolean
    fun deleteItem(id: String): Boolean
    fun getAllItem(): ArrayList<T>?
    fun getItemById(t: T): T?

}