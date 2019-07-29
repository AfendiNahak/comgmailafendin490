package afendin490.gmail.com.comgmailafendin490.InputDataSQL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import afendin490.gmail.com.comgmailafendin490.R;

public class Main_Input_Data extends AppCompatActivity {

    //deklarasi objek
    String[] daftar;
    ListView ListView01;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Main_Input_Data main_input_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        Button btnBuat =(Button)findViewById(R.id.btnBuatData);

        //even pada button buat untuk intent ke form pembuatan data
        btnBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(Main_Input_Data.this, BuatData.class);
                startActivity(inte);
            }
        });
        main_input_data = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    //fungsi yang digunakan untuk melakukan refresh pada list jika suatu even sudah selesai dilakukan
    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }

        //deklarasi sehingga data bisa ditampilkan didalam listview
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);

        //membuat even dengan memunculkan menu pop up jika salah satu item list di klik
        //menu yang muncul adalah lihat data, update data atau hapus data
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Data", "Update Data", "Hapus Data"}; //submenu
                AlertDialog.Builder builder = new AlertDialog.Builder(Main_Input_Data.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        //menggunakan switch-case untuk intent menu
                        switch(item){
                            case 0 :
                                //melakukan intent ke halaman lihat data jika menu lihat data diklik
                                Intent intent = new Intent(getApplicationContext(), LihatData.class);
                                intent.putExtra("nama", selection);
                                startActivity(intent);
                                break;
                            case 1 :
                                //melakukan intent ke halaman update data jika menu update data diklik
                                Intent intent2 = new Intent(getApplicationContext(), UpdateData.class);
                                intent2.putExtra("nama", selection);
                                startActivity(intent2);
                                break;
                            case 2 :
                                //melakukan aksi hapus data dari tabel jika menu hapus data diklik
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from biodata where nama = '"+selection+"'"); //query hapus data
                                Toast.makeText(getApplicationContext(), "Berhasil Dihapus", Toast.LENGTH_LONG).show(); //pesan jika data sudah dihapus
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }
}
