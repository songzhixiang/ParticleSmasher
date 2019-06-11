package com.andy.library;

import android.view.View;

/**
 * @author andysong
 * @data 2019-06-11
 * @discription xxx
 */
public  abstract class DebouncingOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        doClick();
    }

    abstract void doClick();
}
