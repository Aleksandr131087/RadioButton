package com.example.radiobutton

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private var score = 0

    private val questions = arrayOf(
        Question("Кто был первым президентом России?", "Борис Ельцин", "Владимир Путин", "Михаил Горбачев", 0),
        Question("Когда была подписана Конституция РФ?", "1993", "1991", "2000", 1),
        Question("Когда началась ВОВ?", "1939", "1941", "1812", 1),
        Question("Когда закончилась ВОВ?", "1939", "1941", "1945", 2),
        Question("Когда произошло крещение Руси?", "988", "342", "1412", 0),
    )
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        score = intent.getIntExtra("score", 0)
        loadQuestion()

        val nextButton: Button = findViewById(R.id.nextButton)
        nextButton.setOnClickListener {
            val selectedId = findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId
            val answerIndex = when (selectedId) {
                R.id.radioButton1 -> 0
                R.id.radioButton2 -> 1
                R.id.radioButton3 -> 2
                else -> -1
            }

            if (answerIndex == questions[currentQuestionIndex].correctAnswerIndex) {
                score += 100
            }
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                loadQuestion()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        val question = questions[currentQuestionIndex]
        findViewById<TextView>(R.id.questionTextView).text = question.text
        findViewById<RadioButton>(R.id.radioButton1).text = question.answers[0]
        findViewById<RadioButton>(R.id.radioButton2).text = question.answers[1]
        findViewById<RadioButton>(R.id.radioButton3).text = question.answers[2]
    }
}
data class Question(val text: String, val answer1: String, val answer2: String, val answer3: String, val correctAnswerIndex: Int) {
    val answers = arrayOf(answer1, answer2, answer3)
}