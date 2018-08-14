package eu.artouch.firstgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GameView gameView = findViewById(R.id.gameView);

        tvData = findViewById(R.id.tvData);

        Button btnClean = findViewById(R.id.btnClean);
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.clearGameView();

            }
        });
    }
    public void changeText(String text){
        tvData.setText(text);
    }
}
