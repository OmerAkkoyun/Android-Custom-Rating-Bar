package com.omerakkoyun.mycustomratingbar.customview.custom_rating_bar

import android.content.Context
import android.media.MediaDrm
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omerakkoyun.mycustomratingbar.R
import com.omerakkoyun.mycustomratingbar.utils.setTintColor


class CustomRatingBarAdapter (val context: Context,val itemLayout:Int=R.layout.item_star_drawable,var ratingStarCount:Int,val eventListener: OnStarClickListener) : RecyclerView.Adapter<CustomRatingBarAdapter.MyviewHolder>(),
    View.OnClickListener {

    var rating:Float = 0.0f
    var selectedStarColor:Int?=null
    var unSelectedStarColor:Int?=null
    var selectedStarImageRes:Int?=null
    var unselectedStarImageRes:Int?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return MyviewHolder(view)
    }

    override fun getItemCount(): Int {

        return ratingStarCount
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {


        if(position<rating && selectedStarColor!=null){
            holder.starImage.setTintColor(selectedStarColor!!)
        }else if (unSelectedStarColor!=null){
            holder.starImage.setTintColor(unSelectedStarColor!!)
        }

        if (position<rating && selectedStarImageRes!=null){
            holder.starImage.setImageResource(selectedStarImageRes!!)
        }else if (unselectedStarImageRes!=null){
            holder.starImage.setImageResource(unselectedStarImageRes!!)
        }

        holder.starImage.tag=position
        holder.starCount.text = (position+1).toString()
        holder.starImage.setOnClickListener(this)

    }


   inner class MyviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val starImage:ImageView=itemView.findViewById(R.id.custom_star_imageView)
        val starCount:TextView=itemView.findViewById(R.id.tvCount)
    }


    override fun onClick(v: View?) {
         val position = v?.tag as Int
          when (v.id) {
             R.id.custom_star_imageView ->{
                 rating=(position+1).toFloat()
                 eventListener.onEvent(R.id.custom_star_imageView,rating)
                 notifyDataSetChanged()

             }
         }
    }


}