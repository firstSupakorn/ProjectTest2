package com.example.projecttest2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.projecttest2.database.entity.IphoneData
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.databinding.FragmentMainBinding
import com.example.projecttest2.network.token.TokenApi.getJmartToken
import com.example.projecttest2.network.token.TokenApi.getToken


class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentMainBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = MapDataBase.getDataBase(application).daoMap()

        binding.iphoneButton.setOnClickListener { view: View ->
//            getToken2(requireContext())
//            getMobileSub(requireContext())
//            MapDataBase.getDataBase(application).daoMap().insertMap(MapData(10,"IPHONe11"))
//            Log.i("getDatabase", MapDataBase.getDataBase(application).daoMap().getMap(10).name!!)
            Navigation.findNavController(view).navigate(R.id.action_mainFragment5_to_iphoneFragment)
        }
        binding.promotionButton.setOnClickListener { view: View ->
//            ApiService.getPromotion(requireContext())
//            MapDataBase.getDataBase(application).daoMap().insertPromotion(PromotionData(1,"http"))
//            Log.i("getDatabase", MapDataBase.getDataBase(application).daoMap().getUniquePromotion(1).url!!)
            Navigation.findNavController(view).navigate(R.id.action_mainFragment5_to_promotionFragment)
        }
        binding.videoButton.setOnClickListener { view: View ->
            MapDataBase.getDataBase(application).daoMap().insertIphone(IphoneData(11, "iphone100", "http", "iphone100"))

//            Navigation.findNavController(view).navigate(R.id.action_mainFragment5_to_videoFragment)
        }
        binding.loginButton.setOnClickListener { view: View ->
            getToken(requireContext())
            getJmartToken(requireContext())
//            //  for test database
//            val allPromotion = MapDataBase.getDataBase(application).daoMap().getAllPromotion()
//            for (i in allPromotion) {
//                Log.i("getDatabase", "${i.id.toString()}  :  ${i.url.toString()}")
//            }
        }

        return binding.root

    }
}

