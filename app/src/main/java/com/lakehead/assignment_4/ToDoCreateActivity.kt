package com.lakehead.assignment_4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.lakehead.assignment_4.databinding.TodoCreateBinding


class ToDoCreateActivity : AppCompatActivity() {
    private lateinit var binding: TodoCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val updateButton: Button = findViewById(R.id.button)
        updateButton.setOnClickListener {
            val name = binding.taskNameEdit.text.toString()
            val notes = binding.taskNotesEdit.text.toString()
            val hasDueDate = true
            val isCompleted = binding.switch1.isChecked
            var dueDate = ""

            binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                val formattedDate = "$year-${month + 1}-$dayOfMonth"
                dueDate = formattedDate
            }


            val todo = ToDo(name, notes, hasDueDate, isCompleted, dueDate)
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
