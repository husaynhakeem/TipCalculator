/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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