package com.lakehead.assignment_4

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.util.Log;
import android.widget.Button
import androidx.core.content.ContextCompat
import kotlin.math.log


class ToDoAdapter(private val todos: List<ToDo>) :
   RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

   @SuppressLint("UseSwitchCompatOrMaterialCode")
   inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val nameTextView: TextView = itemView.findViewById(R.id.taskName)
      val dateTextView: TextView = itemView.findViewById(R.id.date)
      val completedSwitch: Switch = itemView.findViewById(R.id.switch_1)
      val editButton: Button = itemView.findViewById(R.id.Edit)

      init{
         editButton.setOnClickListener{
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
               val intent = Intent(itemView.context, TodoDetailsActivity::class.java)
               intent.putExtra("todo_id", todos[position].documentId.toString())
               Log.i("ID", todos[position].documentId.toString())
               itemView.context.startActivity(intent)
            }
         }

         completedSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
               itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.completedColor))
            } else {
               itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.backgroundColor))
            }


         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
      val view = LayoutInflater.from(parent.context)
         .inflate(R.layout.text_row_item, parent, false)
      return ToDoViewHolder(view)
   }

   override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
      val todo = todos[position]
      holder.nameTextView.text = todo.name
      holder.dateTextView.text = todo.dueDate

      holder.completedSwitch.isChecked = todo.completed == true

      Log.d("TAG", todo.completed.toString())
   }

   override fun getItemCount(): Int {
      return todos.size
   }


}


