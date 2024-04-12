package com.example.practice_7

import android.annotation.SuppressLint
import android.os.Bundle

import android.app.Activity
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var editText: EditText
    private lateinit var slider: SeekBar
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_text)
        slider = findViewById(R.id.slider)
        textView = findViewById(R.id.text_view)

        editText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateTax()
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateTax()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    @SuppressLint("SetTextI18n")
    private fun updateTax() {
        val billAmountString = editText.text.toString()
        val taxPercentage = slider.progress
        if (billAmountString.isNotEmpty()) {
            val billAmount = billAmountString.toDouble()
            val taxAmount = billAmount * taxPercentage / 100.0
            textView.text = "Bill value: %.2f$ tip percentage: %d%% Tip amount: %.2f$".format(billAmount, taxPercentage, taxAmount)
        } else {
            textView.text = "Enter the invoice value and select a percentage."
        }
    }
}