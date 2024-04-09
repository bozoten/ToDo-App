package com.lakehead.assignment_4

import com.google.firebase.firestore.FirebaseFirestore

class DataManager {
    private val db = FirebaseFirestore.getInstance()

    private val collectionRef = db.collection("ToDo")

    fun getToDos(onComplete: (List<ToDo>) -> Unit)
    {
        collectionRef.get()
            .addOnSuccessListener { result ->
                val todos = result.mapNotNull { it.toObject(ToDo)() }
                onComplete(todos)
            }
            .addOnFailureListener{
                onComplete(emptyList())
            }
    }

}