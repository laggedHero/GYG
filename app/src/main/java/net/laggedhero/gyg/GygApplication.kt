package net.laggedhero.gyg

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class GygApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }
}
