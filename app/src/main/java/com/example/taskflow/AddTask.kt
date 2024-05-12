package com.example.taskflow

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class AddTask : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val editTextTaskName = findViewById<TextInputEditText>(R.id.editTextTaskName)
        // Other EditTexts

        val buttonAddTask = findViewById<Button>(R.id.buttonAddTask)
        buttonAddTask.setOnClickListener {
            val name = editTextTaskName.text.toString()
            // Retrieve other input fields
            val task = Task(name = name, description = "", priority = "", deadline = "")
            viewModel.insertTask(task)
            finish()
        }
    }
}
