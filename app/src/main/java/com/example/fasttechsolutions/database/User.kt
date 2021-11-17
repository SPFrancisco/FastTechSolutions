package com.example.fasttechsolutions.database

data class User(
    val id : String?= null,
    val name : String?= null,
    val lastName : String?= null,
    val phoneNumber : String?= null,
    val email : String?= null,
    val gender : String?= null,
    val dateOfBirth : String?= null,
    val country : String?= null,
    val province : String?= null,
    val address : String?= null,
    val password : String?= null
)
