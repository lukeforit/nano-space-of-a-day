package me.lukeforit.spaceofaday.ui.base;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class BindingAdapters {
    @BindingAdapter("image_url")
    public static void bindUrl(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().centerCrop())
                    .into(imageView);
        }
    }

    @BindingAdapter("background_url")
    public static void bindUrl(final ViewGroup viewGroup, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(viewGroup.getContext())
                    .load(url)
                    .apply(new RequestOptions().centerCrop())
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(
                                @NonNull Drawable resource,
                                @Nullable Transition<? super Drawable> transition) {
                            resource.setAlpha(80);
                            viewGroup.setBackground(resource);
                        }
                    });
        }
    }
}
