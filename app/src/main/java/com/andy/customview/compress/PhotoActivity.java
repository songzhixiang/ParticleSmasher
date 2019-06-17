package com.andy.customview.compress;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.andy.customview.R;
import com.andy.library.compress.CompressConfig;
import com.andy.library.compress.CompressImageManager;
import com.andy.library.compress.Photo;
import com.andy.library.compress.listener.CompressImage;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author andysong
 * @data 2019-06-17
 * @discription xxx
 */
public class PhotoActivity extends AppCompatActivity {

    final int REQUEST_CODE_CHOOSE = 10;

    List<Uri> mSelected;

    ArrayList<Photo> mPhotos = new ArrayList<>();

    private CompressConfig mCompressConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
//        mCompressConfig= CompressConfig.getDefaultConfig();
        mCompressConfig = CompressConfig.builder()
                .setUnCompressMinPixel(1000)//最小像素不压缩
                .setUnCompressNormalPixel(2000)//标准像素不压缩
                .setMaxPixel(1200)//长或宽不超过某像素
                .setMaxSize(200 * 1024)//压缩到最大值
                .enablePixelCompress(true)//开启像素压缩
                .enableQualityCompress(true)//开启质量压缩
                .enableReserveRaw(true)//是否保留源文件
                .setCacheDir("")//没有设置的
                .setShowCompressDialog(true)
                .create();
    }

    public void compress(View view) {
    }

    public void pick(View view) {
        Matisse.from(PhotoActivity.this)
                .choose(MimeType.ofAll())
                .countable(true)
                .maxSelectable(9)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new Glide4Engine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
//            mSelected = Matisse.obtainResult(data);
            for (int i = 0; i < Matisse.obtainPathResult(data).size(); i++) {
                mPhotos.add(new Photo(Matisse.obtainPathResult(data).get(i)));
            }
            CompressImageManager.build(PhotoActivity.this, mCompressConfig, mPhotos, new CompressImage.CompressListener() {
                @Override
                public void onCompressSuccess(ArrayList<Photo> images) {
                    Log.e("path","path"+images.get(0).getCompressPath());
                }

                @Override
                public void onCompressFailed(ArrayList<Photo> images, String error) {
                    Log.e("Matisse", "onCompressFailed: " + images+";错误信息为"+error);
                }
            }).compress();

        }
    }
}
