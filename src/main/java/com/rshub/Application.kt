package com.rshub

import tornadofx.launch

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        launch<AccountApplication>()
    }
}