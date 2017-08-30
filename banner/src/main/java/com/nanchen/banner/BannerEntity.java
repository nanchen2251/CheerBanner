package com.nanchen.banner;

/**
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-08-30  9:16
 */

public class BannerEntity {
    public int type; // 显示样式，你可以自定义 N 种样式
    public String url; // 地址，可以是图片或者网址或者其他
    public String title; // 显示轮播的标题

    public BannerEntity(int type, String url, String title) {
        this.type = type;
        this.url = url;
        this.title = title;
    }

    public BannerEntity() {
    }
}
