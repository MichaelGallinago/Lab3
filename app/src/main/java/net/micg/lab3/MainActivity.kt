package net.micg.lab3

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var input = StringBuilder()
    private var lastOperator: Char? = null
    private var isNewOperation = false
    private var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpEdgeToEdge()

        setUpValueButtonsListeners()
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

    private fun setUpValueButtonsListeners() {
        val valueButtons = listOf(R.id.button0)
        for (id in valueButtons.indices) {
            with(findViewById<Button>(id)) {
                setOnClickListener {
                    valueButtonClickListener(this.text.toString())
                }
            }
        }
    }

    private fun valueButtonClickListener(value: String) {

    }
}