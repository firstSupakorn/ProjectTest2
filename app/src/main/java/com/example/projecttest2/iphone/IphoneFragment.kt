package com.example.projecttest2.iphone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.GridLayoutManager
import com.example.projecttest2.MainActivity
import com.example.projecttest2.R
import com.example.projecttest2.database.IphoneData
import com.example.projecttest2.database.MapData
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.database.Url
import com.example.projecttest2.databinding.FragmentIphoneBinding
import com.example.projecttest2.network.JmartApi
import com.example.projecttest2.network.JmartApiService
import com.example.projecttest2.network.MobileApi
import kotlinx.android.synthetic.main.fragment_iphone.*

class IphoneFragment : Fragment(), OnIphoneItemClickListner {
    private val iphoneList = listOf(
            IphoneData(1, "256GB",";http","123"),
            IphoneData(2, "256GB",";http","123"),
            IphoneData(3, "256GB",";http","123"),
            IphoneData(4, "256GB",";http","123"),
            IphoneData(5, "256GB",";http","123")
    )



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i("api","on create view")

        val application = requireNotNull(this.activity).application

        val dataSource = MapDataBase.getDataBase(application).daoMap()

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentIphoneBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_iphone, container, false)


        val iphoneViewModel = IphoneViewModel(dataSource, application)
        Log.i("api","on create view")

        binding.iphoneViewModel = iphoneViewModel
        Log.i("api","on create view")

        val iphoneList1 = dataSource.getAllIphone()

        iphoneList1.observeForever{
            Log.i("testwtfbug","${it}")
            val iphoneAdapter = IphoneAdapter(it,this)
            binding.listRecyclerView.apply{
                layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
                adapter = iphoneAdapter
            }
        }

        return binding.root
    }


    override fun onItemClick(item: IphoneData, position: Int) {
        val intent = Intent(context, SubIphoneActivity::class.java)

        val application = requireNotNull(this.activity).application

        val dataSource = MapDataBase.getDataBase(application).daoMap()
        val jmartAllProductInfo = dataSource.getAllJmart()
        val jmartProductInfo = dataSource.getUniqueJmartProduct(item.display.toString())

        intent.putExtra("IPHONEMODEL", item.model)
        intent.putExtra("IPHONEDESC", item.display)
        intent.putExtra("IPHONEIMAGEURL", item.imageUrl)

        Log.i("onclick","click")
        Log.i("onclick","${item.display}")
        Log.i("onclick","${item.model}")
        startActivity(intent)

    }
    }


