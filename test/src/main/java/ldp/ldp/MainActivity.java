package ldp.ldp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;

import static ldp.ldp.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private Button media ;
    private Button peda ;
    private Button formation ;
    private Button fun ;
    public ArrayList al2 ;
    private DBConnexion db;
    private Boolean test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        if (isOnline())
        {
            // Faire quelque chose si le périphérique est connecté
            db = new DBConnexion(); db.execute();
        }
        else
        {
            // Faire quelque chose s'il n'est pas connecté
        }
        Boolean test;
        test=Boolean.FALSE;






        peda = (Button) findViewById(R.id.peda);
        media = (Button) findViewById(R.id.media);
        formation = (Button) findViewById(R.id.formation);
        fun = (Button) findViewById(R.id.fun);
        peda.setOnClickListener(pedaListener);
        formation.setOnClickListener(codeListener);
        media.setOnClickListener(mediaListener);
        fun.setOnClickListener(funListener);
        //peda.setOnTouchListener(pedaTListener);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private View.OnClickListener pedaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            boolean ok=Boolean.TRUE;
            if (isOnline()) {
                    Intent intent = new Intent(MainActivity.this, page2.class);
                    al2 = new ArrayList(db.al);
                    try {
                        System.out.println("test: " + al2.get(0));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),
                                " Donnée en cours de chargement .",

                                Toast.LENGTH_SHORT).show();
                        ok=Boolean.FALSE;
                    }
                intent.putStringArrayListExtra("value", al2);
                if(ok)
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),
                        " Pas de connexion a internet",
                        Toast.LENGTH_SHORT).show();
            }
            //db.onPostExecute(test);// test pour la fin de la requete
            /*if(test){
                System.out.println("requete fini");
            }*/
        }
    };
    private View.OnClickListener codeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            //setContentView(page2);
            boolean ok=Boolean.TRUE;
            if (isOnline()) {
                Intent intent = new Intent(MainActivity.this, Formation.class);
                al2 = new ArrayList(db.al);
                try {
                    System.out.println("test: " + al2.get(0));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            " Donnée en cours de chargement .",

                            Toast.LENGTH_SHORT).show();
                    ok=Boolean.FALSE;
                }
                intent.putStringArrayListExtra("value", al2);
                intent.putExtra("theme","formation");
                if(ok)
                    startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),
                        " Pas de connexion a internet",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
    private View.OnClickListener mediaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            Intent intent2 = new Intent(MainActivity.this, Listecode.class);
            intent2.putExtra("theme","media");
            intent2.putStringArrayListExtra("value", al2);
            startActivity(intent2);
        }
    };
    private View.OnClickListener funListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            boolean ok=Boolean.TRUE;
            if (isOnline()) {
                Intent intent = new Intent(MainActivity.this, Page2Bis.class);
                al2 = new ArrayList(db.al);
                try {
                    System.out.println("test: " + al2.get(0));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            " Donnée en cours de chargement .",

                            Toast.LENGTH_SHORT).show();
                    ok=Boolean.FALSE;
                }
                intent.putStringArrayListExtra("value", al2);
                if(ok)
                    startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),
                        " Pas de connexion a internet",
                        Toast.LENGTH_SHORT).show();
            }
            /*al2 = new ArrayList(db.al);
            Intent intent2 = new Intent(MainActivity.this, Page2Bis.class);
            intent2.putExtra("theme","fun");
            intent2.putStringArrayListExtra("value", al2);
            startActivity(intent2);*/
        }
    };
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }




}