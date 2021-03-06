package com.example.githubapitest.model.users

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Permissions(

	@field:SerializedName("pull")
	val pull: Boolean? = null,

	@field:SerializedName("admin")
	val admin: Boolean? = null,

	@field:SerializedName("push")
	val push: Boolean? = null
): Serializable