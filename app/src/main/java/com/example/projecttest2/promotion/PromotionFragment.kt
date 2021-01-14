package com.example.projecttest2.promotion

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.projecttest2.R
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.databinding.FragmentPromotionBinding
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
import java.io.InputStream
import java.net.URL


var sampleImages = intArrayOf(
        R.drawable.bg,
        R.drawable.bg1
)
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

        val carouselView = binding.carouselview
        carouselView.setPageCount(imageUrlList.size)
        carouselView.setImageListener(imageListener)

        return binding.root
    }

    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            Picasso.get().load(imageUrlList[position]).into(imageView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}