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
               val intent = Intent(itemView.context, TodoDetailsActivity::class.java)
               itemView.context.startActivity(intent)
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
      holder.completedSwitch.isChecked = todo.isCompleted
   }

   override fun getItemCount(): Int {
      return todos.size
   }


}