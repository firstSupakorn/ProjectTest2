package com.example.projecttest2.promotion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.projecttest2.R
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.database.Url
import com.example.projecttest2.databinding.FragmentPromotionBinding
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener


var mylist = ArrayList<String>()

var imageUrlList= arrayOf(
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg",
        "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_1.jpg",
        "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_2.jpg",
        "https://fibo.jaymart.org/asset/Images/02/Promotion/1d834a8d-070c-4f91-8da6-0232c8d3c17f.jpg",
        "https://fibo.jaymart.org/asset/Images/02/Promotion/ac51ad4f-62c2-4f34-8ab8-57dc464e8cd7.jpg",
        "https://fibo.jaymart.org/asset/Images/02/Promotion/7babf5c8-300f-4932-816a-b8259b676826.jpg"
)



class PromotionFragment : Fragment() {
    lateinit var adapter: PromotionAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentPromotionBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_promotion, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = MapDataBase.getDataBase(application).daoMap()

        val promotionViewModel = PromotionViewModel(dataSource, application)

        val promotionDB = MapDataBase.getDataBase(application).daoMap().getAllPromotion()

//        promotionViewModel.promotions.observe(viewLifecycleOwner, Observer<List<Url>> {
//            for (url in it)
//            {
//                mylist.add(url.url)
//                Log.i("liveData","${url.url}")
//            }
//            Log.i("liveData","size : "+it.size.toString())
//        })


//        val carouselView = binding.carouselview
//        carouselView.setPageCount(imageUrlList.size)
//        carouselView.setImageListener{ position, imageView ->
//            Picasso.get().load(imageUrlList[position]).into(imageView)
//        }


        promotionViewModel.promotions.observe(viewLifecycleOwner, Observer<List<Url>> {
        val carouselView = binding.carouselview
        carouselView.setPageCount(it.size)
        carouselView.setImageListener { position, imageView ->
            Picasso.get().load(it[position]).into(imageView)
        }
        })


        return binding.root
    }



//    var imageListener: ImageListener = object : ImageListener {
//        override fun setImageForPosition(position: Int, imageView: ImageView) {
//            Picasso.get().load(mylist[position]).into(imageView)
////            Picasso.get().load(imageUrlList[position]).into(imageView)
//
//        }
//    }

}