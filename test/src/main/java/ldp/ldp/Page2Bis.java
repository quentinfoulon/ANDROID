package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by quentin on 02/06/2016.
 */
public class Page2Bis extends AppCompatActivity {

    private Button video ;
    private Button image ;
    private Button lien ;
    private Button extra ;
    private Toolbar toolbar ;
    // @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.page2bis);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Fun");
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
        video = (Button) findViewById(R.id.video);
        image = (Button) findViewById(R.id.image);
        lien = (Button) findViewById(R.id.lien);
        extra = (Button) findViewById(R.id.extra);
        video.setOnClickListener(videoListener);
        image.setOnClickListener(imageListener);
        lien.setOnClickListener(lienListener);
        extra.setOnClickListener(extraListener);
    }
    private View.OnClickListener videoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent2 = getIntent();

            //setContentView(page2);
            Intent intent = new Intent(Page2Bis.this, DeroulanteList.class);
            intent.putStringArrayListExtra("value", intent2.getStringArrayListExtra("value"));
            intent.putExtra("theme","video");
            startActivity(intent);


        }
    };
    private View.OnClickListener imageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent2 = getIntent();

            //setContentView(page2);
            Intent intent = new Intent(Page2Bis.this, ImageList.class);
            intent.putStringArrayListExtra("value", intent2.getStringArrayListExtra("value"));
            intent.putExtra("theme","image");
            startActivity(intent);


        }
    };
    private View.OnClickListener lienListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent2 = getIntent();

            //setContentView(page2);
            Intent intent = new Intent(Page2Bis.this, DeroulanteList.class);
            intent.putStringArrayListExtra("value", intent2.getStringArrayListExtra("value"));
            intent.putExtra("theme","lien");
            startActivity(intent);


        }
    };
    private View.OnClickListener extraListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent2 = getIntent();

            //setContentView(page2);
            Intent intent = new Intent(Page2Bis.this, DeroulanteList.class);
            intent.putStringArrayListExtra("value", intent2.getStringArrayListExtra("value"));
            intent.putExtra("theme","extra");
            startActivity(intent);


        }
    };

}