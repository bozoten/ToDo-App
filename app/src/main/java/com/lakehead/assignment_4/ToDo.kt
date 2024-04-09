package com.lakehead.assignment_4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



data class ToDo (
    val name: String = "",
    val notes: String = "",
    val id: Long? = null,
    val hasDueDate: Boolean? = null,
    val isCompleted: Boolean? = null,
    val dueDate: String = ""
)



