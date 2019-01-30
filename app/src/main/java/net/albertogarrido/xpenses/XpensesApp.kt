package net.albertogarrido.xpenses

import android.app.Application
import android.arch.persistence.room.Room
import com.jakewharton.threetenabp.AndroidThreeTen

class XpensesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this);
    }
}