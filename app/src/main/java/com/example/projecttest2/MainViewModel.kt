package com.example.projecttest2

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.iphone.IphoneRepository
import com.example.projecttest2.network.SessionManager
import com.example.projecttest2.network.TokenInfo
import com.example.projecttest2.network.UserInfo
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.tirgei.retrofitauthorization.data.ApiClient


class MainViewModel(val database: DaoMap,
                      application: Application) : AndroidViewModel(application) {
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    init {
        viewModelScope.launch {
        }
    }


//    private fun getToken() {
//        apiClient = ApiClient()
//        sessionManager = SessionManager(contextVar)
//        val userInfo = UserInfo("admin@jaymart","Jaymart@2020")
//
//        apiClient.getApiService(contextVar).getToken(userInfo)
//                .enqueue(object : Callback<TokenInfo> {
//                    override fun onFailure(call: Call<TokenInfo>, t: Throwable) {
//                        Log.i("api", t.message.toString())
//                    }
//
//                    override fun onResponse(call: Call<TokenInfo>, response: Response<TokenInfo>) {
//                        val tokenVar = response.body()
//                        Log.i("api",tokenVar?.token.toString())
//                }
//             }
//            )
//        }
    }
