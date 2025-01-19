package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressIndicator: CircularProgressIndicator
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupRecyclerView()
        setupSearchView()
        observeUiState()
        observeLoginState()
    }
    private fun setupViews() {
        recyclerView = findViewById(R.id.recipe_recycler_view)
        progressIndicator = findViewById(R.id.progress_indicator)
    }
    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecipeAdapter(
            recipes = emptyList(),
            onItemClick = { recipe ->
                Toast.makeText(this, "Clicked on: ${recipe.name}", Toast.LENGTH_SHORT).show()
            },
            onActionClick = { recipe, action ->
                Toast.makeText(this, "$action on ${recipe.name}", Toast.LENGTH_SHORT).show()
            }
        )
        recyclerView.adapter = adapter
    }
    private fun setupSearchView() {
        val searchView: SearchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true
            override fun onQueryTextChange(newText: String?): Boolean {
                recipeViewModel.queryRecipes(newText.orEmpty())
                return true
            }
        })
    }
    private fun observeUiState() {
        lifecycleScope.launch {
            recipeViewModel.uiState.collectLatest { state ->
                progressIndicator.isVisible = state.isLoading
                recyclerView.isVisible = !state.isLoading
                adapter.updateRecipes(state.recipes)
            }
        }
    }
    private fun observeLoginState() {
        lifecycleScope.launch {
            MyApplication.getInstance().credentialsManager.isLoggedIn.collectLatest { isLoggedIn ->
                if (!isLoggedIn) {
                    startActivity(Intent(this@MainActivity, AuthenticationActivity::class.java))
                    finish()
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                recipeViewModel.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
