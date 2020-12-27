package com.khuong.ailatrieuphu.model

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

object Utils {
    @JvmStatic
    @BindingAdapter("setText")
    fun setText(tv: TextView, content: String?) {
        tv.setText(content)
    }
    @JvmStatic
    @BindingAdapter("setText")
    fun setText(tv: TextView, content: Int) {
        tv.setText(content.toString())
    }
    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    @BindingAdapter("setDuration")
    fun setText(tv: TextView, duration: Long?) {
        if(duration == null){
            tv.setText("")
            return
        }
        val simple = SimpleDateFormat("mm:ss")
        tv.setText(simple.format(duration))
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(iv: ImageView, imageId: Int) {
        iv.setImageResource(imageId)
    }

    @JvmStatic
    @BindingAdapter("setImageLink")
    fun setImageLink(iv: ImageView, link: String) {
        Glide.with(iv.context)
            .load(link)
            .into(iv)
    }
}