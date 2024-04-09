package com.lakehead.assignment_4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class ToDo (
    val name: String = "",
    val notes: String = "",
    val hasDueDate: Boolean = true,
    val isCompleted: Boolean = false,
    val dueDate: String = ""
)

