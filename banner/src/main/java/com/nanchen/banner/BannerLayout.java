package com.nanchen.banner;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;

/**
 * BannerLayout 其他布局继承自这里
 * <p>
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-08-30  9:24
 */

public class BannerLayout extends FrameLayout {
    private ImageView mImageView;
    private TextView mTvBannerTitle;
    private WebView mWebView;
    private LinearLayout mLinearLayout;
    private Button mButton;
    private TextView mTextView;
    private int flag = 0;

    public BannerLayout(@NonNull Context context) {
        this(context, null);
    }

    public BannerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(getContext(), R.layout.layout_banner, this);
        mImageView = (ImageView) this.findViewById(R.id.imageView);
        mTvBannerTitle = (TextView) this.findViewById(R.id.tv_banner_title);
        mWebView = (WebView) findViewById(R.id.webView);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_mine);
        mButton = (Button) findViewById(R.id.btn);
        mTextView = (TextView) findViewById(R.id.text);
    }

    public void setEntity(BannerEntity entity) {
        if (entity.type == 0){
            mImageView.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.GONE);
            Glide.with(getContext().getApplicationContext()).load(entity.url).centerCrop().crossFade().into(mImageView);
        }else if (entity.type == 1){
            mImageView.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
            mLinearLayout.setVisibility(View.GONE);

            WebSettings settings = mWebView.getSettings();
            settings.setLoadWithOverviewMode(true);
            settings.setJavaScriptEnabled(true);
            settings.setAppCacheEnabled(true);
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            settings.setSupportZoom(true);
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.setWebChromeClient(new WebChromeClient());
            mWebView.loadUrl(entity.url);
        }else{
            mImageView.setVisibility(View.GONE);
            mWebView.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.VISIBLE);
            mButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTextView.setText(String.format(Locale.CHINA,"按钮已点击：%d",++flag));
                }
            });
        }
        if (!TextUtils.isEmpty(entity.title)) {
            mTvBannerTitle.setText(entity.title);
        } else {
            mTvBannerTitle.setVisibility(GONE);
        }
    }
}
