package com.example.projecttest2.promotion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.projecttest2.iphone.IphoneViewModel
import com.example.projecttest2.R
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.databinding.FragmentIphoneBinding
import com.example.projecttest2.databinding.FragmentPromotionBinding

class PromotionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentPromotionBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_promotion, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = MapDataBase.getDataBase(application).daoMap()
//
        val promotionViewModel = PromotionViewModel(dataSource, application)
//
        binding.promotionViewModel = promotionViewModel

        promotionViewModel.getPromotion()

        return binding.root
    }
}