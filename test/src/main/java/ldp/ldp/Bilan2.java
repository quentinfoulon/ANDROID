package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

/**
 * Created by quentin on 07/10/2016.
 */
public class Bilan2 extends AppCompatActivity {
    private Toolbar toolbar ;
    private EditText texte;
    private Button poster;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.bilan2);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Bilan");
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


        texte=(EditText) findViewById(R.id.textebilan);
        poster=(Button) findViewById(R.id.poster);
        poster.setOnClickListener(suivantListener);

    }
    private View.OnClickListener suivantListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent2 = getIntent();
            Intent intent = new Intent(Bilan2.this, Gestion.class);
            intent.putExtra("value", intent2.getStringExtra("value"));
            intent.putExtra("poste", intent2.getStringExtra("poste"));
            intent.putExtra("username", intent2.getStringExtra("username"));
            intent.putExtra("nom", intent2.getStringExtra("nom"));
            intent.putExtra("prenom", intent2.getStringExtra("prenom"));
            intent.putExtra("theme","gestion");
            startActivity(intent);



        }
    };
}
