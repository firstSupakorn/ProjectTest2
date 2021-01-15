package com.example.projecttest2.iphone

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttest2.R
import com.example.projecttest2.database.IphoneData
import com.example.projecttest2.database.MapData
import com.squareup.picasso.Picasso

class IphoneHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.iphone_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mYearView: TextView? = null
    private var imageIphoneView: ImageView? = null


    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mYearView = itemView.findViewById(R.id.list_description)
        imageIphoneView = itemView.findViewById(R.id.imgIphone)

    }

    fun bind(iphone: IphoneData) {
        mTitleView?.text = iphone.display
        mYearView?.text = iphone.imageUrl
        Picasso.get().load(iphone.imageUrl).into(imageIphoneView)
    }

}