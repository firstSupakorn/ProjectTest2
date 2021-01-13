package com.example.projecttest2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.projecttest2.database.MapData
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.databinding.FragmentMainBinding
import com.example.projecttest2.network.ApiService


class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
        val application = requireNotNull(this.activity).application

        binding.iphoneButton.setOnClickListener { view: View ->
            ApiService.getMobileSub()
//            MapDataBase.getDataBase(application).daoMap().insertMap(MapData(10,"IPHONe11"))
//            Log.i("getDatabase", MapDataBase.getDataBase(application).daoMap().getMap(10).name!!)
//            Navigation.findNavController(view).navigate(R.id.action_mainFragment5_to_iphoneFragment)
        }
        binding.promotionButton.setOnClickListener { view: View ->
            ApiService.getPromotion()
//            Navigation.findNavController(view).navigate(R.id.action_mainFragment5_to_promotionFragment)
        }
        binding.videoButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_mainFragment5_to_videoFragment)
        }
        binding.loginButton.setOnClickListener { view: View ->
            ApiService.getToken()
        }
        return binding.root

    }

}
