package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        supportActionBar?.hide()

        setContentView(binding.root)

        binding.appCompatImageView.alpha = 0f
        binding.tvName.alpha = 0f

        binding.appCompatImageView.animate().setDuration(1500).alpha(1f)
        binding.tvName.animate().setDuration(2500).alpha(1f).withEndAction {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}