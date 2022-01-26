package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityReminderBinding
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivitySampleBinding

class SampleActivity : AppCompatActivity() {

    lateinit var binding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        Navbar(findViewById(R.id.bottom_navigation), this, R.id.nav_reminder)

        binding.btnYes.setOnClickListener {
            Toast.makeText(this, "fdss!", Toast.LENGTH_SHORT).show()
        }
    }
}