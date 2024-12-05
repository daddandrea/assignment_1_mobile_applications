package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityFragmentsBinding

class FragmentDemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentsBinding
    private var showingFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FragmentA())
                .commit()
        }

        binding.fragmentToggleButton.setOnClickListener {
            if (showingFragmentA) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, FragmentB())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, FragmentA())
                    .commit()
            }
            showingFragmentA = !showingFragmentA
        }
    }
}