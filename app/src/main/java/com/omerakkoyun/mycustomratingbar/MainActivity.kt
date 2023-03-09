package com.omerakkoyun.mycustomratingbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.omerakkoyun.mycustomratingbar.customview.custom_rating_bar.CustomRatingBar

class MainActivity : AppCompatActivity(), CustomRatingBar.OnCustomRatingBarChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customRatingBar = findViewById<CustomRatingBar>(R.id.customRatingBar)
        customRatingBar.onCustomRatingBarChangeListener = this

    }

    override fun onRatingChange(customRatingBar: View, rating: Float) {
        Toast.makeText(this, ""+rating.toInt(), Toast.LENGTH_SHORT).show();
    }


}