package com.nanchen.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-08-30  9:36
 */

public class BannerPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<BannerEntity> mEntities = new ArrayList<>();
    private List<BannerLayout> mLayouts = new ArrayList<>();
    private OnBannerClickListener mClickListener;

    public BannerPagerAdapter(Context context, List<BannerEntity> entities) {
        mContext = context;
        this.mEntities = entities;
        setLayouts();
    }

    private void setLayouts() {
        for (BannerEntity entity : mEntities) {
            BannerLayout layout = new BannerLayout(mContext);
            layout.setEntity(entity);
            mLayouts.add(layout);
        }
    }

    @Override
    public int getCount() {
        return mLayouts.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        BannerLayout bannerLayout = mLayouts.get(position);
        bannerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onClick(position - 1);
                }
            }
        });
        container.addView(bannerLayout, 0);
        return mLayouts.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mLayouts.get(position));
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    public void setOnBannerClickListener(OnBannerClickListener clickListener) {
        this.mClickListener = clickListener;
    }

}

