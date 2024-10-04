package net.micg.lab3

import android.util.Log
import java.lang.Exception
import net.objecthunter.exp4j.ExpressionBuilder

object CalculationUtility {
    fun calculate(rawExpression: String): String {

        if (rawExpression.isEmpty()) return "0"

        val validatedExpression = if (rawExpression.last() in "+-*/.")
            rawExpression.dropLast(1) else rawExpression

        try {
            val expression = ExpressionBuilder(validatedExpression).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            return if (result == longResult.toDouble()) longResult.toString() else result.toString()
        } catch (e: Exception) {
            Log.d("EXCEPTION", "Message: ${e.message}")
        }

        return "0"
    }
}