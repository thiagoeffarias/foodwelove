package com.compose.welovefood

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.compose.welovefood.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
    }

    private fun setupButtons() {
        binding.startButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}