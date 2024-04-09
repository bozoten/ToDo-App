package com.lakehead.assignment_4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



data class ToDo (
    val id: String = "",
    val name: String = "",
    val notes: String = "",
    val hasDueDate: Boolean? = null,
    val isCompleted: Boolean? = null,
    val dueDate: String = ""
)



