package com.example.tsukadamac.shoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Button button = (Button) findViewById(R.id.button);
        // ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
            button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ボタンがクリックされた時に呼び出されます
                Intent intent = new Intent(MainActivity.this, TabActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
