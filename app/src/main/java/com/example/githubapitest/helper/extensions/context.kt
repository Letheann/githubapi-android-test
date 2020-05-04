package com.example.githubapitest.helper.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.githubapitest.MainApplication

inline fun <reified A> Context.toActivity(
    clear: Boolean = false,
    noinline extras: (Bundle.() -> Unit)? = null
) {
    Intent(this, A::class.java).apply {
        clear thenDo {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        extras?.also {
            putExtra(MainApplication.APPLICATION, Bundle().apply(extras::invoke))
        }
    }.also { startActivity(it) }
}


inline fun <reified A> Activity.toActivityForResult(
    clear: Boolean = false,
    requestCode: Int = 1,
    noinline extras: (Bundle.() -> Unit)? = null
) {
    Intent(this, A::class.java).apply {
        clear thenDo {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        extras?.also {
            putExtra(MainApplication.APPLICATION, Bundle().apply(extras::invoke))
        }
    }.also { startActivityForResult(it, requestCode) }
}