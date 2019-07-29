package afendin490.gmail.com.comgmailafendin490.GoogleMap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import afendin490.gmail.com.comgmailafendin490.R;

public class Google_Map extends AppCompatActivity {

    //Deklarasi variable atau objek
    Button btnNavigasi;
    String goolgeMap = "com.google.android.apps.maps"; // package aplikasi google maps android
    Uri gmmIntentUri;
    Intent mapIntent;
    String dekat_STMIK_Akakom = "-7.7912085, 110.405764"; // koordinat Dekat STMIK Akakom

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_map_layout);

        // Cari id button untuk navigasi
        btnNavigasi = (Button) findViewById(R.id.btnNavigasi);

        // tombol untuk menjalankan navigasi google maps intent
        btnNavigasi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Buat Uri dari intent string dengan menggunakan hasilnya untuk membuat Intent.
                gmmIntentUri = Uri.parse("google.navigation:q=" + dekat_STMIK_Akakom);

                // Buat Uri dari intent gmmIntentUri
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
                mapIntent.setPackage(goolgeMap);

                //mengecek apakah aplikasi google map sudah diintall atau belum
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(Google_Map.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                            Toast.LENGTH_LONG).show();
                }
            }

        });

    }
}
