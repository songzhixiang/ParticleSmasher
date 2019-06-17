package com.andy.library.compress.listener;

import com.andy.library.compress.Photo;

import java.util.ArrayList;

/**
 * @author andysong
 * @data 2019-06-17
 * @discription xxx
 */
public interface CompressImage {

    // 开始压缩
    void compress();

    // 图片集合的压缩结果返回
    interface CompressListener {

        // 成功
        void onCompressSuccess(ArrayList<Photo> images);

        // 失败
        void onCompressFailed(ArrayList<Photo> images, String error);
    }
}
