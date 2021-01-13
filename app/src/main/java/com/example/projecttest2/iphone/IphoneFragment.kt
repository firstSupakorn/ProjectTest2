package com.example.projecttest2.iphone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.GridLayoutManager
import com.example.projecttest2.R
import com.example.projecttest2.database.MapData
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.databinding.FragmentIphoneBinding
import kotlinx.android.synthetic.main.fragment_iphone.*

class IphoneFragment : Fragment() {
//    val application = requireNotNull(this.activity).application
//    private val iphoneList = MapDataBase.getDataBase(application).daoMap().getAllMap()
    private val iphoneList = listOf(
            MapData(1, "256GB"),
            MapData(2, "256GB")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentIphoneBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_iphone, container, false)
        Log.i("test","iphone")

        val application = requireNotNull(this.activity).application

        val dataSource = MapDataBase.getDataBase(application).daoMap()

        val iphoneViewModel = IphoneViewModel(dataSource, application)

        val iphoneList = dataSource.getAllMap()

        binding.iphoneViewModel = iphoneViewModel


//        val jsonMobileSub = iphoneViewModel.getMobileSub()
//        Log.i("api",binding.jsonMobileSub.toString())



        for (item in iphoneList) {
            Log.i("getDatabase", item.name!!)
        }


        return binding.root
    }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_recycler_view.apply{
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = IphoneAdapter(iphoneList)

        }

    }

    }


