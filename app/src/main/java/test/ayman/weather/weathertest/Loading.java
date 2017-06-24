package test.ayman.weather.weathertest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.LogRecord;


public class Loading extends AppCompatActivity {
    public static String id, email, jsonStr, trackid;
    public static int temp;
    public static ArrayList<HashMap<String, String>> contactList;
    public static ArrayList<HashMap<String, String>> contactList2;

    public String TAG = Loading.class.getSimpleName();
    //MyDBHelper dbHelper;
    private ProgressDialog progressDialog;
    private ListView lv;
    public String main,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
//        DBAdapter.init( this );


        setContentView( R.layout.activity_loading );
        contactList = new ArrayList<>();
        contactList2 = new ArrayList<>();
        new GetContacts().execute();

    }


    @SuppressWarnings("deprecation")
    public class GetContacts extends AsyncTask<Void, Void, Void> {


        private LogRecord dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog = ProgressDialog.show( Loading.this,
                    "Loading Data", "Please wait" );


            progressDialog.getProgress();

        }


        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText( Loading.this, "Loaded", Toast.LENGTH_SHORT ).show();


            Intent intent = new Intent( getBaseContext(), MapsActivity.class );



//
//
//            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences( getBaseContext() );
//            SharedPreferences.Editor editor = sharedPrefs.edit();
//            Gson gson = new Gson();
//
//            String json = gson.toJson( contactList );
//
//            editor.putString( TAG, json );
//
            progressDialog.dismiss();
            finish();


        }


        @Override
        public Void doInBackground(Void... arg0) {

            test.ayman.weather.weathertest.HttpHandler sh = new test.ayman.weather.weathertest.HttpHandler();//


            String url = "http://api.openweathermap.org/data/2.5/weather?lat="+ MainActivity.Global.Mylat+"&lon="+MainActivity.Global.Mylong+"&appid=24e76dd6671451da4a56893220eb9e37";
            String jsonStr = sh.makeServiceCall( url );

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject( jsonStr );
                    //        JSONArray jsonArray = new JSONArray(json);


                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray( "weather" );
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject( i );

                         String main = c.getString( "main" );
                        String icon = c.getString( "icon" );
                        final String description = c.getString( "description" );

                        // tmp hash map for single contact

                        HashMap<String, String> contact = new HashMap<>();

                        contact.put( "main", main );
                        contact.put( "icon", icon );
                        contact.put( "description", description );





                        // adding Object to contact list
                        contactList.add( contact );
                    }
                    String dt = jsonObj.getString( "dt" );
                    JSONObject weather = jsonObj.getJSONObject( "main" );
                    HashMap<String, String> contact = new HashMap<>();

                    String temp = weather.getString( "temp" );

                        String pressure = weather.getString( "pressure" );
                        String humidity = weather.getString( "humidity" );
                    String temp_min = weather.getString( "temp_min" );
                    String temp_max = weather.getString( "temp_max" );
//                    String sea_level = weather.getString( "sea_level" );
//                    String grnd_level = weather.getString( "grnd_level" );




                    contact = new HashMap<>();
                    contact.put( "data", pressure + " hPa");
                    contact.put( "name", " Pressure"  );
                    contactList2.add( contact );
                    contact = new HashMap<>();
                    contact.put( "data", humidity+" %" );
                    contact.put( "name", "  Humidity" );
                    contactList2.add( contact );

                    contact = new HashMap<>();
                    contact.put( "data", String.valueOf( (Float.parseFloat(  temp_min ))- 273.15F )+ " ℃"  );
                    contact.put( "name", "  Temp min" );
                    contactList2.add( contact );


                    contact = new HashMap<>();
                    contact.put( "data", String.valueOf( (Float.parseFloat(  temp_max ))- 273.15F )+ " ℃"  );
                    contact.put( "name", "  Temp max" );
                    contactList2.add( contact );

//                    contact = new HashMap<>();
//                    contact.put( "data", sea_level );
//                    contact.put( "name", "  Sea level" );
//                    contact = new HashMap<>();
//                    contact.put( "data", grnd_level );
//                    contact.put( "name", "  Ground level" );
                    contact = new HashMap<>();
                    contact.put( "data", String.valueOf( (Float.parseFloat(  temp ))- 273.15F ) + " ℃"  );
                    contact.put( "name", "  Temp" );

                    contactList2.add( contact );



                    JSONObject wind = jsonObj.getJSONObject( "wind" );
                    contact = new HashMap<>();

                    String speed = wind.getString( "speed" );

                    String deg = wind.getString( "deg" );



                    JSONObject sys = jsonObj.getJSONObject( "sys" );
                    contact = new HashMap<>();

                    String country = sys.getString( "country" );

                    String sunrise = sys.getString( "sunrise" );
                    String sunset = sys.getString( "sunset" );



                    Intent intent = new Intent(Loading.this, MapsActivity.class);
                    intent.putExtra("country",String.valueOf( country ));
                    intent.putExtra("sunrise",String.valueOf( sunrise ));
                    intent.putExtra("sunset",String.valueOf( sunset ));
                    intent.putExtra("speed",String.valueOf( speed ));
                    intent.putExtra("deg",String.valueOf( deg ));
                    intent.putExtra("dt",String.valueOf( dt ));

                    intent.putExtra( "contactList", contactList );
                    intent.putExtra( "contactList2", contactList2 );
                    intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );



                    startActivityForResult( intent, 500 );












                } catch (final JSONException e) {
                    Log.e( TAG, "Json parsing error: " + e.getMessage() );
                    runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText( getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG ).show();
                        }
                    } );

                }

            } else {
                Log.e( TAG, "Couldn't get json from server." );
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText( getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG ).show();
                    }
                } );
//                Intent intent = new Intent( getBaseContext(), Weathertoday.class );

//                intent.putExtra( "contactList", contactList );
//                intent.putExtra( "contactList2", contactList2 );
//
//                startActivityForResult( intent, 500 );

                finish();


            }

            return null;
        }

    }

}
