package eu.artouch.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Lifecycle", "onCreate called" );
    }

    /**
     * Meghívódik ha láthatóvá válik az aktiviti
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "onStart called" );
    }

    /**
     * Ha fókuszba kerül az aktiviti
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume called" );
    }

    /**
     * Ha háttérbe kerül az aktiviti
     */
    @Override
    protected void onPause() {
        Log.d("Lifecycle", "onPause called" );
        super.onPause();
    }

    /**
     * Meghívódik ha már nem látható
     */
    @Override
    protected void onStop() {
        Log.d("Lifecycle", "onStop called" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Lifecycle", "onDestroy called" );
        super.onDestroy();
    }
}
