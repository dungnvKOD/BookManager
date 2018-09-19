package com.dungnv.myapp.model.Entity

class BookType {

    var idBookType: String? = null
    var nameType: String? = null
    var description: String? = null //mo ta
    var location: Int = -1

    constructor(idBookType: String?, nameType: String?, description: String?, location: Int) {
        this.idBookType = idBookType
        this.nameType = nameType
        this.description = description
        this.location = location
    }

    constructor()

}