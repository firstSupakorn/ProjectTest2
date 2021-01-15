package com.example.projecttest2.iphone

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.DaoMap
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class IphoneViewModel(val database: DaoMap,
    application: Application) : AndroidViewModel(application) {

    private val videosRepository = IphoneRepository(database)

    init {
        viewModelScope.launch {
            videosRepository.refreshIphonePage()
        }
    }
}

