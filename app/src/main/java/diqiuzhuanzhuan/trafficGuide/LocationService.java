package diqiuzhuanzhuan.trafficGuide;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

/**
 * Created by long on 2014/8/19.
 */
public class LocationService extends IntentService implements AMapLocationListener{

    private LocationManagerProxy mLocationManagerProxy;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private Intent mResultIntent;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public LocationService() {
        super("LocationService");
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        return;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationManagerProxy.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if ( aMapLocation != null && aMapLocation.getAMapException().getErrorCode() == 0 ) {
            Log.i("location", Double.toString(aMapLocation.getLatitude()));
            Log.i("location", Double.toString(aMapLocation.getLongitude()));
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocationManagerProxy = LocationManagerProxy.getInstance(this);
        mLocationManagerProxy.requestLocationData(LocationProviderProxy.AMapNetwork, 60 * 1000, 15, this);
        mLocationManagerProxy.setGpsEnable(false);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("My notification")
                .setContentText("hello world").setSmallIcon(android.R.drawable.ic_dialog_email);
        mBuilder.setDefaults(Notification.DEFAULT_ALL);

        mResultIntent = new Intent(this, MyActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, mResultIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
        mBuilder.setContentIntent(resultPendingIntent);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotificationManager.notify(001, mBuilder.build());
        }
        catch (SecurityException se) {
            se.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
