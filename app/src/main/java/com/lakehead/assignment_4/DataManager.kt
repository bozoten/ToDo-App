package com.lakehead.assignment_4

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class DataManager {
    private val db = FirebaseFirestore.getInstance()

    private val collectionRef = db.collection("ToDo")

    fun getToDos(onComplete: (List<ToDo>) -> Unit)
    {
        collectionRef.get()
            .addOnSuccessListener { result ->
                val todos = result.mapNotNull { it.toObject<ToDo>() }
                onComplete(todos)
            }
            .addOnFailureListener{
                onComplete(emptyList())
            }
    }

    fun addToDo(toDo: ToDo, onComplete: (Boolean)-> Unit) {
        collectionRef.add(toDo)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun updateToDo(toDoId: String, updatedToDo: ToDo, onComplete: (Boolean) -> Unit) {
        val documentRef = collectionRef.document(toDoId)
        documentRef.update(toDoId, updatedToDo)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

}