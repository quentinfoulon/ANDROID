package ldp.ldp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    private DBConnexion db2;
    private Boolean test;
    private  ArrayList al3 ;
    private Indentificateur2 db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        if (isOnline())
        {
            // Faire quelque chose si le périphérique est connecté
            //db2 = new DBConnexion(); db2.execute();
            if(test==null) {
                al3 = new ArrayList();
                db = new Indentificateur2();
                db.execute();
            }

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

            if (isOnline()) {
                Intent intent = new Intent(MainActivity.this, page2.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);//.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);;
                if(test){
                    intent.putStringArrayListExtra("value", al3);
                    //finish();

                    startActivity(intent);}
                else{Toast.makeText(getApplicationContext(),
                        " Donnée en cours de chargement .",

                        Toast.LENGTH_SHORT).show();}
            }else{
                Toast.makeText(getApplicationContext(),
                        " Pas de connexion a internet",
                        Toast.LENGTH_SHORT).show();
            }

        }
    };
    private View.OnClickListener codeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            //setContentView(page2);
            if (isOnline()) {
                Intent intent = new Intent(MainActivity.this, Formation.class);
                if(test){
                    intent.putStringArrayListExtra("value", al3);
                    finish();
                    startActivity(intent);}
                else{Toast.makeText(getApplicationContext(),
                        " Donnée en cours de chargement .",

                        Toast.LENGTH_SHORT).show();}
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
            if (isOnline()) {
                Intent intent = new Intent(MainActivity.this, Page2Bis.class);
                if(test){
                    intent.putStringArrayListExtra("value", al3);
                    //finish();
                    startActivity(intent);}
                else{Toast.makeText(getApplicationContext(),
                        " Donnée en cours de chargement .",

                        Toast.LENGTH_SHORT).show();}
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


    private class Indentificateur2 extends AsyncTask<String, Void, Boolean> {
        private InputStream is = null;
        public String resultat=null;
        public boolean resultat2;
        //private ArrayList<Map<String, String>> list;

        @Override
        protected Boolean doInBackground(String... params) {
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            //String s = params[0];
            String result = "";
            //list = new ArrayList<Map<String, String>>();

// L'année à envoyer
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            //nameValuePairs.add(new BasicNameValuePair("username",s));

// Envoi de la requête avec HTTPPost
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://flnq.fr/pdf/pdf.php");
                //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
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
                //System.out.println(result);
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
                    //list.add(json_data.getString("titre"));
                    al3.add(json_data.getString("theme")+";"+json_data.getString("soustheme")+";"+json_data.getString("titre")+";"+json_data.getString("lien"));



                    //result=json_data.getString("date")+";"+json_data.getString("username")+";"+json_data.getString("password")+";"+json_data.getString("nom")+";"+json_data.getString("prenom");
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
            test=true;
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