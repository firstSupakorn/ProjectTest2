package com.example.projecttest2
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class IphoneModel( var name: String = "" )

class IphoneViewModel : ViewModel(){
    init {
        Log.i("IphoneFragment","call iphone view model")
        var number = 0

    }
    var score = MutableLiveData<Int>()


//    fun updateScore(){
//        score.value = score.value + 1
//    }

}
