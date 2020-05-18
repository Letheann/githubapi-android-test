package com.example.githubapitest.helper.extensions

import java.util.ArrayList

infix fun Boolean.thenDo(action: () -> Unit) {
    if (this) action()
}

infix fun <E> ArrayList<E>.add(async: E) {
   this.add(async)
}