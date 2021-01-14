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
import com.example.projecttest2.database.PromotionData
import com.example.projecttest2.databinding.FragmentIphoneBinding
import com.example.projecttest2.databinding.FragmentMainBinding
import com.example.projecttest2.iphone.IphoneViewModel
import com.example.projecttest2.network.ApiService
import com.example.projecttest2.network.MobileApi.getMobileSub
import com.example.projecttest2.network.TokenApi.getToken
import com.example.projecttest2.network.TokenApi.getToken2


class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentMainBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = MapDataBase.getDataBase(application).daoMap()
//        val mainViewModel = MainViewModel(dataSource, application , context!!  )

//        binding.mainViewModel = mainViewModel

        binding.iphoneButton.setOnClickListener { view: View ->
            getToken2(requireContext())
            getMobileSub(requireContext())
//            MapDataBase.getDataBase(application).daoMap().insertMap(MapData(10,"IPHONe11"))
//            Log.i("getDatabase", MapDataBase.getDataBase(application).daoMap().getMap(10).name!!)
//            Navigation.findNavController(view).navigate(R.id.action_mainFragment5_to_iphoneFragment)
        }
        binding.promotionButton.setOnClickListener { view: View ->
//            ApiService.getPromotion(requireContext())
//            MapDataBase.getDataBase(application).daoMap().insertPromotion(PromotionData(1,"http"))
//            Log.i("getDatabase", MapDataBase.getDataBase(application).daoMap().getUniquePromotion(1).url!!)
            Navigation.findNavController(view).navigate(R.id.action_mainFragment5_to_promotionFragment)
        }
        binding.videoButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_mainFragment5_to_videoFragment)
        }
        binding.loginButton.setOnClickListener { view: View ->
//            getToken(requireContext())
            val promotionDB = MapDataBase.getDataBase(application).daoMap().getAllPromotion()
            for (i in promotionDB)
            {
                Log.i("getDatabase","promotion DB ${i.id.toString()} ${i.url}")
            }
        }
        return binding.root

    }

}
