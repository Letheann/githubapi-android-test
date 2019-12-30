package com.example.githubapitest.model

open class ViewEvents {
    data class SucessGetUsers(val users: List<Users>?) : ViewEvents()
}