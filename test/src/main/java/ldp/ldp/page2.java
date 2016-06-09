package ldp.ldp;

import android.annotation.TargetApi;
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
public class page2 extends AppCompatActivity {

    private Button etre ;
    private Button num ;
    private Button svt ;
    private Button trans ;
    private Toolbar toolbar ;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.page2);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Fiche Peda");
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(Color.parseColor("#939292"));

        //action du retour a la page .
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etre = (Button) findViewById(R.id.etre);
        num = (Button) findViewById(R.id.num);
        svt = (Button) findViewById(R.id.svt);
        trans = (Button) findViewById(R.id.trans);
        num.setOnClickListener(numListener);
        svt.setOnClickListener(svtListener);
        trans.setOnClickListener(transListener);
        etre.setOnClickListener(etreListener);
    }
    private View.OnClickListener numListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent2 = getIntent();

            //setContentView(page2);
            Intent intent = new Intent(page2.this, DeroulanteList.class);
            intent.putStringArrayListExtra("value", intent2.getStringArrayListExtra("value"));
            intent.putExtra("theme","numerique");
            startActivity(intent);


        }
    };
    private View.OnClickListener svtListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent2 = getIntent();

            //setContentView(page2);
            Intent intent = new Intent(page2.this, DeroulanteList.class);
            intent.putStringArrayListExtra("value", intent2.getStringArrayListExtra("value"));
            intent.putExtra("theme","svt");
            startActivity(intent);


        }
    };
    private View.OnClickListener transListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent2 = getIntent();

            //setContentView(page2);
            Intent intent = new Intent(page2.this, DeroulanteList.class);
            intent.putStringArrayListExtra("value", intent2.getStringArrayListExtra("value"));
            intent.putExtra("theme","transition");
            startActivity(intent);


        }
    };
    private View.OnClickListener etreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent2 = getIntent();

            //setContentView(page2);
            Intent intent = new Intent(page2.this, DeroulanteList.class);
            intent.putStringArrayListExtra("value", intent2.getStringArrayListExtra("value"));
            intent.putExtra("theme","etrehumain");
            startActivity(intent);


        }
    };

}
