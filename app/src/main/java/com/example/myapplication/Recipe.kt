package com.example.myapplication

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val imageId: Int,
    val ingredients: ArrayList<String>
)
