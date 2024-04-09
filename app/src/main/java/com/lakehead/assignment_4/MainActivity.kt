package com.lakehead.assignment_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.FirebaseApp
import com.lakehead.assignment_4.databinding.ActivityMainBinding

/**
 * Filename: MainActivity.kt
 * Project: Assignment 4
 * Author's name: Shridhara Pavel Rahul Uma
 * Student Id: 1175516
 * Date: 24-03-2024
 * Description: Backend
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        val firestore = DataManager()
        firestore.getToDos { toDos ->
            for(toDo in toDos)
            {
                val toDoAdapter = ToDoAdapter(toDos)
                // println(toDo.name)
                //binding.FirstRecyclerView(toDos)
                binding.FirstRecyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = toDoAdapter
                }
            }
        }

    }
}

