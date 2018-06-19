package com.zakaprov.chatmockup

import android.app.Application
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zakaprov.chatmockup.model.Message
import com.zakaprov.chatmockup.model.User
import io.realm.Realm
import io.realm.RealmConfiguration
import java.io.InputStreamReader

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        configureRealm()
    }

    private fun configureRealm() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name(getString(R.string.config_realm_name))
            .schemaVersion(BuildConfig.realmSchemaVersion)
            .deleteRealmIfMigrationNeeded()
            .initialData {
                val gson = Gson()

                with(resources.openRawResource(R.raw.data)) {
                    val data = gson.fromJson(InputStreamReader(this), JsonObject::class.java)

                    val messages = gson.fromJson(data.get(getString(R.string.config_data_messages)), Array<Message>::class.java)
                    it.copyToRealm(messages.asIterable())

                    val users = gson.fromJson(data.get(getString(R.string.config_data_users)), Array<User>::class.java)
                    it.copyToRealm(users.asIterable())
                }
            }
            .build()

        Realm.setDefaultConfiguration(config)
    }
}
