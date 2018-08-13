package eu.artouch.firstgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {

    private Paint paintBg = new Paint();
    private Paint paintLine = new Paint();


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0, getWidth(), getHeight(), paintBg);
        canvas.drawLine(0,0, getWidth(), getHeight(), paintLine);

    }
}
