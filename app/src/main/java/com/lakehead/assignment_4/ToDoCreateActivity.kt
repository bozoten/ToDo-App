package com.lakehead.assignment_4

import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.lakehead.assignment_4.databinding.TodoCreateBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


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
            val hasDueDate = true
            val isCompleted = binding.switch1.isChecked
            val dueDate = binding.calendarView.date

            val unixTime = dueDate

            val date = Date(unixTime)

            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate: String = sdf.format(date)


            val todo = ToDo(null, name, notes, hasDueDate, isCompleted, formattedDate)
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

        cancelButton.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
