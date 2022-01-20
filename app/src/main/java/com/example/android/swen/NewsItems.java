package com.example.android.swen;

import android.widget.ImageView;

public class NewsItems {
    String mImageUrl;
    String mNewsDescription;
    String mNewsSource;
    String mWebUrl;

    public NewsItems(String ImageUrl, String NewsDescription, String NewsSource,String WebUrl) {
        mImageUrl=ImageUrl;
        mNewsDescription = NewsDescription;
        mNewsSource = NewsSource;
        mWebUrl=WebUrl;
    }
}
