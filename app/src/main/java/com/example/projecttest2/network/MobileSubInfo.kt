package com.example.projecttest2.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback


object MobileApi{
    fun getMobileSub(context: Context): MutableLiveData<MobileSub05> {

        val sessionManager = SessionManager(context)
        val token = sessionManager.fetchAuthToken("jbotToken")

        var data: MutableLiveData<MobileSub05> = MutableLiveData()
        Log.i("api", "get MobileSub")

        PromotionsApi.retrofitService.getMobileSub("Bearer ${token}" ).enqueue(
                object : Callback<MobileSubInfo> {
                    override fun onFailure(call: Call<MobileSubInfo>, t: Throwable) {
                        Log.i("api", "Fail")
                    }

                    override fun onResponse(call: Call<MobileSubInfo>, response: retrofit2.Response<MobileSubInfo>) {
                        if (response.isSuccessful) {
                            val json = response.body()
                            data.postValue(response.body()?.mobileSub05)
                        }
                        else{
                            Log.i("api", "Please Login")
                        }
                    }
                }
        )
        return data
    }
}

data class MobileSubInfo (
        val mobileSub01 : MobileSub01,
        val mobileSub02 : MobileSub02,
        val mobileSub03 : MobileSub03,
        val mobileSub04 : MobileSub04,
        val mobileSub05 : MobileSub05,
        val mobileSub06 : MobileSub06
)


data class MobileSub01 (
        val brand : String,
        val subBtn01 : SubBtn01,
        val subBtn02 : SubBtn02,
        val subBtn03 : SubBtn03,
        val subBtn04 : SubBtn04,
        val subBtn05 : SubBtn05,
        val subBtn06 : SubBtn06
)

data class MobileSub02 (
        val brand : String,
        val subBtn01 : SubBtn01,
        val subBtn02 : SubBtn02,
        val subBtn03 : SubBtn03,
        val subBtn04 : SubBtn04,
        val subBtn05 : SubBtn05,
        val subBtn06 : SubBtn06
)

data class MobileSub03 (
        val brand : String,
        val subBtn01 : SubBtn01,
        val subBtn02 : SubBtn02,
        val subBtn03 : SubBtn03,
        val subBtn04 : SubBtn04,
        val subBtn05 : SubBtn05,
        val subBtn06 : SubBtn06
)

data class MobileSub04 (
        val brand : String,
        val subBtn01 : SubBtn01,
        val subBtn02 : SubBtn02,
        val subBtn03 : SubBtn03,
        val subBtn04 : SubBtn04,
        val subBtn05 : SubBtn05,
        val subBtn06 : SubBtn06
)
data class MobileSub05 (
        val brand : String,
        val subBtn01 : SubBtn01,
        val subBtn02 : SubBtn02,
        val subBtn03 : SubBtn03,
        val subBtn04 : SubBtn04,
        val subBtn05 : SubBtn05,
        val subBtn06 : SubBtn06
)
data class MobileSub06 (
        val brand : String,
        val subBtn01 : SubBtn01,
        val subBtn02 : SubBtn02,
        val subBtn03 : SubBtn03,
        val subBtn04 : SubBtn04,
        val subBtn05 : SubBtn05,
        val subBtn06 : SubBtn06
)

data class SubBtn01 (
        val model : String,
        val display : String,
        val imageNormal : String
)
data class SubBtn02 (
        val model : String,
        val display : String,
        val imageNormal : String
)data class SubBtn03 (
        val model : String,
        val display : String,
        val imageNormal : String
)data class SubBtn04 (
        val model : String,
        val display : String,
        val imageNormal : String
)data class SubBtn05 (
        val model : String,
        val display : String,
        val imageNormal : String
)data class SubBtn06 (
        val model : String,
        val display : String,
        val imageNormal : String
)
