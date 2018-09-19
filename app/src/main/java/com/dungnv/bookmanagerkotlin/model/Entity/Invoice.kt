package com.dungnv.myapp.model.Entity

class Invoice {//hoa don
    var idInvoice: String? = null//ma hoa don
    var purchasing: String? = null//ngay mua

    constructor(idInvoice: String?, purchasing: String?) {
        this.idInvoice = idInvoice
        this.purchasing = purchasing
    }

    constructor()


}