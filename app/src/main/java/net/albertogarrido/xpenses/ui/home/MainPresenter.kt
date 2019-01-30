package net.albertogarrido.xpenses.ui.home

import android.content.Context
import android.util.Log
import net.albertogarrido.xpenses.database.Expense
import net.albertogarrido.xpenses.database.ExpenseType
import net.albertogarrido.xpenses.database.ExpensesDatabase
import org.threeten.bp.Instant
import kotlin.concurrent.thread

class MainPresenter private constructor(private val view: MainView) {
    interface MainView {
        fun getContex(): Context
    }

    companion object {
        fun createWith(view: MainView) = MainPresenter(view)
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

    // todo this is just for test until a main activity ui is created
    fun debugPrintAll() {
        db?.apply {
            thread {
                val listResult = expenseDao().getAll()
                listResult.forEach {
                    Log.e("xpenses_debug", "${it.id} - $it")
                }
            }
        }
    }

}
