package com.example.projecttest2.iphone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttest2.database.entity.IphoneData


class IphoneAdapter(private val list: List<IphoneData>, var clickListner: OnIphoneItemClickListner)
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
        holder.bind(iphone, clickListner)
    }

    override fun getItemCount(): Int = list.size

}

interface OnIphoneItemClickListner{
    fun onItemClick(item: IphoneData, position: Int){
    }
}