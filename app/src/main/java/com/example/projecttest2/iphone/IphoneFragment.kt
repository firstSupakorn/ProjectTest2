package com.example.projecttest2.iphone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.GridLayoutManager
import com.example.projecttest2.R
import com.example.projecttest2.database.entity.IphoneData
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.databinding.FragmentIphoneBinding

class IphoneFragment : Fragment(), OnIphoneItemClickListner {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i("api","on create view")

        val application = requireNotNull(this.activity).application

        val dataSource = MapDataBase.getDataBase(application).daoMap()

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentIphoneBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_iphone, container, false)

        val iphoneViewModel = IphoneViewModel(dataSource, application)

        //  EDIT!!! -> change to function in repository
        val iphoneList = dataSource.getAllIphone()

        iphoneList.observeForever{
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

        intent.putExtra("IPHONEMODEL", item.model)
        intent.putExtra("IPHONEDESC", item.display)
        intent.putExtra("IPHONEIMAGEURL", item.imageUrl)

        Log.i("onclick","click")
        Log.i("onclick","${item.display}")
        Log.i("onclick","${item.model}")
        startActivity(intent)

    }
    }


