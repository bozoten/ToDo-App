package com.lakehead.assignment_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.lakehead.assignment_4.databinding.TodoDetailsBinding

class TodoDetailsActivity : AppCompatActivity() {
    private lateinit var binding: TodoDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todoId = intent.getStringExtra("todo_id")

        if (todoId != null) {
            val firestore = DataManager()
            firestore.getToDo(todoId) { todo ->
                if (todo != null) {
                    binding.taskNameEdit.setText(todo.name)
                    binding.taskNotesEdit.setText(todo.notes)
                    binding.switch1.isChecked = todo.completed == true
                }
            }
        }

        val updateButton: Button = findViewById(R.id.button)
        val deleteButton: Button = findViewById(R.id.button2)
        val cancelButton: Button = findViewById(R.id.button3)

        updateButton.setOnClickListener {
            showConfirmationDialog("Update", "Are you sure you want to update?", "Update")
        }

        deleteButton.setOnClickListener {
            showConfirmationDialog("Delete", "Are you sure you want to delete?", "Delete")
        }

        cancelButton.setOnClickListener {
            showConfirmationDialog("Cancel", "Are you sure you want to cancel?", "Cancel")
        }
    }

    private fun showConfirmationDialog(title: String, message: String, action: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
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

    private fun performUpdate()
    {
        val name = binding.taskNameEdit.text.toString()
        val notes = binding.taskNotesEdit.text.toString()
        val hasDueDate = true

        val isCompleted = binding.switch1.isChecked
        val dueDate = ""

        FirebaseApp.initializeApp(this)
        val bundle = intent.extras
        val todo_id = bundle!!.getString("todo_id")
        val id = todo_id

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





}


