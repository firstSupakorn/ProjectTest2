package com.example.projecttest2.iphone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttest2.database.IphoneData
import com.example.projecttest2.database.MapData


class IphoneAdapter(private val list: List<IphoneData>)
    : RecyclerView.Adapter<IphoneHolder>() {

    var iphones: List<IphoneData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IphoneHolder {
        val inflater = LayoutInflater.from(parent.context)
        return IphoneHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: IphoneHolder, position: Int) {
        val iphone: IphoneData = list[position]
        holder.bind(iphone)
    }

    override fun getItemCount(): Int = list.size

}