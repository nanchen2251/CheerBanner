package com.nanchen.cheerbanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nanchen.banner.BannerEntity;
import com.nanchen.banner.BannerView;
import com.nanchen.banner.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BannerView mBannerView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBannerView = (BannerView) findViewById(R.id.banner);
        mTextView = (TextView) findViewById(R.id.textView);
        final List<BannerEntity> entities = new ArrayList<>();
        entities.add(new BannerEntity(1, "https://github.com/nanchen2251", "webView"));
        entities.add(new BannerEntity(0, "http://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg", "image"));
        entities.add(new BannerEntity(2, "https://github.com/nanchen2251", "custom"));
        mBannerView.setEntities(entities);

        mBannerView.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onClick(int position) {
                if (mTextView.getVisibility() == View.VISIBLE){
                    mTextView.setVisibility(View.GONE);
                }else{
                    mTextView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        mBannerView.startAutoScroll();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        mBannerView.stopAutoScroll();
    }
}
