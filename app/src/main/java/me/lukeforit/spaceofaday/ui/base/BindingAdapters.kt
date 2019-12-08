package me.lukeforit.spaceofaday.ui.base

import androidx.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

object BindingAdapters {
    @BindingAdapter("image_url")
    fun bindUrl(imageView: ImageView, url: String) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.context)
                    .load(url)
                    .apply(RequestOptions().centerCrop())
                    .into(imageView)
        }
    }

    @BindingAdapter("background_url")
    fun bindUrl(viewGroup: ViewGroup, url: String) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(viewGroup.context)
                    .load(url)
                    .apply(RequestOptions().centerCrop())
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(
                                resource: Drawable,
                                transition: Transition<in Drawable>?) {
                            resource.alpha = 80
                            viewGroup.background = resource
                        }
                    })
        }
    }
}
