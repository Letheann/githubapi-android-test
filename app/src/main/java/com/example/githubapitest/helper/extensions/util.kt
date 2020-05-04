package com.example.githubapitest.helper.extensions

infix fun Boolean.thenDo(action: () -> Unit) {
    if (this) action()
}