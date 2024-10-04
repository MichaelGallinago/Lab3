package net.micg.lab3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val expression = StringBuilder()
    //private val regex = Regex("-?\\d+(\\.\\d+)?[+\\-*/]-?\\d+(\\.\\d+)?(\\s*[+\\-*/]-?\\d+(\\.\\d+)?)*")
    private val regex = Regex("-?\\d+(\\.\\d+)?(\\s*[+\\-*/]\\s*-?\\d+(\\.\\d+)?)*")
    private lateinit var display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpEdgeToEdge()

        display = findViewById(R.id.display)
        setUpSymbolButtonsListeners()
        setUpActionButtonsListeners()
    }

    private fun setUpEdgeToEdge() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setUpSymbolButtonsListeners() {
        listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonDot,
            R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonMinus, R.id.buttonPlus
        ).forEach {
            with(findViewById<Button>(it)) {
                setOnClickListener {
                    symbolButtonClickListener(this.text.toString())
                }
            }
        }
    }

    private fun symbolButtonClickListener(value: String) {
        expression.append(value)

        val expressionToCheck = if (value in "+-*/.")
            expression.dropLast(1) else expression

        if (expressionToCheck.matches(regex)) {
            display.text = expression.toString()
            return
        }
        println(expression.toString())
        removeLastChar()
    }

    private fun setUpActionButtonsListeners() {
        findViewById<Button>(R.id.buttonEquals).setOnClickListener {
            with(CalculationUtility.calculate(expression.toString()))
            {
                display.text = expression.clear().append(this).toString()
            }
        }

        findViewById<Button>(R.id.buttonBackspace).setOnClickListener {
            removeLastChar()
        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            expression.clear()
            display.text = "0"
        }
    }

    private fun removeLastChar() {
        if (expression.isEmpty()) return
        expression.deleteCharAt(expression.length - 1)
        display.text = if (expression.isEmpty()) "0" else expression.toString()
    }
}
