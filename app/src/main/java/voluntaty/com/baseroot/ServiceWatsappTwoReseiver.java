package voluntaty.com.baseroot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class ServiceWatsappTwoReseiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            //для Service
            Intent serviceIntent = new Intent(context,ServiceWatsappTwo.class);
            context.startService(serviceIntent);
            //	context.startService(new Intent(context, MyService.class));
        }
    }
}