package com.rshub.views

import com.rshub.models.AccountModel
import com.rshub.models.AccountsModel
import javafx.scene.control.ButtonType
import javafx.stage.FileChooser
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import tornadofx.*
import java.io.File
import java.nio.file.Files

class AccountView : View("Accounts") {

    private val model: AccountsModel by inject()

    override val root = borderpane {
        prefWidth = 600.0
        prefHeight = 500.0
        style {
            baseColor = c("000000")
        }
        top {
            menubar {
                menu("File") {
                    item("Open") {
                        setOnAction {
                            val file = chooseFile(
                                "Choose Accounts Json File",
                                arrayOf(FileChooser.ExtensionFilter("Josn File", "*.json")),
                                File(System.getProperty("user.home")),
                                "accounts.json",
                                FileChooserMode.Single
                            ).singleOrNull()
                            if (file != null) {
                                val text = file.readText()
                                val accounts = Json.decodeFromString<List<AccountModel>>(text)
                                model.accounts.clear()
                                model.accounts.addAll(accounts)
                            }
                        }
                    }
                    item("Save") {
                        setOnAction {
                            val file = chooseFile(
                                "Choose Accounts Json File",
                                arrayOf(FileChooser.ExtensionFilter("Json File", "*.json")),
                                File(System.getProperty("user.home")),
                                "accounts.json",
                                FileChooserMode.Save
                            ).single()
                            Files.write(file.toPath(), Json.encodeToString(model.accounts.toList()).toByteArray())
                        }
                    }
                }
                menu("Accounts") {
                    item("Add Account") {
                        setOnAction {
                            val account = AccountModel("", "", 1, "default")
                            model.accounts.add(account)
                        }
                    }
                    item("Delete Account") {
                        disableWhen(model.selectedAccount.isNull)
                        setOnAction {
                            val sel = model.selectedAccount.get()
                            confirm(
                                "Delete Account",
                                "Are you sure you want to delete ${sel.email.get()}",
                                confirmButton = ButtonType.YES
                            ) {
                                model.accounts.remove(model.selectedAccount.get())
                            }
                        }
                    }
                }
            }
        }
        center {
            hbox {
                spacing = 10.0
                listview<AccountModel> {
                    items.bind(model.accounts) { it }
                    bindSelected(model.selectedAccount)
                    cellFormat { textProperty().bind(it.email) }
                    items.onChange {
                        if (it.next() && it.wasAdded()) {
                            selectionModel.select(it.addedSubList.single())
                        }
                    }
                }
                vbox {
                    paddingAll = 10.0
                    dynamicContent(model.selectedAccount) {
                        if (it != null) {
                            form {
                                fieldset("Account") {
                                    field("Username") {
                                        textfield(it.email)
                                    }
                                    field("Password") {
                                        passwordfield(it.password)
                                    }
                                }
                                fieldset("Settings") {
                                    field("World") {
                                        textfield(it.world) {
                                            stripNonInteger()
                                        }
                                    }
                                    field("Group") {
                                        textfield(it.group)
                                    }
                                }
                            }
                        } else {
                            label("No Selected Account.")
                        }
                    }
                }
            }
        }
    }
}