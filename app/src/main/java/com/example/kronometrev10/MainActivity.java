package com.example.kronometrev10;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnReset;
    Chronometer krono;
    TextView tv;
    long fark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnStart=findViewById(R.id.buttonStart);
        btnStart.setText("Başlat");
        btnReset=findViewById(R.id.buttonReset);
        krono=findViewById(R.id.chronometer2);
        tv=findViewById(R.id.textView2);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnStart.getText()=="Başlat")
                {
                    krono.setBase(SystemClock.elapsedRealtime());
                    krono.start();
                    btnStart.setText("Duraklat");
                }

               else if (btnStart.getText()=="Duraklat")
                {

                    krono.stop();
                    fark=SystemClock.elapsedRealtime();
                    btnStart.setText("Devam Ettir");
                }

               else if (btnStart.getText()=="Devam Ettir")
                {
                    krono.setBase(krono.getBase()+SystemClock.elapsedRealtime()-fark);
                    krono.start();
                    btnStart.setText("Duraklat");
                }


            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                krono.stop();
                krono.setBase(SystemClock.elapsedRealtime());
                btnStart.setText("Başlat");
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}