package com.omerakkoyun.mycustomratingbar.customview.custom_rating_bar

import android.content.Context
import android.media.MediaDrm
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omerakkoyun.mycustomratingbar.R
import com.omerakkoyun.mycustomratingbar.utils.getColor


class CustomRatingBar : FrameLayout, OnStarClickListener {


    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter:CustomRatingBarAdapter
    private lateinit var view: View
    var onCustomRatingBarChangeListener: OnCustomRatingBarChangeListener?=null
    private var ratingStarCount:Int=5

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context,attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context,attrs,defStyleAttr)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context,attrs, defStyleAttr, defStyleRes)
    }

    private fun init(  context: Context,
                      attrs: AttributeSet?=null,
                      defStyleAttr: Int=0,
                      defStyleRes: Int=0) {

         view =LayoutInflater.from(context).inflate(R.layout.custom_rating_bar,this,true)

        val typedArray=context.theme.obtainStyledAttributes(attrs,R.styleable.CustomRatingBar,defStyleAttr,defStyleRes)
        val selectedStarColor=typedArray.getColor(R.styleable.CustomRatingBar_selected_star_color,
            getColor(context,R.color.selected_star_color))
        val unSelectedStarColor=typedArray.getColor(R.styleable.CustomRatingBar_un_selected_star_color,
            getColor(context,R.color.un_selected_star_color))
        val selectedStar=typedArray.getResourceId(R.styleable.CustomRatingBar_selected_star,R.drawable.custom_rating_bar_star)
        val unSelectedStar=typedArray.getResourceId(R.styleable.CustomRatingBar_un_selected_star,R.drawable.custom_rating_bar_star)
        ratingStarCount=typedArray.getInteger(R.styleable.CustomRatingBar_no_of_stars,5)

        recyclerView=view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        adapter= CustomRatingBarAdapter(context,ratingStarCount = ratingStarCount,eventListener = this)
        adapter.selectedStarColor=selectedStarColor
        adapter.unSelectedStarColor=unSelectedStarColor
        adapter.selectedStarImageRes=selectedStar
        adapter.unselectedStarImageRes=unSelectedStar
        adapter.rating=0.0f

        recyclerView.adapter=adapter

    }




    fun setRating(rating:Float){
        adapter.rating=rating
        adapter.notifyDataSetChanged()
    }

    fun getRating():Float{
        return adapter.rating
    }

    fun getRatingStarCount():Int{
        return ratingStarCount
    }

    fun setRatingStarCount(ratingStarCount:Int){
        this.ratingStarCount=ratingStarCount
        adapter.notifyDataSetChanged()
    }

    fun setSelectedStarColor(@ColorInt selectedColor:Int){
        adapter.selectedStarColor=selectedColor
        adapter.notifyDataSetChanged()
    }
    fun setUnSelectedStarColor(@ColorInt unSelectedColor:Int){
        adapter.selectedStarColor=unSelectedColor
        adapter.notifyDataSetChanged()
    }


    override fun onEvent(id: Int, value: Any?) {
        when(id){
            R.id.custom_star_imageView->{
                onCustomRatingBarChangeListener?.onRatingChange(view,value as Float)
            }

        }

    }

    public interface OnCustomRatingBarChangeListener{
        fun onRatingChange(customRatingBar:View,rating:Float)
    }

}