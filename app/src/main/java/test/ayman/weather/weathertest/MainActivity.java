package test.ayman.weather.weathertest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button b;
    private LocationManager locationManager;
    private LocationListener listener;

    public static class Global {
        public static String  Myaccuracy;
        public static String Mylat;
        public static String Mylong;
    }
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        b = (Button) findViewById( R.id.button );
        locationManager = (LocationManager) getSystemService( LOCATION_SERVICE );
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
               Global.Myaccuracy = String.valueOf( Double.valueOf( location.getAccuracy() ) );
                Global.Mylat = String.valueOf( Double.valueOf( location.getLatitude() ) );
                Global.Mylong = String.valueOf( Double.valueOf( location.getLongitude() ) );


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
//                Toast.makeText( start.this, "  Trying to get best location accuracy  ", Toast.LENGTH_LONG ).show();


            }

            @Override
            public void onProviderEnabled(String s) {
                Toast.makeText( MainActivity.this, " GPS Service Stared Successfully  ", Toast.LENGTH_LONG ).show();


            }

            @Override
            public void onProviderDisabled(String s) {

                Toast.makeText( MainActivity.this, "  You have to Enable GPS  ", Toast.LENGTH_LONG ).show();

                Intent i = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
                startActivity( i );
            }
        };
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates( "gps", 5000, 0, listener );

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
if (Global.Myaccuracy !=  null) {


    Intent intent = new Intent( MainActivity.this, Loading.class );
    Log.d( "Vars" , ""+Global.Mylong + "Lat" + Global.Mylat);

    startActivity( intent );
}
else {



    Toast.makeText( getApplicationContext(),
            "Please wait , GPS is trying to get your location... try again after a moment " ,
            Toast.LENGTH_LONG ).show();}

                // }


            }
        });
    }
}
