package com.lakehead.assignment_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.lakehead.assignment_4.databinding.TodoDetailsBinding

class TodoDetailsActivity : AppCompatActivity() {
    private lateinit var binding: TodoDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val updateButton: Button = findViewById(R.id.button)
        updateButton.setOnClickListener {
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
                firestore.updateToDo(todo_id, todo) { isSuccess ->
                    if(isSuccess) {
                        println("Success!")
                    }
                }
            }

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}


