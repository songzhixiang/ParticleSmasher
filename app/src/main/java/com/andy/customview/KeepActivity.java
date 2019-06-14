package com.andy.customview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author andysong
 * @data 2019-06-14
 * @discription 进程保活，创建了1px的Activity
 */
public class KeepActivity extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //设置Activity左上角
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams  attr = window.getAttributes();
        attr.width =1;
        attr.height =1;
        attr.x = 0;
        attr.y = 0;
        window.setAttributes(attr);
        KeepManager.getInstance().setKeep(this);
        Log.e("szx","我在加载了");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("szx","我被销毁了");
    }
}
