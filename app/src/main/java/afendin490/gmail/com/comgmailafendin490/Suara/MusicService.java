package afendin490.gmail.com.comgmailafendin490.Suara;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import afendin490.gmail.com.comgmailafendin490.R;

public class MusicService extends Service {

    MediaPlayer mp;

    @Override
    public IBinder onBind(Intent arg0){
        return null;
    }
    @Override
    public void onCreate(){
        mp = MediaPlayer.create(this, R.raw.let_it_be);
        mp.setLooping(false);
    }

    public void onStart(Intent intent,int startid){
        mp.start();
    }
    public void onDestroy(){
        mp.stop();
    }

}

