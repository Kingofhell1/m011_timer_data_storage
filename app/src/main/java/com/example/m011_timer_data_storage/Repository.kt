package com.example.m011_timer_data_storage

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

class Repository {
   private lateinit var prefs: SharedPreferences
   private var localValue: String? = null
    fun getText(context: Context): String {
        prefs = context.getSharedPreferences(DbConst.PREFERENCE_NAME, Context.MODE_PRIVATE)
        return when {
            // проверяем, если локальная переменная отлична от нул
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            // проверям, если записанные данные отличны от нул
            getDataFromSharedPreference(context) != null -> getDataFromSharedPreference(context)!!

            else ->""
    }
    }
    private fun getDataFromLocalVariable(): String? {
        return localValue
    }

    private fun getDataFromSharedPreference(context: Context): String?{
        val getPrefs = context.getSharedPreferences(DbConst.PREFERENCE_NAME, Context.MODE_PRIVATE)
        return getPrefs.getString(DbConst.SHARED_PREFS_KEY, null)

    }

    fun saveText(text: String, context: Context){
        prefs.edit().putString(DbConst.SHARED_PREFS_KEY, text).apply()
        localValue = text
        Toast.makeText(context,"Файл сохранен", Toast.LENGTH_LONG).show()
    }

    fun clearText(context: Context){
        prefs.edit().clear().apply()
        localValue = null
        Toast.makeText(context,"Файл удален", Toast.LENGTH_LONG).show()

    }
}