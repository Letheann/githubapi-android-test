package com.example.githubapitest.model

import java.io.Serializable

data class FilterParameters(
    var sort: String? = null,
    var order: String? = "desc"
) : Serializable