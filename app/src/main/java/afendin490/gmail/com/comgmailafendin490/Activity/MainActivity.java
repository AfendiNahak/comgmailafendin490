package afendin490.gmail.com.comgmailafendin490.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import afendin490.gmail.com.comgmailafendin490.GoogleMap.Google_Map;
import afendin490.gmail.com.comgmailafendin490.InputDataSQL.Main_Input_Data;
import afendin490.gmail.com.comgmailafendin490.Kamera.Camera_Layout;
import afendin490.gmail.com.comgmailafendin490.SensorGPS.Sensor_GPSKompas;
import afendin490.gmail.com.comgmailafendin490.Suara.SongPlayer;
import afendin490.gmail.com.comgmailafendin490.WebService.WebService_Main;
import afendin490.gmail.com.comgmailafendin490.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //pindah ke halaman input data SQLite dengan intent
    public void InputData(View view) {
        Intent intent = new Intent(MainActivity.this, Main_Input_Data.class);
        startActivity(intent);
    }

    //pindah ke halaman Google Map dengan intent
    public void GoogleMap(View view) {
        Intent intent = new Intent(MainActivity.this, Google_Map.class);
        startActivity(intent);
    }

    //pindah ke halaman Sensor, Kompas dan GPS dengan intent
    public void Sensor_GPSKompas(View view) {
        Intent intent = new Intent(MainActivity.this, Sensor_GPSKompas.class);
        startActivity(intent);
    }

    //pindah ke halaman Kamera dengan intent
    public void Camera(View view) {
        Intent intent = new Intent(MainActivity.this, Camera_Layout.class);
        startActivity(intent);
    }

    //pindah ke halaman Pemutar Musik dengan intent
    public void SongPlayer(View view) {
        Intent intent = new Intent(MainActivity.this, SongPlayer.class);
        startActivity(intent);
    }

    //pindah ke halaman Web Service dengan intent
    public void WebService(View view) {
        Intent intent = new Intent(MainActivity.this, WebService_Main.class);
        startActivity(intent);
    }
}
