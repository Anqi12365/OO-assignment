package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // 使用 lateinit 替代可空类型
    private lateinit var inputFirstNumber: EditText
    private lateinit var inputSecondNumber: EditText
    private lateinit var buttonPlus: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        inputFirstNumber = findViewById(R.id.inputFirstNumber)
        inputSecondNumber = findViewById(R.id.inputSecondNumber)
        buttonPlus = findViewById(R.id.buttonPlus)
        buttonMinus = findViewById(R.id.buttonMinus)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        textResult = findViewById(R.id.textResult)

        // Set click listeners
        buttonPlus.setOnClickListener { calculate('+') }
        buttonMinus.setOnClickListener { calculate('-') }
        buttonMultiply.setOnClickListener { calculate('*') }
        buttonDivide.setOnClickListener { calculate('/') }
    }

    private fun calculate(operator: Char) {
        val firstStr = inputFirstNumber.text.toString().trim()
        val secondStr = inputSecondNumber.text.toString().trim()

        if (firstStr.isEmpty() || secondStr.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val first = firstStr.toDouble()
            val second = secondStr.toDouble()
            val result = when (operator) {
                '+' -> first + second
                '-' -> first - second
                '*' -> first * second
                '/' -> {
                    if (second == 0.0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        return
                    }
                    first / second
                }
                else -> 0.0
            }

            textResult.text = if (result % 1 == 0.0) {
                result.toInt().toString()
            } else {
                String.format("%.2f", result)
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
        }
    }
}