package net.albertogarrido.xpenses

import net.albertogarrido.xpenses.database.ExpenseType

data class ExpenseViewModel(
    val amount: String,
    val description: String,
    val type: ExpenseType
)