package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val recipes = listOf(
        Recipe(
            1,
            "Spaghetti alla carbonara",
            "Gli spaghetti migliori al mondo",
            R.drawable.carbonara,
            arrayListOf("Spaghetti", "Guanciale", "Pecorino", "Pepe nero", "Uova")
        ),
        Recipe(
            2,
            "Spaghetti all'assassina",
            "Gli spaghetti di Bari migliori al mondo",
            R.drawable.assassina,
            arrayListOf("Spaghetti", "Salsa di pomodoro", "Peperoncino", "Stracciatella")
        ),
        Recipe(
            3,
            "Pizza margherita",
            "La pizza migliore al mondo",
            R.drawable.margherita,
            arrayListOf(
                "Farina",
                "Acqua",
                "Lievito di birra",
                "Olio extravergine d'oliva",
                "Sale fino",
                "Pomodori pelati",
                "Grana padano DOP",
                "Basilico",
                "Mozzarella"
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recipe_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecipeAdapter(
            recipes = recipes,
            onItemClick = { recipe ->
                Toast.makeText(this, "Clicked on: ${recipe.name}", Toast.LENGTH_SHORT).show()
            },
            onActionClick = { recipe, action ->
                Toast.makeText(this, "$action on ${recipe.name}", Toast.LENGTH_SHORT).show()
            }
        )
        recyclerView.adapter = adapter
    }
}