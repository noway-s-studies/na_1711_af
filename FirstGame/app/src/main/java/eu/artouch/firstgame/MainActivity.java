package eu.artouch.firstgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GameView gameView = findViewById(R.id.gameView);

        Button btnClean = findViewById(R.id.btnClean);
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.clearGameView();

            }
        });
    }
}
