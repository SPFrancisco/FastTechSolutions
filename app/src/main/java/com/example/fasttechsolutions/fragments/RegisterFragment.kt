package com.example.fasttechsolutions.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.fasttechsolutions.database.TaskDatabase
import com.example.fasttechsolutions.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    lateinit var binding : FragmentRegisterBinding
    private lateinit var db : TaskDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_register, container, false)
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        //viewModel = ViewModelProvider()

        db = Room.databaseBuilder(
            requireNotNull(this.activity).application,
            TaskDatabase::class.java, "users-db"
        ).allowMainThreadQueries()
            .build()

        binding.btnSignUp.setOnClickListener {

        }

        return binding.root
    }
}