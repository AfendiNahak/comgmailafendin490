package afendin490.gmail.com.comgmailafendin490.Kamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import afendin490.gmail.com.comgmailafendin490.R;

public class Camera_Layout extends AppCompatActivity {

    //deklarasi objek
    private ImageView imageHolder;
    private final int requestCode = 20;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_layout);

        //mencari id dari masing-masing objek
        imageHolder = (ImageView)findViewById(R.id.imageview);
        Button capturedImageButton = (Button)findViewById(R.id.button_image);

        //even pada tombol open kamera untuk mencapture gampar
        capturedImageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");

            String partFilename = currentDateFormat();
            storeCameraPhotoInSDCard(bitmap, partFilename);
        }

        switch (resultCode) {
            case -1:     Log.i("StartUpActivity", "Photo Captured");
                bitmap=(Bitmap) data.getExtras().get("data");
                MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,null,null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageHolder.setImageBitmap(bitmap);
                break;
            default:      Log.i("StartUpActivity", "Cancelled Camera"); break;
        }
    }

    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

    // fungsi untuk menyimpan gampar pada sd card dan menampilkan kembali pada halaman capture
    private void storeCameraPhotoInSDCard(Bitmap bitmap, String currentDate){
        File outputFile = new File(Environment.getExternalStorageDirectory(), "photo_" + currentDate + ".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(),"Gambar Tersimpan dan Tampilkan ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

