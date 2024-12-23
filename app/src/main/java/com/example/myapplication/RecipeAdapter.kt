package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit,
    private val onActionClick: (Recipe, String) -> Unit,
    private val likedItems: MutableSet<Int> = mutableSetOf<Int>()
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipe_image)
        private val recipeName: TextView = itemView.findViewById(R.id.recipe_name)
        private val recipeDescription: TextView = itemView.findViewById(R.id.recipe_description)
        private val shareButton: ImageButton = itemView.findViewById(R.id.share_button)
        private val likeButton: ImageButton = itemView.findViewById(R.id.like_button)

        fun bind(recipe: Recipe) {
            recipeName.text = recipe.name
            recipeDescription.text = recipe.description
            recipeImage.setImageResource(recipe.imageId)

            itemView.setOnClickListener {
                onItemClick(recipe)
                val intent = Intent(itemView.context, RecipeDetailsActivity::class.java)
                intent.putExtra("name", recipe.name)
                intent.putExtra("imageId", recipe.imageId)
                intent.putStringArrayListExtra("ingredients", recipe.ingredients)
                itemView.context.startActivity(intent)
            }
            shareButton.setOnClickListener { onActionClick(recipe, "Share") }
            likeButton.setOnClickListener {
                if (likedItems.contains(recipe.id)) {
                    likedItems.remove(recipe.id)
                    likeButton.setImageResource(R.drawable.ic_like)
                    onActionClick(recipe, "Unlike")
                } else {
                    likedItems.add(recipe.id)
                    likeButton.setImageResource(R.drawable.ic_like_pressed)
                    onActionClick(recipe, "Like")
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeAdapter.RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

}
