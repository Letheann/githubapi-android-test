package com.example.githubapitest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Search(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<Repos>? = null
): Serializable