package com.zhengsonglan.viewpagerwithanimations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by zsl on 2015/2/25.
 */
public class SelectActivity extends ActionBarActivity implements View.OnClickListener {
    private Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("选择动画");
        setContentView(R.layout.activity_select);
        btn1 = (Button) findViewById(R.id.animation1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.animation2);
        btn2.setOnClickListener(this);
        btn3= (Button) findViewById(R.id.animation3);
        btn3.setOnClickListener(this);
        btn4= (Button) findViewById(R.id.animation4);
        btn4.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animation1: {
                AppConfig.select=1;
                break;
            }

            case R.id.animation2: {
                AppConfig.select=2;
                break;
            }
            case R.id.animation3: {
                AppConfig.select=3;
                break;
            }
            case R.id.animation4: {
                AppConfig.select=4;
                break;
            }
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
