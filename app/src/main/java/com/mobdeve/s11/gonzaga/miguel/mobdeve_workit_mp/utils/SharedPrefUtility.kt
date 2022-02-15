package com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobdeve.s11.gonzaga.miguel.mobdeve_workit_mp.model.WorkoutModel
import java.util.ArrayList

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

    fun saveMyWorkoutsPreferences(key: String?, value: ArrayList<WorkoutModel?>) {
        val prefsEditor = appPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(value)
        prefsEditor.putString(key, json)
        prefsEditor.apply()
    }

    fun getMyWorkouts(key: String?): ArrayList<WorkoutModel?> {

        val workout: ArrayList<WorkoutModel?>
        val gson = Gson()
        val json: String? = appPreferences.getString(key, null)
        /*val type: Type = object : TypeToken<ArrayList<ProductModel?>>() {}.type
        return gson.fromJson(json, type)*/
        workout = when {
            json.isNullOrEmpty() -> ArrayList<WorkoutModel?>()
            else -> gson.fromJson(json, object : TypeToken<ArrayList<WorkoutModel?>>() {}.type)
        }
        return workout
        //try to next may question mark
        //val type: Type = object : TypeToken<ArrayList<ProductModel?>?>() {}.type
        /*return gson.fromJson(json, type)*/

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

    fun getIntegerPreferences(key: String) : Int =
        appPreferences.getInt(key, 0)

    fun removeStringPreferences(key: String?) {
        val prefsEditor = appPreferences.edit()
        prefsEditor.remove(key).apply()
    }

    fun removeAllPreferences() {
        val prefsEditor = appPreferences!!.edit().clear().commit()
    }

}