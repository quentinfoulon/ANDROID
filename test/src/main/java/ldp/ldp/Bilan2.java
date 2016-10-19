package ldp.ldp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by quentin on 07/10/2016.
 */
public class Bilan2 extends AppCompatActivity {
    private Toolbar toolbar ;
    private EditText texte;
    private Button poster;
    private Indentificateur2 db;
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
            intent.putExtra("antenne", intent2.getStringExtra("antenne"));
            intent.putExtra("theme","gestion");
            finish();
            startActivity(intent);
            if (isOnline())
            {
                // Faire quelque chose si le périphérique est connecté
                String date=null;
                date=""+intent2.getStringExtra("jour")+"-"+intent2.getStringExtra("mois")+"-"+intent2.getStringExtra("annee");
                db = new Indentificateur2(); db.execute(String.valueOf(texte.getText()),intent2.getStringExtra("username"),intent2.getStringExtra("titre"),intent2.getStringExtra("adresse"),date,intent2.getStringExtra("participant"));
                //db.onPostExecute(result);

                //System.out.println("test"+result);
            }
            else
            {
                // Faire quelque chose s'il n'est pas connecté
            }



        }
    };
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private class Indentificateur2 extends AsyncTask<String, Void, Boolean> {
        private InputStream is = null;
        public String resultat=null;
        public boolean resultat2;
        private Intent intent3 = getIntent();
        @Override
        protected Boolean doInBackground(String... params) {
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            String s = params[1];
            String result = "";
// L'année à envoyer
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username",s));
            nameValuePairs.add(new BasicNameValuePair("texte",params[0]));
            nameValuePairs.add(new BasicNameValuePair("titre",params[2]));
            nameValuePairs.add(new BasicNameValuePair("adresse",params[3]));
            nameValuePairs.add(new BasicNameValuePair("date",params[4]));
            nameValuePairs.add(new BasicNameValuePair("participants",params[5]));

// Envoi de la requête avec HTTPPost
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://flnq.fr/pdf/bilan.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            }catch(Exception e){
                Log.e("log_tag", "Error in http connection "+e.toString());

            }

//Conversion de la réponse en chaine


// Parsing des données JSON


            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            resultat=result;

            return resultat2;
        }
        @Override
        protected void onPostExecute(Boolean a) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */


        }
        @Override
        protected void onPreExecute() {
        /*
         *    do things before doInBackground() code runs
         *    such as preparing and showing a Dialog or ProgressBar
        */
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        /*
         *    updating data
         *    such a Dialog or ProgressBar
        */

        }
    }
}
