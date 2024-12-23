package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailsActivity : AppCompatActivity(R.layout.activity_recipe_details) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipeName = intent.getStringExtra("name") ?: ""
        val recipeNameTextView = findViewById<TextView>(R.id.recipe_name)
        recipeNameTextView.text = recipeName

        val recipeImageRes = intent.getIntExtra("imageId", R.drawable.cibo_default)
        val recipeImageView = findViewById<ImageView>(R.id.recipe_image)
        recipeImageView.setImageResource(recipeImageRes)

        val ingredients = intent.getStringArrayListExtra("ingredients") ?: listOf()
        val ingredientsListView = findViewById<ListView>(R.id.recipe_ingredients)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredients)
        ingredientsListView.adapter = adapter
    }
}