package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static ldp.ldp.R.layout.activity_main;
import static ldp.ldp.R.layout.page2;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
         db = new DBConnexion(); db.execute();


        peda = (Button) findViewById(R.id.peda);
        media = (Button) findViewById(R.id.media);
        code = (Button) findViewById(R.id.code);
        fun = (Button) findViewById(R.id.fun);
        peda.setOnClickListener(pedaListener);
        code.setOnClickListener(codeListener);
        media.setOnClickListener(mediaListener);
        //peda.setOnTouchListener(pedaTListener);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private View.OnClickListener pedaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            Intent intent = new Intent(MainActivity.this, page2.class);
            al2=new ArrayList(db.al);
            try {
                System.out.println("test: " + al2.get(0));
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),
                        " Probleme de connection a la Base de données.",
                        Toast.LENGTH_SHORT).show();
            }
            intent.putStringArrayListExtra("value", al2);
            startActivity(intent);
        }
    };
    private View.OnClickListener codeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            Intent intent2 = new Intent(MainActivity.this, Change.class);
            intent2.putExtra("theme","code");
            intent2.putStringArrayListExtra("value", al2);
            startActivity(intent2);
        }
    };
    private View.OnClickListener mediaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            Intent intent2 = new Intent(MainActivity.this, DeroulanteList.class);
            intent2.putExtra("theme","media");
            intent2.putStringArrayListExtra("value", al2);
            startActivity(intent2);
        }
    };



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