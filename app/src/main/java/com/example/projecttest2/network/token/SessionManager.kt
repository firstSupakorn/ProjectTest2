package com.example.projecttest2.network.token

import android.content.Context
import android.content.SharedPreferences
import com.example.projecttest2.R

class SessionManager (context: Context) {
        private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String, type: String) {
        val editor = prefs.edit()
        editor.putString(type, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(type: String): String? {
        return prefs.getString(type, null)
    }
}