package net.albertogarrido.xpenses.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

@Database(entities = [Expense::class], version = 1)
@TypeConverters(InstantTypeConverter::class, ExpenseTypeConverter::class)
abstract class ExpensesDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        private var INSTANCE: ExpensesDatabase? = null

        fun getInstance(context: Context): ExpensesDatabase? {
            if (INSTANCE == null) {
                synchronized(ExpensesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ExpensesDatabase::class.java,
                        "expenses.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}


