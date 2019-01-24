package net.albertogarrido.xpenses

class AddPresenter private constructor(private val view: AddView) {

    interface AddView {
        fun collectData(): List<Any>
    }

    companion object {
        fun createWith(view: AddView) = AddPresenter(view)
    }

    fun hasChanged(): Boolean {
        //todo
        return true
    }

    fun save() {
        val data = mapData()

        println(data)
    }

    private fun mapData(): Expense {
        val data = view.collectData()
        return Expense(data[0] as Int, data[1] as String, data[2] as ExpenseType)
    }
}