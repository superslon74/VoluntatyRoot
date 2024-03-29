package voluntaty.com.baseroot;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;


public class ServiceWatsapp extends Service {

    public static final int INTERVAL = 6000000; // 10 sec
    public static final int FIRST_RUN = 5000; // 5 seconds
    int REQUEST_CODE = 11223344;

    AlarmManager alarmManager;

    @Override
    public void onCreate() {
        super.onCreate();

        startService();
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        if (alarmManager != null) {
            Intent intent = new Intent(this, RepeatingAlarmServiceWatsapp.class);
            alarmManager.cancel(PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0));
        }

    }

    private void startService() {

        Intent intent = new Intent(this, RepeatingAlarmServiceWatsapp.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + FIRST_RUN,
                INTERVAL,
                pendingIntent);
    }
}
