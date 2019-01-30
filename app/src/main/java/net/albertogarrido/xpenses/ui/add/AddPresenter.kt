package net.albertogarrido.xpenses.ui.add

import android.content.Context
import net.albertogarrido.xpenses.database.Expense
import net.albertogarrido.xpenses.ExpenseViewModel
import net.albertogarrido.xpenses.database.ExpensesDatabase
import kotlin.concurrent.thread

class AddPresenter private constructor(private val view: AddView) {

    interface AddView {
        fun collectData(): ExpenseViewModel
        fun getContex(): Context
    }

    companion object {
        fun createWith(view: AddView) =
            AddPresenter(view)
    }

    var db: ExpensesDatabase? = null

    fun resume() {
        db = ExpensesDatabase.getInstance(view.getContex())
    }

    fun pause() {
        ExpensesDatabase.destroyInstance()
    }

    fun destroy() {
        //todo cleanup destroy step
    }

    fun hasChanged(): Boolean {
        //todo
        return true
    }

    fun save(callback: () -> Unit) {
        val data = mapData()
        db?.apply {
            thread {
                expenseDao().insert(data)
                callback()
            }
        }
    }

    private fun mapData(): Expense = Expense.fromViewModel(view.collectData())
}