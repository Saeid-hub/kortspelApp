package com.example.kortspelapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private var score = 0
    private var currentCard = Random.nextInt(1, 14) // Slumpmässigt kort mellan 1 och 13

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val cardImage = findViewById<ImageView>(R.id.cardImage)
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val higherButton = findViewById<Button>(R.id.higherButton)
        val lowerButton = findViewById<Button>(R.id.lowerButton)

        updateCardImage(cardImage, currentCard)

        // Hantera klick för Higher-knappen
        higherButton.setOnClickListener {
            val isCorrect = checkGuess(true) // Kontrollera om gissningen var korrekt
            updateUI(cardImage, scoreText) // Uppdatera kortbild och poängtext
            showResultPopup(isCorrect) // Visa popup baserat på resultat
        }

        // Hantera klick för Lower-knappen
        lowerButton.setOnClickListener {
            val isCorrect = checkGuess(false) // Kontrollera om gissningen var korrekt
            updateUI(cardImage, scoreText) // Uppdatera kortbild och poängtext
            showResultPopup(isCorrect) // Visa popup baserat på resultat
        }
    }

    // Kontrollera om gissningen var korrekt och returnera resultatet
    private fun checkGuess(isHigher: Boolean): Boolean {
        val newCard = Random.nextInt(1, 14) // Dra ett nytt kort
        val isCorrect = (isHigher && newCard > currentCard) || (!isHigher && newCard < currentCard) // Kontrollera korrekthet

        if (isCorrect) {
            score++ // Öka poängen om korrekt
        }

        currentCard = newCard // Uppdatera currentCard med det nya kortet
        return isCorrect
    }

    // Uppdatera kortbild och poängtext
    private fun updateUI(cardImage: ImageView, scoreText: TextView) {
        scoreText.text = "Poäng: $score"
        updateCardImage(cardImage, currentCard)

        if (score >= 10) { // Om poäng når 10, gå till GameOverActivity
            val intent = Intent(this, GameOverActivity::class.java)
            intent.putExtra("finalScore", score)
            startActivity(intent)
            finish()
        }
    }

    // Visa en popup som berättar om gissningen var korrekt eller fel
    private fun showResultPopup(isCorrect: Boolean) {
        val message = if (isCorrect) {
            "Korrekt gissning!" // Om korrekt
        } else {
            "Fel gissning!" // Om fel
        }

        // Skapa och visa en AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Resultat")
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss() // Stäng dialogen
        }
        val dialog = builder.create()
        dialog.show()
    }

    // Uppdatera kortets bild
    private fun updateCardImage(cardImage: ImageView, cardValue: Int) {
        val cardResId = resources.getIdentifier("card_$cardValue", "drawable", packageName)
        cardImage.setImageResource(R.drawable.seven_card)
    }
}

