package net.albertogarrido.xpenses.database

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.Instant
import java.lang.IllegalArgumentException

class InstantTypeConverter {
    @TypeConverter
    fun toInstant(value: Long) = Instant.ofEpochMilli(value)

    @TypeConverter
    fun toLong(value: Instant) = value.toEpochMilli()
}

class ExpenseTypeConverter {
    @TypeConverter
    fun toType(value: Int) =
        when (value) {
            1 -> ExpenseType.INCOME
            2 -> ExpenseType.EXPENSE
            else -> throw IllegalArgumentException("only values 1 and 2") // todo
        }

    @TypeConverter
    fun toInt(value: ExpenseType) =
        when (value) {
            ExpenseType.INCOME -> 1
            ExpenseType.EXPENSE -> 2
        }
}