package com.example.projecttest2.iphone

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.projecttest2.R
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.databinding.ActivityMainBinding
import com.example.projecttest2.databinding.ActivityMainBindingImpl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sub_iphone.*
import java.lang.Exception

class SubIphoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sp: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_iphone)
        val dataSource = MapDataBase.getDataBase(applicationContext).daoMap()
        val iphoneDesc = getIntent().getStringExtra("IPHONEDESC").toString()
        car_name.text = iphoneDesc

        val jmartProductInfo = dataSource.getUniqueJmartProduct(iphoneDesc)
        Log.i("jmartOnclick","${iphoneDesc} ${jmartProductInfo} ")


        Picasso.get().load(getIntent().getStringExtra("IPHONEIMAGEURL")).into(image_car)

//        .setImageResource()

        car_name.text = getIntent().getStringExtra("IPHONEDESC").toString()
//        val jmartData = getIntent().getStringExtra("JMARTDATA")
//        Log.i("jmartOnclick","${jmartData} ")
        sp = findViewById(R.id.spinner) as Spinner
        val countries = arrayOf("tha","wtf","ggez")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,jmartProductInfo)
        sp.adapter = adapter


        Log.i("onclick",getIntent().getStringExtra("IPHONEMODEL").toString())



        }
    }


