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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import static ldp.ldp.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Button media ;
    private Button peda ;
    private Button code ;
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
        code = (Button) findViewById(R.id.code);
        fun = (Button) findViewById(R.id.fun);
        peda.setOnClickListener(pedaListener);
        code.setOnClickListener(codeListener);
        media.setOnClickListener(mediaListener);
        fun.setOnClickListener(funListener);
        //peda.setOnTouchListener(pedaTListener);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
            Intent intent2 = new Intent(MainActivity.this, Listecode.class);
            intent2.putExtra("theme","code");
            intent2.putStringArrayListExtra("value", al2);
            startActivity(intent2);
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
            al2 = new ArrayList(db.al);
            Intent intent2 = new Intent(MainActivity.this, Page2Bis.class);
            intent2.putExtra("theme","fun");
            intent2.putStringArrayListExtra("value", al2);
            startActivity(intent2);
        }
    };
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }



    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://ldp.ldp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://ldp.ldp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}