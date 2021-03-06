package com.example.projecttest2.promotion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.projecttest2.R
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.database.entity.Url
import com.example.projecttest2.databinding.FragmentPromotionBinding
import com.squareup.picasso.Picasso
import java.lang.Exception


var mylist = ArrayList<String>()

//var imageUrlList= listOf(
//        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg",
//        "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_1.jpg",
//        "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_2.jpg",
//        "https://jmb-master.jventures.co.th/jmb-extend/v1/products/JAY_MOBILE_CATEGORY_SET/09/iPhoneSE_2_128/iPhoneSE2_878063505.jpg",
//        "https://fibo.jaymart.org/asset/Images/02/Mobilesub/a17e9165-c7fe-4951-a977-cba60b399f4b.png",
//        "https://fibo.jaymart.org/asset/Images/02/Promotion/1d834a8d-070c-4f91-8da6-0232c8d3c17f.jpg",
//        "https://fibo.jaymart.org/asset/Images/02/Promotion/ac51ad4f-62c2-4f34-8ab8-57dc464e8cd7.jpg",
//        "https://fibo.jaymart.org/asset/Images/02/Promotion/7babf5c8-300f-4932-816a-b8259b676826.jpg"
//)
var imageUrlList= listOf(
        Url("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg"),
        Url("https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_1.jpg")
)



class PromotionFragment : Fragment() {
    lateinit var adapter: PromotionAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentPromotionBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_promotion, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = MapDataBase.getDataBase(application).daoMap()

        val carouselView = binding.carouselview

        val promotionViewModel = PromotionViewModel(dataSource, application)

        Log.i("getDatabase",promotionViewModel.promotionListLiveData.toString())
        Log.i("liveCycle","promotion fragment")

        try{
            promotionViewModel.promotionListLiveData.observeForever(){

            }
        }
        catch (e:Exception){
            Log.i("getDatabase","Error: ${e.toString()}")

        }

        //  Get list promotion url
//        val listPromotionData = dataSource.getPromotions()

        //  Set image on carouselview
//        carouselView.setPageCount(listPromotionData.size)
//        carouselView.setImageListener { position, imageView ->
//            Picasso.get().load(listPromotionData[position].url).into(imageView)
//        }

        return binding.root
    }


}