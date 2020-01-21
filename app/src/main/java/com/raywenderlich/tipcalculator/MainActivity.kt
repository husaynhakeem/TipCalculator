package com.raywenderlich.tipcalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val billAndTipTextWatcher = object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            updateTipAmountAndBillTotal()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        billAmountEditText.addTextChangedListener(billAndTipTextWatcher)
        tipPercentageEditText.addTextChangedListener(billAndTipTextWatcher)
        updateTipAmountAndBillTotal()
    }

    private fun updateTipAmountAndBillTotal() {
        val billAmount = getValue(billAmountEditText)
        val tipPercentage = getValue(tipPercentageEditText)
        val tipAmount = calculateTipAmount(billAmount, tipPercentage)
        val billTotal = calculateBillTotal(billAmount, tipAmount)
        tipAmountTextView.text = getString(R.string.tip_amount, tipAmount)
        billTotalTextView.text = getString(R.string.bill_total, billTotal)
    }

    private fun getValue(editText: EditText): Float {
        val value = editText.text.toString()
        return try {
            value.toFloat()
        } catch (exception: NumberFormatException) {
            0F
        }
    }

    private fun calculateTipAmount(billAmount: Float, tipPercentage: Float): Float {
        return billAmount * tipPercentage / 100
    }

    private fun calculateBillTotal(billAmount: Float, tipAmount: Float): Float {
        return billAmount + tipAmount
    }
}