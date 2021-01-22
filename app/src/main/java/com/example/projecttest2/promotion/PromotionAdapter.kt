package com.example.projecttest2.promotion
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttest2.R
import com.makeramen.roundedimageview.RoundedImageView

class PromotionAdapter():RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>(){

    lateinit var list:List<Int>
    fun setContentList(list:List<Int>)
    {
        this.list = list
        notifyDataSetChanged()
    }
    inner class PromotionViewHolder(view:View):RecyclerView.ViewHolder(view){
        var image=itemView.findViewById<RoundedImageView>(R.id.list_promotion_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_promotion_image,parent,false)
        return PromotionViewHolder(view)

    }
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PromotionAdapter.PromotionViewHolder, position: Int) {
        holder.image.setImageResource(list[position])
    }

}