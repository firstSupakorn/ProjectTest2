package com.example.projecttest2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.projecttest2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_promotion.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setContentView(R.layout.activity_main)
//
//        var sampleImages = intArrayOf(
//                R.drawable.bg,
//                R.drawable.bg1
//        )
//        Log.i("image",sampleImages.size.toString())
//
//        mainCarouselView.pageCount = sampleImages.size
//
//        mainCarouselView.setImageListener{position, imageView ->
//            imageView.setImageResource(sampleImages[position])
//        }




//        binding.button2.setOnClickListener{
//            pushButton()
//        }
    }
//    private fun pushButton(){
////        binding.textView2.text = iphoneModelname
//        binding.textView2.text = iphoneModel.name
//    }
}