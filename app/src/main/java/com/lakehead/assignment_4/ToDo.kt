package com.lakehead.assignment_4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ToDo {
    @Json(name = "name") val name: String,
    @Json(name = "notes") val notes: String,
    @Json(name = "hasDueDate") val hasDueDate: Boolean,
    @Json(name = "isCompleted") val isCompleted: Boolean = false,
    @Json(name = "dueDate") val dueDate: String
}