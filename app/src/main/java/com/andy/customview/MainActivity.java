package com.andy.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExplosionField explosionField  = new ExplosionField(this,new FallingParticleFactory());
        explosionField.addListener(findViewById(R.id.tv_hello));
    }
}
