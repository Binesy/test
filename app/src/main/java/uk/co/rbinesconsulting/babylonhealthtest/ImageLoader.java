package uk.co.rbinesconsulting.babylonhealthtest;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoader {

    private ImageLoader() {
    }

    public static void loadImage(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("imageView must not be null");
        }

        if (url == null) {
            throw new IllegalArgumentException("url must not be null");
        }

        Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}