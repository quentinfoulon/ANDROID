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
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by quentin on 07/10/2016.
 */
public class Dispo extends AppCompatActivity {
    private Toolbar toolbar ;
    private Button poster;
    private CalendarView date;
    private TextView texte;
    private Indentificateur2 db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.dispo);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Dispo");
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

        date = (CalendarView) findViewById(R.id.calendarView);
        poster=(Button) findViewById(R.id.postdispo);
        texte=(TextView) findViewById(R.id.commentaireDispo);
        poster.setOnClickListener(suivantListener);
        //date.updateDate(2016,9,1);
        //date.date


    }
    private View.OnClickListener suivantListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (isOnline())
            {
                // Faire quelque chose si le périphérique est connecté
                //String dateS=null;
                Intent intent2 = getIntent();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String selectedDate = sdf.format(new Date(date.getDate()));
                System.out.println("date :"+selectedDate);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    date.setDateTextAppearance(Color.BLUE);

                }
                //dateS=String.valueOf(date.getDayOfMonth())+"-"+String.valueOf(date.getMonth()+1)+"-"+String.valueOf(date.getYear());
                //db = new Indentificateur2(); db.execute(intent2.getStringExtra("username"),String.valueOf(texte.getText()),selectedDate);
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
            String s = params[0];
            String result = "";
// L'année à envoyer
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username",s));
            nameValuePairs.add(new BasicNameValuePair("texte",params[1]));
            nameValuePairs.add(new BasicNameValuePair("date",params[2]));


// Envoi de la requête avec HTTPPost
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://flnq.fr/pdf/dispopost.php");
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
            Intent intent2 = getIntent();
            Intent intent = new Intent(Dispo.this, Gestion.class);
            intent.putExtra("value", intent2.getStringExtra("value"));
            intent.putExtra("poste", intent2.getStringExtra("poste"));
            intent.putExtra("username", intent2.getStringExtra("username"));
            intent.putExtra("nom", intent2.getStringExtra("nom"));
            intent.putExtra("prenom", intent2.getStringExtra("prenom"));
            intent.putExtra("theme","gestion");
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