package com.example.myapplication

object RecipeRepository {
    val recipes = listOf(
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

}