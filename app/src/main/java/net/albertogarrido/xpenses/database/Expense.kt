package net.albertogarrido.xpenses.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import net.albertogarrido.xpenses.ExpenseViewModel
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

@Entity
data class Expense(
    val amountCents: Int,
    val description: String,
    val instant: Instant,
    val type: ExpenseType
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0

    companion object {
        fun fromViewModel(data: ExpenseViewModel): Expense {
            return Expense(
                (data.amount.toFloat() * 100).toInt(), // fixme this is ugly, review
                data.description,
                Instant.now(),
                data.type
            )
        }
    }
    fun getLocalTime() = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault())

}

enum class ExpenseType {
    EXPENSE, INCOME
}
