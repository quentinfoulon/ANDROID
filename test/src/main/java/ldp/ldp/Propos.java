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

/**
 * Created by quentin on 26/09/2016.
 */
public class Propos extends AppCompatActivity {
    private Toolbar toolbar;
    public EditText mdp;
    private EditText user;
    private Button ajout ;
    private Button zero ;
    private InputStream is = null;
    private Indentificateur2 db;
    private Boolean fin;
    private Intent intent2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent2 = new Intent(Propos.this, Gestion.class);

        //setContentView(R.layout.propos);
        setContentView(R.layout.connexion);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        Intent intent = getIntent();
        toolbar.setTitle("Version :0.9");


        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#939292"));
        }
        ajout = (Button) findViewById(R.id.okbutton);
        zero = (Button) findViewById(R.id.cancelbutton);
        mdp = (EditText) findViewById(R.id.password);
        user = (EditText) findViewById(R.id.username);
        ajout.setOnClickListener(ajoutListener);
        zero.setOnClickListener(zeroListener);
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
    private View.OnClickListener ajoutListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            // doit etre réalisé dans une background ou dans une asck tache comme DBconnexion
            Toast.makeText(
                    getApplicationContext(),
                    "je clique sur le bouton 2", Toast.LENGTH_SHORT
            ).show();
            String result = null;
            if (isOnline())
            {
                // Faire quelque chose si le périphérique est connecté
                db = new Indentificateur2(); db.execute(String.valueOf(user.getText()),String.valueOf(mdp.getText()));
                //db.onPostExecute(result);

                System.out.println("test"+result);
            }
            else
            {
                // Faire quelque chose s'il n'est pas connecté
            }

        }
    };
    private View.OnClickListener zeroListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
             Toast.makeText(
                    getApplicationContext(),
                    "je clique sur le bouton 1", Toast.LENGTH_SHORT
            ).show();
        }
    };

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public void test(boolean result){
        fin=result;



    }
    private class Indentificateur2 extends AsyncTask<String, Void, Boolean> {
        private InputStream is = null;
        public String resultat=null;
        public boolean resultat2;

        @Override
        protected Boolean doInBackground(String... params) {
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            String s = params[0];
            String result = "";
// L'année à envoyer
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username",s));

// Envoi de la requête avec HTTPPost
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://flnq.fr/pdf/login.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            }catch(Exception e){
                Log.e("log_tag", "Error in http connection "+e.toString());

            }

//Conversion de la réponse en chaine
            try{

                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();

                result=sb.toString();
                System.out.println(result);
            }catch(Exception e){
                Log.e("log_tag", "Error converting result "+e.toString());

            }

// Parsing des données JSON
            try{
                //System.out.println("test4");
                JSONArray jArray = new JSONArray(result);
                //System.out.println("test3");
                for(int i=0;i<jArray.length();i++){
                    //System.out.println("test1");
                    JSONObject json_data = jArray.getJSONObject(i);
                    //System.out.println("test2");
                    Log.i("log_tag","poste: "+json_data.getString("poste")+
                            ", username: "+json_data.getString("username")+
                            ", password: "+json_data.getString("password")+
                            ", nom: "+json_data.getString("nom")+
                            ", prenom: "+json_data.getString("prenom")
                    );
                    if (params[1].equals( json_data.getString("password")))
                        resultat2=true;
                    else
                        resultat2=false;
                    intent2.putExtra("poste", json_data.getString("poste"));
                    intent2.putExtra("username", json_data.getString("username"));
                    intent2.putExtra("nom", json_data.getString("nom"));
                    intent2.putExtra("prenom", json_data.getString("prenom"));
                    result=json_data.getString("poste")+";"+json_data.getString("username")+";"+json_data.getString("password")+";"+json_data.getString("nom")+";"+json_data.getString("prenom");
                }
            }
            catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
                result=null;
            }

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

            if (a) {

            intent2.putExtra("value", resultat);
            startActivity(intent2);
            }
            else{
                Toast.makeText(
                    getApplicationContext(),
                    "mot de passe ou identifiant incorrect", Toast.LENGTH_SHORT
                ).show();
            }

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
