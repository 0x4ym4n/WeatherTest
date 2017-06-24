package test.ayman.weather.weathertest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

@SuppressWarnings("ALL")
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener,
        View.OnClickListener {


    public static String id, email, jsonStr;
    private ProgressDialog progressDialog;
    ArrayList<Integer> coloredItems = new ArrayList<>();
    private SharedPreferences.Editor mEditPrefs;
    private SharedPreferences mPreferences;
    private GoogleApiClient googleApiClient;

    private static final String TAG = "MapsActivity";




    public GoogleMap mMap;
    public static ArrayList<HashMap<String, String>> contactList;

    private ListView lv;
    private Activity convertView;

    public boolean[] checkBoxState;

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    private class ViewHolder {
        CheckBox checkBox;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


        setContentView( R.layout.activity_maps );
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
        lv = (ListView) findViewById( R.id.list );
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener( this )
                .addApi( LocationServices.API)
                .build();


    }




    @SuppressLint("MissingPermission")
    @Override

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType( GoogleMap.MAP_TYPE_SATELLITE );
        if (ActivityCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        googleMap.setMyLocationEnabled( true );
//
        final ArrayList<HashMap<String, String>> contactList = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("contactList");

        final String country = getIntent().getStringExtra( "country" );
        final int sunrise = Integer.parseInt( getIntent().getStringExtra( "sunrise" ) );
        final int sunset = Integer.parseInt( getIntent().getStringExtra( "sunset" ) );
        final String speed = getIntent().getStringExtra( "speed" );
        final String deg = getIntent().getStringExtra( "deg" );

        final String main = getIntent().getStringExtra( "main" );
        final String description = getIntent().getStringExtra( "description" );

        final int dt = Integer.parseInt( getIntent().getStringExtra( "dt" ) );

        final TextView countrt_ = (TextView) findViewById( R.id.textView19 );
        final TextView sunreise_ = (TextView) findViewById( R.id.textView );
        final TextView sunset_ = (TextView) findViewById( R.id.textView5);
        final TextView speed_ = (TextView) findViewById( R.id.textView7);
        final TextView degree_ = (TextView) findViewById( R.id.textView9);
        final TextView dt_ = (TextView) findViewById( R.id.textView10);
        final TextView main_ = (TextView) findViewById( R.id.textView11);
        final TextView description_ = (TextView) findViewById( R.id.textView12);
        main_.setText( contactList.get( 0 ).get("main") );
        description_.setText( contactList.get( 0 ).get("description") );



        Calendar cl = Calendar.getInstance();
        cl.setTimeZone( TimeZone.getTimeZone("GMT+3"));


        cl.setTimeInMillis(sunrise);  //here your time in miliseconds


        sunreise_.setText( "Sunrise  : "+"" + cl.get(Calendar.HOUR_OF_DAY) + ":" + cl.get(Calendar.MINUTE) + ":" + cl.get(Calendar.SECOND) +" GMT+3" );
         cl = Calendar.getInstance();
        cl.setTimeInMillis(sunset);  //here your time in miliseconds
        cl.setTimeZone( TimeZone.getTimeZone("GMT+3"));

        sunset_.setText( "Sunset  : "+"" + cl.get(Calendar.HOUR_OF_DAY) + ":" + cl.get(Calendar.MINUTE) + ":" + cl.get(Calendar.SECOND)+" GMT+3");

         cl = Calendar.getInstance();
        cl.setTimeZone( TimeZone.getTimeZone("GMT+3"));

        cl.setTimeInMillis(dt);  //here your time in miliseconds

        dt_.setText( "Last update: "+"" + cl.get(Calendar.HOUR_OF_DAY) + ":" + cl.get(Calendar.MINUTE) + ":" + cl.get(Calendar.SECOND) +" GMT+3");



        countrt_.setText( "Country :  "+country );
        speed_.setText( "Wind Speed : "+speed +"  "+"meter/sec" );
        degree_.setText( "Degree : "+deg);
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        String url = "https://raw.githubusercontent.com/hjnilsson/country-flags/master/png100px/"+String.valueOf( country ).toLowerCase()+".png";
        Glide.with(this).load(url).into(imageView);

        ImageView imageView3 = (ImageView) findViewById(R.id.imageView4);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView6);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView7);
        ImageView imageView6 = (ImageView) findViewById(R.id.imageView10);




        imageView3.setImageDrawable( getResources().getDrawable(R.drawable.sunrise ));
        imageView4.setImageDrawable( getResources().getDrawable(R.drawable.sunset ));
        imageView5.setImageDrawable( getResources().getDrawable(R.drawable.wind));
        url = "http://openweathermap.org/img/w/"+String.valueOf( contactList.get( 0 ).get( "icon" ) )+".png";
        Glide.with(this).load(url).into(imageView6);



        final ArrayList<HashMap<String, String>> contactList2 = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("contactList2");

        //  System.out.println("...serialized data.."+contactList);


        mMap.setTrafficEnabled(true);
        mMap.getCameraPosition();
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

                CameraUpdate center=CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                CameraUpdate zoom=CameraUpdateFactory.zoomTo(14);
                mMap.moveCamera(center);
                mMap.animateCamera(zoom);

            }
        });

//


            final ListAdapter adapter = new SimpleAdapter(MapsActivity.this, contactList2,
                    R.layout.list_item, new String[]{ "name","data"},
                    new int[]{R.id.name, R.id.value, R.id.imageView3}){

                @Override
                public View getView (final int index, View convertView, ViewGroup parent) {

                    View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item,parent,false);
                    TextView namee=(TextView)v.findViewById( R.id.name );
                    TextView value=(TextView)v.findViewById( R.id.value );
                    ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);

                    if(coloredItems.contains(Integer.valueOf(index)))
                        lv.getChildAt(index).setBackgroundColor(Color.GREEN);


                    try {
                        final HashMap<String, String> list = contactList2.get(index);
                        final String nm = list.get("name");
                        final String data = list.get("data");
                        namee.setText( nm );
                        value.setText( data );





                    } catch (Exception e) {
                    }

                    ViewHolder item= new ViewHolder();







                    return v;
                }};

        lv.setDivider(new ColorDrawable(0x9976B2ED));   //0xAARRGGBB
        lv.setDividerHeight(2);

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> adapter, View v, int position,
                                        long arg3)
                {

//

//
//

                }


            });
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                               int index, long arg3) {




                    return true;

                }
            });





    }



    @Override
    public void onBackPressed()
    {
//        Intent intent = new Intent( getBaseContext(), sum.class );
//        startActivity(intent);

        //thats it
    }



}
