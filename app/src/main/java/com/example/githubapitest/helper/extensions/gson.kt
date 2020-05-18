package com.example.githubapitest.helper.extensions

import com.google.gson.Gson

inline fun <reified T : Any> Gson.stringToData(json: String?): T {
    return this.fromJson(json, T::class.java)
}

