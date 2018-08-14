package eu.artouch.highlowgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int random = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        final EditText etGuess = findViewById(R.id.etGuess);
        Button btnGuess = findViewById(R.id.btnGuess);

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chackGuess(etGuess);
            }
        });
        generateRandomNumber();
    }

    private void chackGuess(EditText etGuess) {
        try {
            int guess = Integer.parseInt(etGuess.getText().toString());
            if(guess == random){
                Toast.makeText(MainActivity.this, R.string.text_win, Toast.LENGTH_SHORT).show();
            } else if (guess < random ) {
                Toast.makeText(MainActivity.this, "A szám nagyobb!", Toast.LENGTH_SHORT).show();
            } else if (guess > random ) {
                Toast.makeText(MainActivity.this, "A szám kissebb!", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e){
            etGuess.setError("Ide számot írj!");
            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
        }
    }

    private void generateRandomNumber() {
        random = new Random(System.currentTimeMillis()).nextInt(10);
    }

}
