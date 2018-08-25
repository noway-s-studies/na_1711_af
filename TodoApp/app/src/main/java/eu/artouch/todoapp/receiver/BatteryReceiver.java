package eu.artouch.todoapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BATTERY_LOW")) {
            Toast.makeText(context, "Nagyon alacsony az akkumulázor töltöttsége!",Toast.LENGTH_LONG).show();
        } else if (intent.getAction().equals("android.intent.action.BATTERY_OKAY")) {
            Toast.makeText(context, "Az akkumulázor töltöttsége megfelelő!",Toast.LENGTH_LONG).show();
        }
    }
}
