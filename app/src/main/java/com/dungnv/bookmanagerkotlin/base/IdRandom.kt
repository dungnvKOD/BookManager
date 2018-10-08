package com.dungnv.bookmanagerkotlin.base

import com.dungnv.bookmanagerkotlin.common.Constants
import java.util.*

class IdRandom {
    companion object {
        private var random = Random()
    }

    fun randomId(first: String): String {
        var id: String = "${first}_"
        for (i in 0..5) {
            id += Constants.AB[random.nextInt(Constants.AB.length)]
        }

        return id
    }

}