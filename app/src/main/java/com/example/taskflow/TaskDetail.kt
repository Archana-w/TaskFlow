package com.example.taskflow

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class TaskDetail : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private var taskId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val editTextTaskName = findViewById<TextInputEditText>(R.id.editTextTaskName)
        // Other EditTexts

        val buttonEditTask = findViewById<Button>(R.id.buttonEditTask)
        val buttonDeleteTask = findViewById<Button>(R.id.buttonDeleteTask)

        taskId = intent.getLongExtra(EXTRA_TASK_ID, 0)

        viewModel.allTasks.observe(this, { tasks ->
            val task = tasks.find { it.id == taskId }
            if (task != null) {
                editTextTaskName.setText(task.name)
                // Set other EditTexts
            }
        })

        buttonEditTask.setOnClickListener {
            val name = editTextTaskName.text.toString()
            // Retrieve other input fields
            val task = Task(taskId, name, "", "", "") // Update task details
            viewModel.updateTask(task)
            Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show()
            finish()
        }

        buttonDeleteTask.setOnClickListener {
            val task = Task(taskId, "", "", "", "") // Create a task with ID to delete
            viewModel.deleteTask(task)
            Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    companion object {
        const val EXTRA_TASK_ID = "extra_task_id"
    }
}
