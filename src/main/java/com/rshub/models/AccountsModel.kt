package com.rshub.models

import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import tornadofx.ViewModel

class AccountsModel : ViewModel() {

    val accounts = bind { SimpleListProperty<AccountModel>(this, "accounts", FXCollections.observableArrayList()) }

    val selectedAccount = bind { SimpleObjectProperty<AccountModel>(this, "selected_account") }

}