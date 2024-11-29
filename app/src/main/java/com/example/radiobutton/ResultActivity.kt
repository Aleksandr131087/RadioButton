package com.example.radiobutton

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result2)

        val score = intent.getIntExtra("score", 0)
        findViewById<TextView>(R.id.scoreTextView).text = "Ваши баллы: $score"

        val description = when {
            score == 500 -> "Отличный знания в истории!"
            score >= 400 -> "Хороший результат."
            score >= 300 -> "Удовлетворительный уровень знаний."
            score >= 200 -> "Неплохой уровень знаний, но есть над чем работать."
            else -> "Плохо! Попробуйте еще раз!"
        }
        findViewById<TextView>(R.id.scoreTextView2).text = description
    }
}