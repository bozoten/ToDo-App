package com.lakehead.assignment_4

import com.google.firebase.firestore.DocumentId
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Filename: ToDo.kt
 * Project: Assignment 4
 * Author's name: Shridhara Pavel Rahul Uma
 * Student Id: 1175516
 * Date: 09-04-2024
 * Description: A fully functional Todo app
 */

data class ToDo (
    @DocumentId
    val documentId: String? = null,
    val name: String = "",
    val notes: String = "",
    val hasDueDate: Boolean? = null,
    val completed: Boolean? = null,
    val dueDate: String = ""
)



