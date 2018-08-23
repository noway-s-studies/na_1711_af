package eu.artouch.todoapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class DemoActivity extends AppCompatActivity {

    public static final String COUNTER = "COUNTER";
    public static final String DELMO_PREFERENCES = "DELMO_PREFERENCES";
    public static final String IS_ALREADY_STARTED = "IS_ALREADY_STARTED";
    private Button animateBTN;
    private Button popupBTN;
    private Toolbar toolbar;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        counter=0;
        if (savedInstanceState != null) {
            counter=savedInstanceState.getInt(COUNTER);
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        animateBTN = findViewById(R.id.animateBTN);
        popupBTN = findViewById(R.id.popupBTN);
        popupBTN.setText("Megnyomták: "+counter+"x");
        animateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                popupBTN.setText("Megnyomták: "+counter+"x");
                Animation btnAnimation = AnimationUtils.loadAnimation(DemoActivity.this,R.anim.anim_button);
                toolbar.startAnimation(btnAnimation);
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

        if (!isAlreadyStarted()) {
            showWelcomePopup();
            setAlreadyStarted(true);
        }
    }

    private void showWelcomePopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Üdvözlet");
        builder.setMessage("Most használod elősször az alkalmazást.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_demo,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.animateMenu) {
            Animation btnAnimation = AnimationUtils.loadAnimation(DemoActivity.this,R.anim.anim_button);
            popupBTN.startAnimation(btnAnimation);
        } else if (item.getItemId()==R.id.popupMenu){
            showAllertDialog();
        }
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(COUNTER, counter);
        super.onSaveInstanceState(outState);
    }
    private void setAlreadyStarted(boolean isStarted){
        SharedPreferences sharedPreferences=getSharedPreferences(DELMO_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(IS_ALREADY_STARTED, isStarted);
        editor.commit();
    }
    private boolean isAlreadyStarted(){
        SharedPreferences sharedPreferences = getSharedPreferences(DELMO_PREFERENCES,MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_ALREADY_STARTED,false);
    }
}
