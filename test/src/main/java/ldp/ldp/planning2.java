package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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

/**
 * Created by quentin on 14/10/2016.
 */
public class Planning2 extends AppCompatActivity {
    private Toolbar toolbar;
    private Indentificateur2 db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //intent2 = new Intent(Propos.this, Gestion.class);

        //setContentView(R.layout.propos);
        setContentView(R.layout.historique);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        Intent intent = getIntent();
        toolbar.setTitle("Planning");


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

        System.out.println(intent.getStringExtra("date"));

        // Faire quelque chose si le périphérique est connecté
        db = new Indentificateur2(); db.execute(intent.getStringExtra("date"),intent.getStringExtra("antenne"));
        //db.onPostExecute(result);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }
    private void listeViewRemplir(ArrayList<Map<String, String>> test){
        //System.out.println( test.get(0));
        final ListView listview = (ListView) findViewById(R.id.listViewHisto);
        final String[] fromMapKey = new String[] {"text1", "text2"};
        final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
        ListAdapter adapter =new SimpleAdapter(this, test,
                android.R.layout.simple_list_item_2,
                fromMapKey, toLayoutId);
        listview.setAdapter(adapter);

    }

    private class Indentificateur2 extends AsyncTask<String, Void, Boolean> {
        private InputStream is = null;
        public String resultat=null;
        public boolean resultat2;
        private ArrayList<Map<String, String>> list;

        @Override
        protected Boolean doInBackground(String... params) {
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------
            String s = params[0];
            String result = "";
            list = new ArrayList<Map<String, String>>();

// L'année à envoyer
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("date",s));
            nameValuePairs.add(new BasicNameValuePair("antenne",params[1]));

// Envoi de la requête avec HTTPPost
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://flnq.fr/pdf/planning2.php");
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
                    final Map<String, String> listItemMap = new HashMap<String, String>();
                    listItemMap.put("text1",json_data.getString("titre") );
                    System.out.println(json_data.getString("date"));
                    String type=null;
                    if(json_data.getString("type").equals("R")){
                        type="Regulier";
                    }else if(json_data.getString("type").equals("P")){
                        type="Ponctuelle";
                    }else if(json_data.getString("type").equals("E")){
                        type="Evenementiel";
                    }
                    if(json_data.getString("commentaire").equals("null")||json_data.getString("commentaire").equals(null))
                        listItemMap.put("text2","le "+json_data.getString("date")+" de "+json_data.getString("horaire")+" à "+json_data.getString("ville")+"\nAntenne de "+json_data.getString("antenne")+"\nActivité de type : "+type );
                    else
                        listItemMap.put("text2","le "+json_data.getString("date")+" de "+json_data.getString("horaire")+" à "+json_data.getString("ville")+"\nAntenne de "+json_data.getString("antenne")+"\nActivité de type : "+type+"\n"+"Commentaire :\n"+json_data.getString("commentaire") );
                    list.add(Collections.unmodifiableMap(listItemMap));


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
            listeViewRemplir(list);
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
