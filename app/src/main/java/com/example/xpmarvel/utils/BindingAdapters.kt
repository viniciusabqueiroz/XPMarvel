package com.example.xpmarvel.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.bumptech.glide.Glide
import com.example.xpmarvel.R

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleOrGone")
    fun showOrGone(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("image","placeholder")
    fun setImage(image: ImageView, url: String?, placeHolder: Drawable) {
        if (!url.isNullOrEmpty()){
            Glide.with(image.context).load(url).centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(image)
        }
        else{
            image.setImageDrawable(placeHolder)
        }
    }

    @JvmStatic
    @BindingAdapter("setRefreshLayout", "loading")
    fun setRefreshLayoutLoading(
        view: SwipeRefreshLayout,
        listener: OnRefreshListener?,
        loading: Boolean?
    ) {
        view.setColorSchemeResources(R.color.primary_color, R.color.teal_700, R.color.teal_200)
        view.setOnRefreshListener(listener)
        if (!loading!!) {
            view.isRefreshing = false
        }
    }
}