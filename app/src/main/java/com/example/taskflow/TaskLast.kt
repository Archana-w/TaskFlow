package com.example.taskflow

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskLast : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_last)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        adapter = TaskAdapter()

        val recyclerViewTasks = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        recyclerViewTasks.adapter = adapter

        viewModel.allTasks.observe(this, Observer { tasks ->
            adapter.submitList(tasks)
        })

        // Handle item click to open task detail
        adapter.setOnItemClickListener { task ->
            val intent = Intent(this, TaskDetail::class.java)
            intent.putExtra(TaskDetail.EXTRA_TASK_ID, task.id)
            startActivity(intent)
        }
    }
}
