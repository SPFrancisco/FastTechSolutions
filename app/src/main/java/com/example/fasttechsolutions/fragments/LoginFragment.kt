package com.example.fasttechsolutions.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.fasttechsolutions.database.TaskDatabase
import com.example.fasttechsolutions.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding : FragmentLoginBinding

    private lateinit var db : TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false)
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.btnLogin.setOnClickListener{

        }

        db = Room.databaseBuilder(
            requireNotNull(this.activity).application,
            //applicationContext,
            TaskDatabase::class.java, "users-db"
        ).allowMainThreadQueries()
            .build()

        binding.btnLogin.setOnClickListener{

        }

        /*viewModel.getUser().observe(this, {
            binding.emailEdit.setText(it)
        })*/

        return binding.root
    }
}