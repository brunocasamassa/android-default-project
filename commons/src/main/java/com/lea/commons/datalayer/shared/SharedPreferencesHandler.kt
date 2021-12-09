package com.lea.commons.datalayer.shared

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log

class SharedPreferenceHandler private constructor(context: Context) {

    private var categoryShareds: CategoryShareds = CategoryShareds.GLOBAL
    private val prefs = sharedPreferences(context, categoryShareds)


    private fun sharedPreferences(context: Context, categoryShareds: CategoryShareds) =
        when(categoryShareds) {
            CategoryShareds.GLOBAL -> context.getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE)
        }

    fun saveStr(key: String, valor: String) {
        prefs.edit().apply {
            putString(key, valor)
            apply()
        }
    }

    fun getStr(key: String): String {
        Log.d("SHARED_KEY", prefs.getString(key, "").orEmpty())
        return prefs.getString(key, "").orEmpty()
    }

    fun deleteStr(key: String): Boolean {
        return prefs.edit().remove(key).commit()
    }

    fun saveBool(key: String, isValue: Boolean) {
        prefs.edit().apply {
            putBoolean(key, isValue)
            apply()
        }
    }

    fun getBool(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun delBool(key: String): Boolean {
        return prefs.edit().remove(key).commit()
    }

    fun saveInt(key: String, value: Int) {
        prefs.edit().apply {
            putInt(key, value)
            apply()
        }
    }

    fun getInt(key: String): Int {
        return prefs.getInt(key, 0)
    }
    fun getIntOrDefault(key: String, default: Int): Int {
        return prefs.getInt(key, default)
    }

    fun saveLong(key: String, value: Long) {
        prefs.edit().apply {
            putLong(key, value)
            apply()
        }
    }

    fun getLong(key: String): Long {
        return prefs.getLong(key, 0)
    }

    fun clearPrefs() = prefs.edit().clear().apply()

    companion object {

        fun newInstance(context: Context? = null): SharedPreferenceHandler {
            context?.let { EasyContext.baseContext = it}
            return SharedPreferenceHandler(EasyContext.baseContext)
        }

        /** those methods declare an instance by force
         * one line call
         * */
        fun saveStringForced(key: String , value:String) {
             newInstance().saveStr(key, value) ?:""
        }

        fun getStringForced(key: String): String {
            return newInstance().getStr(key) ?:""
        }

        const val PREFS_FILENAME = "br.com.lea.prefs"

    }

    class EasyContext {
        companion object {
            /** set on MainApplication */
            lateinit var baseContext: Context
        }
    }

    enum class CategoryShareds {
        GLOBAL
    }

}
