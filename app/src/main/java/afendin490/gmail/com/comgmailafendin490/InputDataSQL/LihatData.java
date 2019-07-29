package afendin490.gmail.com.comgmailafendin490.InputDataSQL;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import afendin490.gmail.com.comgmailafendin490.R;

public class LihatData extends AppCompatActivity {

    //deklarasi objek cursor, DataHelper, button dan textView
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnBack;
    TextView hasilNIM, hasilNama, hasilJurusan, hasilJK, hasilAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        //mendapatkan id untuk masing-masing objek
        dbHelper = new DataHelper(this);
        hasilNIM = (TextView) findViewById(R.id.txtHasilNIM);
        hasilNama = (TextView) findViewById(R.id.txtHasilNama);
        hasilJurusan = (TextView) findViewById(R.id.txtHasilJurusan);
        hasilJK = (TextView) findViewById(R.id.txtHasilJK);
        hasilAlamat = (TextView) findViewById(R.id.txtHasilAlamat);

        //melakukan select query pada tabel datamahasiswa
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();

        //menampilkan data dengan cursor
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            hasilNIM.setText(cursor.getString(0).toString());
            hasilNama.setText(cursor.getString(1).toString());
            hasilJurusan.setText(cursor.getString(2).toString());
            hasilJK.setText(cursor.getString(3).toString());
            hasilAlamat.setText(cursor.getString(4).toString());
        }

        //even pada button kembali
        btnBack = (Button) findViewById(R.id.idBtnKembali);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }



}