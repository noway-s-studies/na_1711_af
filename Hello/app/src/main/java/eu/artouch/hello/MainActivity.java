package eu.artouch.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnTime = findViewById(R.id.btnTime);
        final TextView testMy = findViewById(R.id.textMy);


        btnTime.setOnClickListener(new View.OnClickListener() {
            String time = "Az aktuális dátum: " + new Date(System.currentTimeMillis()).toString();
            @Override
            public void onClick(View v) {
                testMy.setText(time);
                Toast.makeText(MainActivity.this, time, Toast.LENGTH_LONG).show();
            }
        });

    }
}
