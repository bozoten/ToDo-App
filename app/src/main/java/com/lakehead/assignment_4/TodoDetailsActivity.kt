package com.lakehead.assignment_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lakehead.assignment_4.databinding.ActivityMainBinding
import com.lakehead.assignment_4.databinding.TodoDetailsBinding

class TodoDetailsActivity : AppCompatActivity() {
    private lateinit var binding: TodoDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_details)
    }
}