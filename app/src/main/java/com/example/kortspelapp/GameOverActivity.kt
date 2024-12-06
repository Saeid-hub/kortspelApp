package com.example.kortspelapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_over)

        val scoreText = findViewById<TextView>(R.id.scoreText)
        val restartButton = findViewById<Button>(R.id.restartButton)
        val exitButton: Button = findViewById(R.id.exitButton)

        val finalScore = intent.getIntExtra("finalScore", 0)
        scoreText.text = "Din poäng: $finalScore"

        restartButton.setOnClickListener{
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        exitButton.setOnClickListener {
            finishAffinity() // Stänger alla aktiviteter och avslutar appen
        }
    }
}