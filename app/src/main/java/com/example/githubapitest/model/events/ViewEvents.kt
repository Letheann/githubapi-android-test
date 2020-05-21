package com.example.githubapitest.model.events

import com.example.githubapitest.model.users.Repos

open class ViewEvents {
    data class SuccessGetUsers(val users: List<Repos>) : ViewEvents()
    class ErrorRequest : ViewEvents()
    class SavedDataRepo : ViewEvents()
    class CannotSaveDataRepo : ViewEvents()
}