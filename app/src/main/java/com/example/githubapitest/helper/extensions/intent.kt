package com.example.githubapitest.helper.extensions

import android.content.Intent
import com.example.githubapitest.MainApplication

inline fun <reified R : Any> Intent.extras(key: String): R? {
    return if (this.getSerializableExtra(key) == null) {
        null
    } else {
        this.getSerializableExtra(key) as R
    }

}

inline fun <reified R : Any> Intent.extrasBundle(key: String): R? {
    return if (this.extras?.getBundle(MainApplication.APPLICATION)?.getSerializable(key) == null) {
        null
    } else {
        this.extras?.getBundle(MainApplication.APPLICATION)?.getSerializable(key) as R
    }
}