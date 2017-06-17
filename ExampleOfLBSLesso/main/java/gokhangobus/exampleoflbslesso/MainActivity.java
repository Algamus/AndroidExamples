package gokhangobus.exampleoflbslesso;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager;
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager)getSystemService(context);


        String provider = LocationManager.GPS_PROVIDER;
        this.check
        Location location = locationManager.getLastKnownLocation(provider);
        updateWithNewLocation(location);
    }

    //---

    private  void updateWithNewLocation(Location location){
        String latLongString;
        TextView myLocationText;
        myLocationText = (TextView)findViewById(R.id.myLocationText);
        if(location != null){
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat"+lat+"\nLong:"+lng;
        }else {
            latLongString="No Location Found";
        }
        myLocationText.setText("Yourcurrent position is:\n"+lang);
    }



}
