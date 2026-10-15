package com.example.vangtichai

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var amount = ""
    private val denominations = intArrayOf(500, 100, 50, 20, 10, 5, 2, 1)
    private lateinit var amountView: TextView
    private val noteViews = mutableMapOf<Int, TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountView = findViewById(R.id.amount_value)
        denominations.forEach { denomination ->
            noteViews[denomination] = findViewById(resources.getIdentifier("note_$denomination", "id", packageName))
        }

        amount = savedInstanceState?.getString(STATE_AMOUNT).orEmpty()
        if (amount.length > MAX_DIGITS) amount = amount.takeLast(MAX_DIGITS)
        updateDisplay()

        val digitIds = intArrayOf(
            R.id.key_1, R.id.key_2, R.id.key_3,
            R.id.key_4, R.id.key_5, R.id.key_6,
            R.id.key_7, R.id.key_8, R.id.key_9, R.id.key_0
        )
        digitIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { view ->
                val digit = (view as Button).text.toString()
                if (amount.length < MAX_DIGITS) {
                    amount = (amount + digit).trimStart('0')
                    updateDisplay()
                }
            }
        }
        findViewById<Button>(R.id.key_clear).setOnClickListener {
            amount = ""
            updateDisplay()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_AMOUNT, amount)
        super.onSaveInstanceState(outState)
    }

    private fun updateDisplay() {
        val numericAmount = amount.toLongOrNull() ?: 0L
        amountView.text = if (amount.isEmpty()) getString(R.string.zero) else amount
        var remaining = numericAmount
        denominations.forEach { denomination ->
            val count = remaining / denomination
            remaining %= denomination
            noteViews[denomination]?.text = "$denomination: $count"
        }
    }

    companion object {
        private const val STATE_AMOUNT = "current_amount"
        private const val MAX_DIGITS = 12
    }
}
