package com.lakehead.assignment_4

import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseApp
import com.lakehead.assignment_4.databinding.TodoCreateBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Filename: ToDoCreateActivity.kt
 * Project: Assignment 4
 * Author's name: Shridhara Pavel Rahul Uma
 * Student Id: 1175516
 * Date: 09-04-2024
 * Description: A fully functional Todo app
 */

class ToDoCreateActivity : AppCompatActivity() {
    private lateinit var binding: TodoCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val updateButton: Button = findViewById(R.id.createButton)
        val cancelButton: Button = findViewById(R.id.cancelButton)

        updateButton.setOnClickListener {
            val name = binding.taskNameEdit.text.toString()
            val notes = binding.taskNotesEdit.text.toString()
            val hasDueDate = binding.switch2.isChecked


            if(binding.switch2.isChecked)
            {
                val dueDate = binding.calendarView.date
                val unixTime = dueDate

                val date = Date(unixTime)

                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate: String = sdf.format(date)
                val todo = ToDo(null, name, notes, hasDueDate, false, formattedDate)
                FirebaseApp.initializeApp(this)


                val firestore = DataManager()

                firestore.addToDo(todo) { isSuccess ->
                    if(isSuccess) {
                        println("Success!")
                    }
                }
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }
            else
            {
                val dueDate = ""

                val todo = ToDo(null, name, notes, hasDueDate, false, dueDate)
                FirebaseApp.initializeApp(this)


                val firestore = DataManager()

                firestore.addToDo(todo) { isSuccess ->
                    if(isSuccess) {
                        println("Success!")
                    }
                }
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }





        }

        cancelButton.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
