package test.ayman.weather.weathertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Weathertoday extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_weathertoday );
        final ArrayList<HashMap<String, String>> contactList = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("contactList");
        Log.d( "Array" , String.valueOf( contactList ) );
        Intent intent = new Intent( getBaseContext(), MainActivity.class );
        startActivity(intent);



    }
}
