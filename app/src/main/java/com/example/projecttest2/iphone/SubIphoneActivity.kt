package com.example.projecttest2.iphone

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.projecttest2.R
import com.example.projecttest2.databinding.ActivityMainBinding
import com.example.projecttest2.databinding.ActivityMainBindingImpl
import kotlinx.android.synthetic.main.activity_sub_iphone.*
import java.lang.Exception

class SubIphoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_iphone)
        try {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_sub_iphone)
        }
        catch (e: Exception) {
            Log.i("onclick",e.toString())
        }
        image_car.setImageResource(R.drawable.iphonebg)

        car_name.text = getIntent().getStringExtra("IPHONEMODEL").toString()

        Log.i("onclick",getIntent().getStringExtra("IPHONEMODEL").toString())



        }
    }


