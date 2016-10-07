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
public class Bilan1 extends AppCompatActivity {
    private Toolbar toolbar ;
    private DatePicker date;
    private EditText adresse;
    private EditText titre;
    private Button suivant;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.bilan1);
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



        date = (DatePicker) findViewById(R.id.datePicker);
        adresse=(EditText) findViewById(R.id.adresse);
        titre=(EditText) findViewById(R.id.titre);
        suivant=(Button) findViewById(R.id.suivant);
        suivant.setOnClickListener(suivantListener);

    }
    private View.OnClickListener suivantListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent2 = getIntent();
            Intent intent = new Intent(Bilan1.this, Bilan2.class);
            intent.putExtra("value", intent2.getStringExtra("value"));
            intent.putExtra("poste", intent2.getStringExtra("poste"));
            intent.putExtra("username", intent2.getStringExtra("username"));
            intent.putExtra("nom", intent2.getStringExtra("nom"));
            intent.putExtra("prenom", intent2.getStringExtra("prenom"));
            intent.putExtra("titre",String.valueOf(titre.getText()));
            intent.putExtra("jour",String.valueOf(date.getDayOfMonth()));
            intent.putExtra("mois",String.valueOf(date.getMonth()));
            intent.putExtra("annee",String.valueOf(date.getYear()));
            intent.putExtra("adresse",String.valueOf(adresse.getText()));
            intent.putExtra("theme","bilan");
            startActivity(intent);



        }
    };
}
