package com.lakehead.assignment_4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.lakehead.assignment_4.databinding.ActivityMainBinding
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
            val id = idCreator().toLong()
            val isCompleted = binding.switch1.isChecked
            val dueDate = ""
            val todo = ToDo(name, notes, id, hasDueDate, isCompleted, dueDate)
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
}


