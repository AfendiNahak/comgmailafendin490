package afendin490.gmail.com.comgmailafendin490.InputDataSQL;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import afendin490.gmail.com.comgmailafendin490.R;

public class UpdateData extends AppCompatActivity {

    //deklarasi objek
    protected Cursor cursor;
    DataHelper dbHelper;
    Button tombolUpdate, tombolKembali;
    EditText txtNIM, txtNama, txtJurusan, txtJK, txtAlamat;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

         //mendapatkan id untuk masing-masing objek
        dbHelper = new DataHelper(this);
        txtNIM = (EditText) findViewById(R.id.txtUpdateNIM);
        txtNama = (EditText) findViewById(R.id.txtUpdateNama);
        txtJurusan = (EditText) findViewById(R.id.txtUpdateJurusan);
        txtJK = (EditText) findViewById(R.id.txtUpdateJK);
        txtAlamat = (EditText) findViewById(R.id.txtUpdateAlamat);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

         //melakukan select query pada tabel datamahasiswa
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();

        //menampilkan data dengan cursor
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            txtNIM.setText(cursor.getString(0).toString());
            txtNama.setText(cursor.getString(1).toString());
            txtJurusan.setText(cursor.getString(2).toString());
            txtJK.setText(cursor.getString(3).toString());
            txtAlamat.setText(cursor.getString(4).toString());
        }

        //mendapatkan id dari tombol update dan kembali
         tombolUpdate = (Button) findViewById(R.id.btnUpdate);
         tombolKembali = (Button) findViewById(R.id.btnKembali);

        // even pada tombol update
         tombolUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update biodata set nama='"+
                        txtNama.getText().toString() +"', jurusan='" +
                        txtJurusan.getText().toString()+"', jk='"+
                        txtJK.getText().toString() +"', alamat='" +
                        txtAlamat.getText().toString() + "' where nim='" +
                        txtNIM.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil Update", Toast.LENGTH_LONG).show(); //pesan saat data berhasil diupdate
                Main_Input_Data.main_input_data.RefreshList();
                finish();
            }
        });

         //even pada tombol kembali
         tombolKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}