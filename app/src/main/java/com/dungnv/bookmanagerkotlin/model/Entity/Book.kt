package com.dungnv.myapp.model.Entity

class Book {
    var idBook: String? = null
    var title: String? = null
    var price: Float? = null//gias
    var quanlity: Int? = null//so luong
    var nxb: String? = null

    constructor(idBook: String?, title: String?, price: Float?, quanlity: Int?, nxb: String?) {
        this.idBook = idBook
        this.title = title
        this.price = price
        this.quanlity = quanlity
        this.nxb = nxb
    }

    constructor()


}