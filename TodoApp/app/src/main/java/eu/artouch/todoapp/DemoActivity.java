package eu.artouch.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DemoActivity extends AppCompatActivity {

    private Button animateBTN;
    private Button popupBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        animateBTN = findViewById(R.id.animateBTN);
        popupBTN = findViewById(R.id.popupBTN);

        animateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
