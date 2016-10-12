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
 * Created by quentin on 02/06/2016.
 */
public class Gestion extends AppCompatActivity {

    private Button planning ;
    private Button dispo ;
    private Button bilan ;
    private Button propos ;
    private Toolbar toolbar ;
    private Indentificateur2 db;
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


            if (isOnline())
            {
                // Faire quelque chose si le périphérique est connecté
                db = new Indentificateur2(); db.execute();
                //db.onPostExecute(result);

                System.out.println("test");
            }
            else
            {
                // Faire quelque chose s'il n'est pas connecté
            }

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
            finish();
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
    private class Indentificateur2 extends AsyncTask<String, Void, Boolean> {
        private InputStream is = null;
        public String resultat=null;
        public boolean resultat2;
        private Intent intent=null;
        private Intent intent2=null;
        public ArrayList al = new ArrayList();
        @Override
        protected Boolean doInBackground(String... params) {
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------

            intent2=getIntent();
            intent = new Intent(Gestion.this, Dispo.class);
            String result = "";
// L'année à envoyer
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username",intent2.getStringExtra("username")));

// Envoi de la requête avec HTTPPost
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://flnq.fr/pdf/dispo.php");
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
                    Log.i("log_tag","poste: "+
                            ", username: "+json_data.getString("username")+
                            ", date: "+json_data.getString("date")+
                            ", commentaire: "+json_data.getString("commentaire")

                    );
                    al.add(json_data.getString("username")+";"+json_data.getString("date")+";"+json_data.getString("commentaire")+";");

                    //result=json_data.getString("date");
                }
            }
            catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
                result=null;
            }

            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------


            return resultat2;
        }
        @Override
        protected void onPostExecute(Boolean a) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */


            Intent intent2 = getIntent();
            intent.putIntegerArrayListExtra("liste",al);
            intent.putExtra("value", intent2.getStringExtra("value"));
            intent.putExtra("poste", intent2.getStringExtra("poste"));
            intent.putExtra("username", intent2.getStringExtra("username"));
            intent.putExtra("nom", intent2.getStringExtra("nom"));
            intent.putExtra("prenom", intent2.getStringExtra("prenom"));
            intent.putExtra("theme","dispo");
            finish();
            startActivity(intent);


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