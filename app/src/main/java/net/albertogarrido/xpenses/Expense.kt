package net.albertogarrido.xpenses

data class Expense (
    val amountCents: Int,
    val description: String,
    val type: ExpenseType
)

enum class ExpenseType {
    EXPENSE, INCOME
}
