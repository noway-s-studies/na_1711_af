package eu.artouch.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PhoneNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        Log.d("TelefonszamKepernyo", "onCreate called" );

        Intent caller = getIntent();
        String username = caller.getStringExtra("USERNAME");
        TextView username_felirat = findViewById(R.id.username_felirat);
        username_felirat.setText("Kedves " + username + ", kérlek add meg a telefonszámodat!");
    }

    /**
     * Meghívódik ha láthatóvá válik az aktiviti
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TelefonszamKepernyo", "onStart called" );
    }

    /**
     * Ha fókuszba kerül az aktiviti
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TelefonszamKepernyo", "onResume called" );
    }

    /**
     * Ha háttérbe kerül az aktiviti
     */
    @Override
    protected void onPause() {
        Log.d("TelefonszamKepernyo", "onPause called" );
        super.onPause();
    }

    /**
     * Meghívódik ha már nem látható
     */
    @Override
    protected void onStop() {
        Log.d("TelefonszamKepernyo", "onStop called" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("TelefonszamKepernyo", "onDestroy called" );
        super.onDestroy();
    }
}
