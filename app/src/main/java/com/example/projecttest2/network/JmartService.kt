package com.example.projecttest2.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.projecttest2.network.JmartCreateApi.retrofitService
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://jmb-master.jventures.co.th/"

interface JmartApiService {
    @GET("jmb-service/v1/japi/items/filter/1068/1100000042")
    fun getProduct(@Query("modelDesc") modelDesc: String, @Header("X-Auth-Token") authHeader: String): Call<JsonArray>

    @GET("jmb-service/v1/japi/generate/token/IFU_JROBOT")
    fun getJmartToken(): Call<String>
}

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()

object JmartCreateApi {
    val retrofitService : JmartApiService by lazy {
        retrofit.create(JmartApiService::class.java)
    }

}

object JmartApi{

    fun getJmartData(context: Context,modelDesc: String): MutableLiveData<JsonArray> {
        val sessionManager = SessionManager(context)
        val jmartToken = sessionManager.fetchAuthToken("jmartToken")

        var data: MutableLiveData<JsonArray> = MutableLiveData()

        retrofitService.getProduct(modelDesc,"${jmartToken}" ).enqueue(
                object : Callback<JsonArray> {
                    override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                        Log.i("api", "Fail"+t.message)
                    }

                    override fun onResponse(call: Call<JsonArray>, response: retrofit2.Response<JsonArray>) {
                        if (response.isSuccessful) {
                            val json = response.body()
                            data.postValue(response.body())
                            Log.i("api", "Success ${json}")
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




data class JmartProductInfo (

        @SerializedName("itemCode") val itemCode : Int,
        @SerializedName("itemDesc") val itemDesc : String,
        @SerializedName("categorySetId") val categorySetId : Int,
        @SerializedName("categorySetName") val categorySetName : String,
        @SerializedName("inventoryItemStatusCode") val inventoryItemStatusCode : String,
        @SerializedName("itemType") val itemType : Int,
        @SerializedName("typeDesc") val typeDesc : String,
        @SerializedName("brandId") val brandId : Int,
        @SerializedName("brandDesc") val brandDesc : String,
        @SerializedName("modelId") val modelId : String,
        @SerializedName("modelDesc") val modelDesc : String,
        @SerializedName("colorId") val colorId : Int,
        @SerializedName("colorDesc") val colorDesc : String,
        @SerializedName("supplierId") val supplierId : Int,
        @SerializedName("supplierDesc") val supplierDesc : String,
        @SerializedName("stdPrice") val stdPrice : Double,
        @SerializedName("onhand") val onhand : Int,
        @SerializedName("pricelistCode") val pricelistCode : Int,
        @SerializedName("pricelistDesc") val pricelistDesc : String,
        @SerializedName("uomCode") val uomCode : String,
        @SerializedName("inventoryItemId") val inventoryItemId : Int,
        @SerializedName("organizationId") val organizationId : Int,
        @SerializedName("bandwidth") val bandwidth : Int,
        @SerializedName("bandwidthDesc") val bandwidthDesc : String,
        @SerializedName("ratioRate") val ratioRate : Int,
        @SerializedName("itemStatus") val itemStatus : String,
        @SerializedName("itemControl") val itemControl : String,
        @SerializedName("itemSpec") val itemSpec : ItemSpec
)


data class ItemSpec (

        @SerializedName("ITEM_CODE") val iTEM_CODE : Int,
        @SerializedName("CATEGORY_SET_ID") val cATEGORY_SET_ID : Int,
        @SerializedName("ITEM_NAME_TH") val iTEM_NAME_TH : String,
        @SerializedName("ITEM_NAME_EN") val iTEM_NAME_EN : String,
        @SerializedName("ITEM_COLOR_TH") val iTEM_COLOR_TH : String,
        @SerializedName("ITEM_COLOR_EN") val iTEM_COLOR_EN : String,
        @SerializedName("IMAGE") val iMAGE : String,
        @SerializedName("DETAIL_DESC_L1") val dETAIL_DESC_L1 : String,
        @SerializedName("DETAIL_DESC_L2") val dETAIL_DESC_L2 : String,
        @SerializedName("DETAIL_DESC_L3") val dETAIL_DESC_L3 : String,
        @SerializedName("DETAIL_DESC_L4") val dETAIL_DESC_L4 : String,
        @SerializedName("DETAIL_DESC_L5") val dETAIL_DESC_L5 : String,
        @SerializedName("DETAIL_DESC_L6") val dETAIL_DESC_L6 : String,
        @SerializedName("DETAIL_DESC_L7") val dETAIL_DESC_L7 : String,
        @SerializedName("DETAIL_DESC_L8") val dETAIL_DESC_L8 : String,
        @SerializedName("DETAIL_DESC_R1") val dETAIL_DESC_R1 : String,
        @SerializedName("DETAIL_DESC_R2") val dETAIL_DESC_R2 : String,
        @SerializedName("DETAIL_DESC_R3") val dETAIL_DESC_R3 : String,
        @SerializedName("DETAIL_DESC_R4") val dETAIL_DESC_R4 : String,
        @SerializedName("DETAIL_DESC_R5") val dETAIL_DESC_R5 : String,
        @SerializedName("DETAIL_DESC_R6") val dETAIL_DESC_R6 : String,
        @SerializedName("DETAIL_DESC_R7") val dETAIL_DESC_R7 : String,
        @SerializedName("DETAIL_DESC_R8") val dETAIL_DESC_R8 : String,
        @SerializedName("DETAIL_INFORMATION_TH") val dETAIL_INFORMATION_TH : String,
        @SerializedName("DETAIL_INFORMATION_EN") val dETAIL_INFORMATION_EN : String,
        @SerializedName("FIELD_1") val fIELD_1 : String,
        @SerializedName("FIELD_2") val fIELD_2 : String,
        @SerializedName("FIELD_3") val fIELD_3 : String,
        @SerializedName("FIELD_4") val fIELD_4 : String,
        @SerializedName("FIELD_5") val fIELD_5 : String,
        @SerializedName("FIELD_6") val fIELD_6 : String,
        @SerializedName("FIELD_7") val fIELD_7 : String,
        @SerializedName("FIELD_8") val fIELD_8 : String,
        @SerializedName("FIELD_9") val fIELD_9 : String,
        @SerializedName("FIELD_10") val fIELD_10 : Int,
        @SerializedName("FIELD_11") val fIELD_11 : String,
        @SerializedName("FIELD_12") val fIELD_12 : String,
        @SerializedName("FIELD_13") val fIELD_13 : String,
        @SerializedName("FIELD_14") val fIELD_14 : String,
        @SerializedName("FIELD_15") val fIELD_15 : String,
        @SerializedName("FIELD_16") val fIELD_16 : String,
        @SerializedName("FIELD_17") val fIELD_17 : String,
        @SerializedName("FIELD_18") val fIELD_18 : String,
        @SerializedName("FIELD_19") val fIELD_19 : String,
        @SerializedName("FIELD_20") val fIELD_20 : String,
        @SerializedName("FIELD_21") val fIELD_21 : String,
        @SerializedName("FIELD_22") val fIELD_22 : String,
        @SerializedName("FIELD_23") val fIELD_23 : String,
        @SerializedName("FIELD_24") val fIELD_24 : String,
        @SerializedName("FIELD_25") val fIELD_25 : String,
        @SerializedName("FIELD_26") val fIELD_26 : String,
        @SerializedName("FIELD_27") val fIELD_27 : String,
        @SerializedName("FIELD_28") val fIELD_28 : String,
        @SerializedName("FIELD_29") val fIELD_29 : String,
        @SerializedName("FIELD_30") val fIELD_30 : String,
        @SerializedName("FIELD_31") val fIELD_31 : String,
        @SerializedName("FIELD_32") val fIELD_32 : String,
        @SerializedName("FIELD_33") val fIELD_33 : String,
        @SerializedName("FIELD_34") val fIELD_34 : String,
        @SerializedName("FIELD_35") val fIELD_35 : String,
        @SerializedName("FIELD_36") val fIELD_36 : String,
        @SerializedName("FIELD_37") val fIELD_37 : String,
        @SerializedName("FIELD_38") val fIELD_38 : String,
        @SerializedName("FIELD_39") val fIELD_39 : String,
        @SerializedName("FIELD_40") val fIELD_40 : String,
        @SerializedName("FIELD_41") val fIELD_41 : String,
        @SerializedName("FIELD_42") val fIELD_42 : String,
        @SerializedName("FIELD_43") val fIELD_43 : String,
        @SerializedName("FIELD_44") val fIELD_44 : String,
        @SerializedName("FIELD_45") val fIELD_45 : String,
        @SerializedName("FIELD_46") val fIELD_46 : String,
        @SerializedName("FIELD_47") val fIELD_47 : String,
        @SerializedName("FIELD_48") val fIELD_48 : String,
        @SerializedName("FIELD_49") val fIELD_49 : String,
        @SerializedName("FIELD_50") val fIELD_50 : String,
        @SerializedName("FIELD_51") val fIELD_51 : String,
        @SerializedName("FIELD_52") val fIELD_52 : String,
        @SerializedName("FIELD_53") val fIELD_53 : String,
        @SerializedName("FIELD_54") val fIELD_54 : String,
        @SerializedName("FIELD_55") val fIELD_55 : String,
        @SerializedName("FIELD_56") val fIELD_56 : String,
        @SerializedName("FIELD_57") val fIELD_57 : String,
        @SerializedName("FIELD_58") val fIELD_58 : String,
        @SerializedName("FIELD_59") val fIELD_59 : String,
        @SerializedName("FIELD_60") val fIELD_60 : String
)

