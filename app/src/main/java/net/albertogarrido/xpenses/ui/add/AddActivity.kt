package net.albertogarrido.xpenses.ui.add

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import net.albertogarrido.xpenses.database.ExpenseType
import net.albertogarrido.xpenses.ExpenseViewModel
import net.albertogarrido.xpenses.R

class AddActivity : AppCompatActivity(), AddPresenter.AddView {

    private val presenter: AddPresenter by lazy { AddPresenter.createWith(this) }

    companion object {
        fun create(context: Context) = Intent(context, AddActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    override fun onResume() {
        super.onResume()
        title = "Add"
        setSupportActionBar(toolbarAdd)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_close_white_24dp)
            setDisplayHomeAsUpEnabled(true)
        }
        presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (presenter.hasChanged()) {
                    showOptionalSaveDiscard()
                } else {
                    finish()
                }
            }
            R.id.action_save -> presenter.save { finish() }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun getContex(): Context = this

    private fun showOptionalSaveDiscard() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.unsaved_changes)
            setPositiveButton(R.string.action_save) { _, _ -> presenter.save { finish() } }
            setNegativeButton(R.string.action_discard) { dialog, _ -> discard(dialog) }
        }.create().show()
    }

    private fun discard(dialog: DialogInterface) {
        dialog.dismiss()
        finish()
    }

    override fun collectData() =
        ExpenseViewModel(
            amount.text.toString(),
            description.text.toString(),
            if (expense.isChecked) ExpenseType.EXPENSE else ExpenseType.INCOME
        )

}
