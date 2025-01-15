package com.example.myapplication

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recipe_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecipeAdapter(
            recipes = emptyList(),
            onItemClick = { recipe ->
                Toast.makeText(this, "Clicked on: ${recipe.name}", Toast.LENGTH_SHORT).show()
            },
            onActionClick = { recipe, action ->
                Toast.makeText(this, "$action on ${recipe.name}", Toast.LENGTH_SHORT).show()
            }
        )
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            recipeViewModel.recipes.collectLatest { updatedRecipes ->
                adapter.updateRecipes(updatedRecipes)
            }
        }

        val searchView: SearchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipeViewModel.queryRecipes(newText.orEmpty())
                return true
            }

        })
    }
}