package com.example.shl.mylib.utils;

import android.widget.ImageView;

import com.example.shl.mylib.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * 网络加载圆形图片
 */
public class XImagHelper {

    private static XImagHelper xImagHelper;

    private static ImageOptions options1, options2, options3;

    private XImagHelper() {
        options1 = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                //设置加载过程中的图片
                .setLoadingDrawableId(R.drawable.loading_bg)
                .build();
        options2 = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                //设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.a56)
                //设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.a56)
                .setCircular(true)
                .build();
        options3 = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                //设置加载过程中的图片
                .setLoadingDrawableId(R.drawable.img_video_bg)
                .build();
    }

    public static XImagHelper getInstance() {
        synchronized (XImagHelper.class) {
            if (xImagHelper == null) {
                xImagHelper = new XImagHelper();
            }
            return xImagHelper;
        }
    }

    public void disImg(String url, ImageView imageView) {
        url = url.trim();
        x.image().bind(imageView, url, options1);
    }

    public void disVideoCover(String url, ImageView imageView) {
        url = url.trim();
        x.image().bind(imageView, url, options3);
    }

    /**
     * 加载圆形图片
     *
     * @param url
     * @param imageView
     */
    public void disCircularImg(String url, ImageView imageView) {
        url = url.trim();
        x.image().bind(imageView, url, options2);
    }
}
