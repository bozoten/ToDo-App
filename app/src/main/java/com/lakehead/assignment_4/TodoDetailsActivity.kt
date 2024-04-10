package com.lakehead.assignment_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseApp
import com.lakehead.assignment_4.databinding.TodoDetailsBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Filename: ToDoDetailsActivity.kt
 * Project: Assignment 4
 * Author's name: Shridhara Pavel Rahul Uma
 * Student Id: 1175516
 * Date: 09-04-2024
 * Description: A fully functional Todo app, This file deals with the to do details
 */

class TodoDetailsActivity : AppCompatActivity() {
    private lateinit var binding: TodoDetailsBinding
    private var initialName: String = ""
    private var initialNotes: String = ""
    private var initialHasDueDate: Boolean = false
    private var initialIsCompleted: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todoId = intent.getStringExtra("todo_id")

        if (todoId != null) {
            val firestore = DataManager()
            // Fetches todo details from Firestore
            firestore.getToDo(todoId) { todo ->
                if (todo != null) {
                    // Storing initial todo details
                    initialName = todo.name
                    initialNotes = todo.notes
                    initialHasDueDate = todo.hasDueDate ?: false
                    initialIsCompleted = todo.completed ?: false

                    // Sets initial vals to Ui elements
                    binding.taskNameEdit.setText(initialName)
                    binding.taskNotesEdit.setText(initialNotes)
                    binding.switch1.isChecked = initialIsCompleted
                    binding.switch2.isChecked = initialHasDueDate
                }
            }
        }

        // finds buttons by id and assigns it to var
        val updateButton: Button = findViewById(R.id.button)
        val deleteButton: Button = findViewById(R.id.button2)
        val cancelButton: Button = findViewById(R.id.button3)

        // Click listeners for buttons
        updateButton.setOnClickListener {
            // shows a confirmation dialog for update
            showConfirmationDialog("Update", "Are you sure you want to update?", "Update")
        }

        deleteButton.setOnClickListener {
            showConfirmationDialog("Delete", "Are you sure you want to delete?", "Delete")
        }

        cancelButton.setOnClickListener {
            // Checking for unsaved changes before performing cancel action
            if (checkForUnsavedChanges()) {
                showConfirmationDialog("Cancel", "Are you sure you want to discard changes?", "Cancel")
            } else {
                performCancel()
            }
        }
    }

    // Function to show confirmation dialog
    private fun showConfirmationDialog(title: String, message: String, action: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            // Performs action based on the user selection
            when (action) {
                "Update" -> performUpdate()
                "Delete" -> performDelete()
                "Cancel" -> performCancel()
            }
        }

        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialogBuilder.create().show()
    }

    // Function to perform update action
    private fun performUpdate()
    {
        // Getting updated values from UI elements
        val name = binding.taskNameEdit.text.toString()
        val notes = binding.taskNotesEdit.text.toString()
        val hasDueDate = binding.switch2.isChecked

        val isCompleted = binding.switch1.isChecked

        FirebaseApp.initializeApp(this)
        val bundle = intent.extras
        val todo_id = bundle!!.getString("todo_id")
        val id = todo_id

        if(binding.switch2.isChecked)
        {
            // If the user wants to add a due date it formats it from unix time to normal date format
            val dueDate = binding.calendarView.date
            val unixTime = dueDate

            val date = Date(unixTime)

            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate: String = sdf.format(date)

            if(id!=null)
            {
                // Updating todo in Firestore with due date
                val firestore = DataManager()
                val todo = ToDo(id, name, notes, hasDueDate, isCompleted, formattedDate)
                firestore.updateToDo(todo) { isSuccess ->
                    if(isSuccess) {
                        println("Success!")
                    }
                }
            }
        }
        else
        {
            // if the user chose not to add a due date it updates the db without a duedate
            val dueDate = ""
            if(id!=null)
            {
                val firestore = DataManager()
                val todo = ToDo(id, name, notes, hasDueDate, isCompleted, dueDate)
                firestore.updateToDo(todo) { isSuccess ->
                    if(isSuccess) {
                        println("Success!")
                    }
                }
            }
        }


        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

    private fun performCancel()
    {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun performDelete()
    {
        FirebaseApp.initializeApp(this)

        val bundle = intent.extras
        val todo_id = bundle!!.getString("todo_id")
        val id = todo_id

        if(id!=null)
        {
            val firestore = DataManager()
            firestore.deleteToDo(todo_id) { isSuccess ->
                if(isSuccess) {
                    println("Success!")
                }
            }
        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun checkForUnsavedChanges(): Boolean {
        val currentName = binding.taskNameEdit.text.toString()
        val currentNotes = binding.taskNotesEdit.text.toString()
        val currentHasDueDate = binding.switch2.isChecked
        val currentIsCompleted = binding.switch1.isChecked

        return (currentName != initialName ||
                currentNotes != initialNotes ||
                currentHasDueDate != initialHasDueDate ||
                currentIsCompleted != initialIsCompleted)
    }



}


