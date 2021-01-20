package com.example.projecttest2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.network.token.SessionManager
import kotlinx.coroutines.launch


class MainViewModel(val database: DaoMap,
                      application: Application) : AndroidViewModel(application) {
    private lateinit var sessionManager: SessionManager

    init {
        viewModelScope.launch {
        }
    }

    }
