package com.lakehead.assignment_4

import com.google.firebase.firestore.DocumentId
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



data class ToDo (
    @DocumentId
    val documentId: String? = null,
    val name: String = "",
    val notes: String = "",
    val hasDueDate: Boolean? = null,
    val completed: Boolean? = null,
    val dueDate: String = ""
)



