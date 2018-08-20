package eu.artouch.todoapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

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
                Animation btnAnimation = AnimationUtils.loadAnimation(DemoActivity.this,R.anim.anim_button);
                popupBTN.startAnimation(btnAnimation);
            }
        });
        popupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
                showAllertDialog();
                Toast.makeText(DemoActivity.this, "Ez egy Toast felugró ablak", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void showPopupWindow(){
        View view = getLayoutInflater().inflate(R.layout.popup_demo,null);
        final PopupWindow popupW = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Button closeButton = view.findViewById(R.id.popupBTN);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupW.dismiss();
            }
        });
        popupW.showAsDropDown(popupBTN, 50,50);
    }
    private void showAllertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AllertDialog");
        builder.setMessage("Megnyomták a gombot");
        builder.setPositiveButton("Rendben", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Mégsem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
