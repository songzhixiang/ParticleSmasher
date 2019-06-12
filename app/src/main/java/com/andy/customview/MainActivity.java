package com.andy.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.annotation.BindView;
import com.andy.annotation.onClick;
import com.andy.library.ButterKnife;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ExplosionField explosionField  = new ExplosionField(this,new FallingParticleFactory());
        explosionField.addListener(findViewById(R.id.tv_hello));
    }


}
