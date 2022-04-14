package com.rshub.models

import com.rshub.serialization.AccountModelSerializer
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import kotlinx.serialization.Serializable
import tornadofx.ViewModel

@Serializable(with = AccountModelSerializer::class)
class AccountModel(name: String, password: String, world: Int, group: String) : ViewModel() {

    val email = bind { SimpleStringProperty(this, "name", name) }
    val password = bind { SimpleStringProperty(this, "password", password) }
    val world = bind { SimpleIntegerProperty(this, "world", world) }
    val group = bind { SimpleStringProperty(this, "group", group) }

}