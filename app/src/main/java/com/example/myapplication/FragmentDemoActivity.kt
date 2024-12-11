package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityFragmentsBinding

class FragmentDemoActivity :
    AppCompatActivity(R.layout.activity_fragments),
    FragmentA.EventListener,
    FragmentB.EventListener {
    private lateinit var binding: ActivityFragmentsBinding
    private var showingFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null)
            loadFragmentView(FragmentA())

        binding.fragmentToggleButton.setOnClickListener {
            if (showingFragmentA)
                loadFragmentView(FragmentB())
            else
                loadFragmentView(FragmentA())

            showingFragmentA = !showingFragmentA
        }
    }

    private fun loadFragmentView(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }

    override fun onGoToBPressed() {
        Log.d("Preview", "onGoToBPressed in activity")
    }

    override fun onGoToAPressed() {
        Log.d("Preview", "onGoToAPressed in activity")
    }
}