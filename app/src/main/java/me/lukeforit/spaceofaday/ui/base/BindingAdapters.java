package me.lukeforit.spaceofaday.ui.base;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingAdapters {
    @BindingAdapter("image_url")
    public static void bindUrl(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}
