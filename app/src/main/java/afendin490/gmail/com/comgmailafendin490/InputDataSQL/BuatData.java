package afendin490.gmail.com.comgmailafendin490.InputDataSQL;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import afendin490.gmail.com.comgmailafendin490.R;

public class BuatData extends AppCompatActivity {

    //deklarasi objek database, button dan editText
    DataHelper dbHelper;
    Button btnBack, btnSave;
    EditText txtNIM, txtNama, txtJurusan, txtJK, txtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_data);

        //mendapatkan id dari masing-masing objek atau variabel
        dbHelper = new DataHelper(this);
        txtNIM = (EditText) findViewById(R.id.textNIM);
        txtNama = (EditText) findViewById(R.id.textNama);
        txtJurusan = (EditText) findViewById(R.id.textJurusan);
        txtJK = (EditText) findViewById(R.id.textJK);
        txtAlamat = (EditText) findViewById(R.id.textAlamat);
        btnSave = (Button) findViewById(R.id.btnSimpan);
        btnBack = (Button) findViewById(R.id.btnKembali);

        //even klik pada button save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                validasiData(); //panggil fungsi validasiData
            }
        });

        //even klik pada button kembali
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    //fungsi addDataMhs untuk tambah data pada tabel datamahasiswa
    public void addDataMhs(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into biodata(nim, nama, jurusan, jk, alamat) values('" +
                txtNIM.getText().toString() + "','" +
                txtNama.getText().toString() + "','" +
                txtJurusan.getText().toString() + "','" +
                txtJK.getText().toString() + "','" +
                txtAlamat.getText().toString() + "')");

        //buat pesan dengan Toast
        Toast.makeText(getApplicationContext(), "Berhasil Menyimmpan", Toast.LENGTH_LONG).show();
        Main_Input_Data.main_input_data.RefreshList();
        finish();
    }

    //fungsi validasiData untuk melakukan validasi sederhana pada form
    public void validasiData(){
        String teksNIM = txtNIM.getText().toString();
        if(teksNIM.length()==0){
            txtNIM.setError("Form Tidak Boleh Kosong!");
            btnSave.setEnabled(false);
        } else if(teksNIM.length()>0){
            btnSave.setEnabled(true);
            addDataMhs(); //panggil fungsi addDataMhs
        }
    }
}