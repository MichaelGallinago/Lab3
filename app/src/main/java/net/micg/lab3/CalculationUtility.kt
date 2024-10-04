package net.micg.lab3

import java.util.*

object CalculationUtility {
    fun calculate(rawExpression: String): Double? {

        if (rawExpression.isEmpty()) return null

        val expression = if (rawExpression.last() in "+-*/.")
            rawExpression.dropLast(1) else rawExpression

        val numbers = Stack<Double>()
        val operators = Stack<Char>()

        var i = 0
        val valueBuilder = StringBuilder()
        while (i < expression.length) {
            with(expression[i]) {
                if (this.isDigit() || this == '.') {
                    while (i < expression.length && (this.isDigit() || this == '.')) {
                        valueBuilder.append(this)
                        i++
                    }
                    numbers.push(valueBuilder.toString().toDouble())
                    valueBuilder.clear()
                } else if (this in "+-/*") {
                    while (!operators.isEmpty() && hasPrecedence(this, operators.peek())) {
                        numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()))
                    }
                    operators.push(this)
                    i++
                } else {}
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOp(operators.pop(), numbers.pop(), numbers.pop()))
        }

        return numbers.pop()
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean = op1 !in "*/" || op2 !in "+-"

    private fun applyOp(op: Char, b: Double, a: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> {
                if (b == 0.0) throw UnsupportedOperationException("Деление на ноль")
                a / b
            }

            else -> throw UnsupportedOperationException("Неизвестная операция: $op")
        }
    }
}