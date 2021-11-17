package com.example.fasttechsolutions.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fasttechsolutions.adapters.TaskClickDeteleInterface
import com.example.fasttechsolutions.adapters.TaskClickInterface
import com.example.fasttechsolutions.database.Task
import com.example.fasttechsolutions.databinding.FragmentTaskBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskFragment : Fragment(), TaskClickInterface, TaskClickDeteleInterface {

    lateinit var binding: FragmentTaskBinding
    lateinit var taskRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    //lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentTaskBinding.inflate(layoutInflater, container, false)

        /*val taskAdapter = TaskAdapter(this, this, this)
        taskRV.adapter = taskAdapter
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireNotNull(this.activity).application)).get(TaskViewModel::class.java)
        viewModel.allTask.observe(this, { list ->
            list?.let {
                taskAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            val intent = Intent(this@TaskFragment,)
        }*/

        return binding.root
    }

    override fun onTaskClick(task: Task) {
        TODO("Not yet implemented")
    }

    override fun onDeleteIconClick(task: Task) {
        TODO("Not yet implemented")
    }
}