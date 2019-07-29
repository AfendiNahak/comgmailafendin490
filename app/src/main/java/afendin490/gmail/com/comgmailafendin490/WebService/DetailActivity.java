package afendin490.gmail.com.comgmailafendin490.WebService;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import afendin490.gmail.com.comgmailafendin490.R;

import static afendin490.gmail.com.comgmailafendin490.WebService.WebService_Main.EXTRA_CREATOR;
import static afendin490.gmail.com.comgmailafendin490.WebService.WebService_Main.EXTRA_LIKES;
import static afendin490.gmail.com.comgmailafendin490.WebService.WebService_Main.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //melakukan intent
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        int likeCount = intent.getIntExtra(EXTRA_LIKES, 0);

        //mendapatkan id dari ImageView dan TextView
        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewCreator = findViewById(R.id.text_view_creator_detail);
        TextView textViewLikes = findViewById(R.id.text_view_like_detail);

        //menampilkan gampar dengan picasso
        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewCreator.setText(creatorName);
        textViewLikes.setText("Likes: " + likeCount);
    }
}
