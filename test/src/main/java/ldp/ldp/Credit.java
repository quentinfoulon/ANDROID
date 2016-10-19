package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by quentin on 19/10/2016.
 */
public class Credit extends AppCompatActivity {
    private Toolbar toolbar;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //intent2 = new Intent(Propos.this, Gestion.class);

        //setContentView(R.layout.propos);
        setContentView(R.layout.propos);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        Intent intent = getIntent();
        toolbar.setTitle("Version :1.0");


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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }
}
