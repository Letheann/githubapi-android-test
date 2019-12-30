package com.example.githubapitest.model

open class ViewEvents {
    data class SuccessGetUsers(val users: List<Users>?) : ViewEvents()
}