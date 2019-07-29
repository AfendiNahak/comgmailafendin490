package afendin490.gmail.com.comgmailafendin490.InputDataSQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    //deklarasi nama database dan versinya
    private static final String DATABASE_NAME = "inputdatamahasiswa.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //membuat query untuk buat tabel dan insert data ke tabel
        String sql = "create table biodata(nim integer primary key, nama text null, jurusan text null, jk text null, alamat text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO biodata(nim, nama, jurusan, jk, alamat) VALUES ('1', 'Afendi', '1998-01-01', 'Laki-laki','Yogyakarta');";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }

}