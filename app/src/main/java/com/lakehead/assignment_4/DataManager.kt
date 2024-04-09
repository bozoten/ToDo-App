package com.lakehead.assignment_4

import android.util.Log
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

    fun getToDo(todoId: String, callback: (ToDo?) -> Unit) {
        collectionRef.document(todoId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val todo = documentSnapshot.toObject(ToDo::class.java)
                    callback(todo)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener { exception ->
                callback(null)
            }
    }

    fun addToDo(toDo: ToDo, onComplete: (Boolean)-> Unit) {
        collectionRef.add(toDo)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun updateToDo(updatedToDo: ToDo, onComplete: (Boolean) -> Unit) {
        Log.i("Update", updatedToDo.toString())
        updatedToDo.documentId?.let{ collectionRef.document(it).update("name", updatedToDo.name, "completed", updatedToDo.completed, "notes", updatedToDo.notes, "hasDueDate", updatedToDo.hasDueDate, "dueDate", updatedToDo.dueDate) }
        onComplete(true)
    }

    fun deleteToDo(toDoId: String, onComplete: (Boolean) -> Unit) {
        val documentRef = collectionRef.document(toDoId)
        documentRef.delete()
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }


}