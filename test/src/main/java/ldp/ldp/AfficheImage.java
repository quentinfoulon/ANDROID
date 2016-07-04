package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.VideoView;

/**
 * Created by quentin on 28/06/2016.
 */
public class AfficheImage extends AppCompatActivity {
    private Toolbar toolbar ;
    private ImageView imageSwitcher;
    private VideoView videoview;
    private WebView webview;
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.afficheimage);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        Intent intent = getIntent();
        toolbar.setTitle(" "+intent.getStringExtra("titre"));
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#939292"));
        }


        //action du retour a la page .
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int i ;
        /*videoview = (VideoView)findViewById(R.id.videoView);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoview);

        Uri uri= Uri.parse("https://youtu.be/iNCENdLWwnk");
        videoview.setMediaController(mediaController);
        videoview.setVideoURI(uri);
        videoview.requestFocus();

        videoview.start();*/
        WebView webview = (WebView) findViewById(R.id.web2);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://www.youtube.com/watch?v=iNCENdLWwnk");

    }
}
