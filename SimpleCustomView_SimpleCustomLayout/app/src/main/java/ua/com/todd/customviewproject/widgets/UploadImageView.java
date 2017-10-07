package ua.com.todd.customviewproject.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import ua.com.todd.customviewproject.R;

public class UploadImageView extends FrameLayout {

    private DisplayImageOptions options;
    private ImageView imageView;
    private ProgressBar progressBar;

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.item_image, this);
        if (isInEditMode())
            return;

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .resetViewBeforeLoading(true)
                .build();

        imageView = (ImageView) findViewById(R.id.image);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .memoryCacheExtraOptions(1000, 1000)
                .diskCacheExtraOptions(1000, 1000, null)
                .build();
        ImageLoader.getInstance().init(config);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.upload_image_view);
        CharSequence s = a.getString(R.styleable.upload_image_view_upload);
        if (s != null) {
            upload(s.toString());
        }
        a.recycle();
    }

    public void upload(String url) {
        ImageLoader.getInstance().displayImage(url, imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    public UploadImageView(Context context) {
        this(context, null, 0);
    }

    public UploadImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UploadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
}
