package com.dungnv.myapp.model.Entity

class User {
    var userName: String? = null
    var password: String? = null
    var name: String? = null
    var phoneNumber: String? = null

    constructor(userName: String?, password: String?, name: String?, phoneNumber: String?) {
        this.userName = userName
        this.password = password
        this.name = name
        this.phoneNumber = phoneNumber
    }

    constructor()


}