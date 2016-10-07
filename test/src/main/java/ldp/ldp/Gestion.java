package ldp.ldp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by quentin on 02/06/2016.
 */
public class Gestion extends AppCompatActivity {

    private Button planning ;
    private Button dispo ;
    private Button bilan ;
    private Button propos ;
    private Toolbar toolbar ;
    // @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.gestion);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Gestion");
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
        planning = (Button) findViewById(R.id.planning);
        dispo = (Button) findViewById(R.id.dispo);
        bilan = (Button) findViewById(R.id.bilan);
        propos = (Button) findViewById(R.id.extra);
        planning.setOnClickListener(planningListener);
        dispo.setOnClickListener(dispoListener);
        bilan.setOnClickListener(bilanListener);
        propos.setOnClickListener(proposListener);
        if (isOnline())
        {
            // Faire quelque chose si le périphérique est connecté

        }
        else
        {
            // Faire quelque chose s'il n'est pas connecté
        }
        Boolean test;
        test=Boolean.FALSE;
    }
    private View.OnClickListener planningListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {




        }
    };
    private View.OnClickListener dispoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent2 = getIntent();

            //setContentView(page2);
            Intent intent = new Intent(Gestion.this, ImageList.class);
            intent.putStringArrayListExtra("value", intent2.getStringArrayListExtra("value"));
            intent.putExtra("theme","dispo");
            startActivity(intent);


        }
    };
    private View.OnClickListener bilanListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent2 = getIntent();
            Intent intent = new Intent(Gestion.this, Bilan1.class);
            intent.putExtra("value", intent2.getStringExtra("value"));
            intent.putExtra("poste", intent2.getStringExtra("poste"));
            intent.putExtra("username", intent2.getStringExtra("username"));
            intent.putExtra("nom", intent2.getStringExtra("nom"));
            intent.putExtra("prenom", intent2.getStringExtra("prenom"));
            intent.putExtra("theme","bilan");
            startActivity(intent);

        }
    };
    private View.OnClickListener proposListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent2 = getIntent();

            //setContentView(page2);
            Intent intent = new Intent(Gestion.this, Propos.class);
            intent.putStringArrayListExtra("value", intent2.getStringArrayListExtra("value"));
            intent.putExtra("theme","propos");
            startActivity(intent);


        }
    };
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}