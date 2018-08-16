package eu.artouch.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("FoKepernyo", "onCreate called" );

        final TextView felirat = findViewById(R.id.felirat);
        final EditText bevitel = findViewById(R.id.bevitel);

        Button navigacio = findViewById(R.id.navigacio);

        navigacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhoneNumberActivity.class);
                intent.putExtra("USERNAME", bevitel.getText().toString());
                startActivity(intent);
            }
        });
    }

    /**
     * Meghívódik ha láthatóvá válik az aktiviti
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("FoKepernyo", "onStart called" );
    }

    /**
     * Ha fókuszba kerül az aktiviti
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("FoKepernyo", "onResume called" );
    }

    /**
     * Ha háttérbe kerül az aktiviti
     */
    @Override
    protected void onPause() {
        Log.d("FoKepernyo", "onPause called" );
        super.onPause();
    }

    /**
     * Meghívódik ha már nem látható
     */
    @Override
    protected void onStop() {
        Log.d("FoKepernyo", "onStop called" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("FoKepernyo", "onDestroy called" );
        super.onDestroy();
    }
}
