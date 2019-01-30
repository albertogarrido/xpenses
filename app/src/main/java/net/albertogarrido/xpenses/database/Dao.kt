package net.albertogarrido.xpenses.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense")
    fun getAll(): List<Expense>

    @Insert
    fun insertAll(vararg expenses: Expense)

    @Insert
    fun insert(expense: Expense)

    @Delete
    fun delete(expense: Expense)
}