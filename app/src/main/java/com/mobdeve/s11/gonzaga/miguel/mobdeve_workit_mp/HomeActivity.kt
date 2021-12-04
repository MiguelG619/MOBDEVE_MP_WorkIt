package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    var binding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var firstNameExtra = intent.getStringExtra("firstNameExtra")
        Log.i("Home Activity", " Username from Extra: $firstNameExtra")

        binding!!.tvUsername.text = "Hello, $firstNameExtra"

    }
}