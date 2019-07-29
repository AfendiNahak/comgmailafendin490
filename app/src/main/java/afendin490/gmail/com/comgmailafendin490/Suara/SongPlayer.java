package afendin490.gmail.com.comgmailafendin490.Suara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;

import afendin490.gmail.com.comgmailafendin490.R;

public class SongPlayer extends AppCompatActivity implements View.OnClickListener{

    //membuat objek EditText dan Button
    private Button btnPlay;
    private Button btnPause;
    private Button btnStop;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_player);

        mp = new MediaPlayer();

        //mendapatkan id dari objek button
        btnPlay = (Button) findViewById(R.id.btnPLAY);
        btnPause = (Button) findViewById(R.id.btnPAUSE);
        btnStop = (Button) findViewById(R.id.btnSTOP);

        stateAwal();

        //even pada button play untuk memutar musik
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
                btnPlay.setEnabled(false);
                btnPause.setEnabled(true);
                btnStop.setEnabled(true);
            }
        });

        //even pada button pause untuk menghentikan musik sementara
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });

        //even pada button stop untuk berhenti memainkan musik
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPLAY:
                startService(new Intent(this, MusicService.class));
                break;
            case R.id.btnSTOP:
                stopService(new Intent(this, MusicService.class));
                break;
        }
    }

    // State Awal Pertama Dijalankan
    public void stateAwal() {
        btnPlay.setEnabled(true);
        btnPause.setEnabled(false);
        btnStop.setEnabled(false);
    }

    // Dijalankan Oleh Tombol Play
    private void play() {
        // Memanggil File MP3
        mp = MediaPlayer.create(this, R.raw.let_it_be);

        try {
            mp.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Menjalankan Audio
        mp.start();
        // Penanganan Ketika Suara Berakhir
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stateAwal();
            }
        });
    }

    // Dijalankan Oleh Tombol Pause
    public void pause() {
        if (mp.isPlaying()) {
            if (mp != null) {
                mp.pause();
            }
        } else {
            if (mp != null) {
                mp.start();
            }
        }
    }

    // Dijalankan Oleh Tombol Stop
    public void stop() {
        mp.stop();

        try {
            mp.prepare();
            mp.seekTo(0);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        stateAwal();
    }
}
