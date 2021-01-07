package com.example.projecttest2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.projecttest2.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val iphoneModel:IphoneModel = IphoneModel("wtf")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Timber.i("onCreate")
//        binding.button2.setOnClickListener{
//            pushButton()
//        }
    }
//    private fun pushButton(){
////        binding.textView2.text = iphoneModelname
//        binding.textView2.text = iphoneModel.name
//    }
}