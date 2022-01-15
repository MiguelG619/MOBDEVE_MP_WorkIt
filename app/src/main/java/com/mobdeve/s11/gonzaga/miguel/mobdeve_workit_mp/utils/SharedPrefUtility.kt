package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.utils

import android.content.Context
import android.content.SharedPreferences

// Sharedpreferences is used for settings or data that does not grow (top 10 place) unlike creation of accounts
//it also loads state
//Used for non technical data
// Similar to windows registry (regedit)
// Name, data type, data
class SharedPrefUtility {

    private lateinit var appPreferences: SharedPreferences
    private val PREFS = "appPreferences"


    // Context --> kung sino yung running
    constructor(context: Context) {
        appPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

    fun saveStringPreferences(key: String?, value: String?) {
        val prefsEditor = appPreferences.edit()
        prefsEditor.putString(key, value).apply()
    }
    fun saveIntegerPreferences(key: String?, value: Int) {
        val prefsEditor = appPreferences.edit()
        prefsEditor.putInt(key, value).apply()
    }

    fun getStringPreferences(key: String?) : String? =
        appPreferences.getString(key, "Nothing Saved")

    fun getIntegerPreferences(key: String) : Int? =
        appPreferences.getInt("key", 0)

    fun removeStringPreferences(key: String?) {
        val prefsEditor = appPreferences.edit()
        prefsEditor.remove(key).apply()
    }

    fun removeAllPreferences() {
        val prefsEditor = appPreferences!!.edit().clear().commit()
    }

}