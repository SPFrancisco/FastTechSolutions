package com.example.fasttechsolutions

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.fasttechsolutions.database.User
import com.example.fasttechsolutions.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    // viewBinding
    private lateinit var binding : ActivityRegisterBinding

    // ActionBar
    private lateinit var actionBar: ActionBar

    // ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    // FirebaseAuth
    private lateinit var firebaseAuth : FirebaseAuth
    private var email = ""
    private var password = ""

    // Database
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configure ActionBar
        actionBar = supportActionBar!!
        actionBar.title = "Register"

        //enable back button
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        // Configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating account...")
        progressDialog.setCanceledOnTouchOutside(false)

        // init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // init database
        database = FirebaseDatabase.getInstance().getReference("Users")

        // Handle click, begin signup
        binding.btnSignUp.setOnClickListener{
            // validate data
            validateData()
        }
    }

    private fun validateData() {
        // get data
        email = binding.registerEmailEdit.text.toString().trim()
        password = binding.registerPasswordEdit.text.toString().trim()

        // validate data
        if(TextUtils.isEmpty(binding.registerNameEdit.text.toString())){
            binding.registerNameEdit.error = "This field is required"
        }
        else if(TextUtils.isEmpty(binding.registerLastNameEdit.text.toString())){
            binding.registerLastNameEdit.error = "This field is required"
        }
        else if(TextUtils.isEmpty(binding.registerPhoneNumberEdit.text.toString())){
            binding.registerPhoneNumberEdit.error = "This field is required"
        }
        else if(TextUtils.isEmpty(binding.registerEmailEdit.text.toString())){
            binding.registerEmailEdit.error = "This field is required"
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                // invalid email format
                binding.registerEmailEdit.error = "Invalid email formal"
            }
        }
        else if(TextUtils.isEmpty(password)){
            // password isn't entered
            binding.registerPasswordEdit.error = "Please enter password"
        }
        else if(password.length < 6){
            // password length is less than 6
            binding.registerPasswordEdit.error = "Password must at least have 6 characters long"
        }
        else if(binding.registerPasswordEdit.text.toString().trim() != binding.registerRePasswordEdit.text.toString().trim()){
            binding.registerRePasswordEdit.error = "Passwords don't match, try again"
        }
        else{
            // data is valid, continue signup
            firebaseRegister()
        }
    }

    private fun firebaseRegister() {
        // show progress
        progressDialog.show()

        // add user to database
        val userId = database.push().key.toString()
        val user = User(userId,
            binding.registerNameEdit.text.toString(),
            binding.registerLastNameEdit.text.toString(),
            binding.registerPhoneNumberEdit.text.toString(),
            binding.registerEmailEdit.text.toString(),
            binding.registerGenderEdit.text.toString(),
            binding.registerDateOfBirthEdit.text.toString(),
            binding.registerCountryEdit.text.toString(),
            binding.registerProvinceEdit.text.toString(),
            binding.registerAddressEdit.text.toString(),
            binding.registerPasswordEdit.text.toString()
        )

        database.child(userId).setValue(user).addOnSuccessListener{

            // create account
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    // signup success
                    progressDialog.dismiss()
                    // get current user
                    val firebaseUser = firebaseAuth.currentUser
                    val email = firebaseUser!!.email
                    Toast.makeText(this,"Account created with email $email", Toast.LENGTH_SHORT).show()

                    // open profile
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                .addOnFailureListener { e->
                    // signup failed
                    progressDialog.dismiss()
                    Toast.makeText(this, "SignUp failed due to ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
            .addOnFailureListener { e->
                // database user add failed
                progressDialog.dismiss()
                Toast.makeText(this, "Database register failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // go back to previous activity, when button of actionbar clicked
        return super.onSupportNavigateUp()
    }
}