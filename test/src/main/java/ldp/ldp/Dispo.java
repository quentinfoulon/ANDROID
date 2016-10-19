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

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by quentin on 07/10/2016.
 */
public class Dispo extends AppCompatActivity {
    private Toolbar toolbar ;
    private Button poster;
    private Button supprimer;
    private CalendarView date;
    private TextView texte;
    private TextView mois;
    private Indentificateur2 db;
    private Indentificateur1 db1;
    private Indentificateur3 db2;
    private String dateSelec;
    private Intent intent2;
    private String username;
    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM/yyyy", Locale.FRENCH);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent2 = getIntent();
        username=intent2.getStringExtra("username");
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

        //date = (CalendarView) findViewById(R.id.calendarView);
        mois=(TextView) findViewById(R.id.mois);
        poster=(Button) findViewById(R.id.postdispo);
        supprimer=(Button) findViewById(R.id.supdispo);
        texte=(TextView) findViewById(R.id.commentaireDispo);
        poster.setOnClickListener(suivantListener);
        supprimer.setOnClickListener(supprimerListener);
        //date.updateDate(2016,9,1);
        //date.date
         compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getTimeZone("Europe/Paris"),Locale.FRANCE);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        //final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        String format = "dd-MM-yyyy ";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();
        dateSelec=formater.format( date ) ;
        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
        // events has size 2 with the 2 events inserted previously
        //Log.d(TAG, "Events: " + events);
        mois.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        db2 = new Indentificateur3(); db2.execute(intent2.getStringExtra("username"));
        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            public  final String TAG = null;


            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String selectedDate = sdf.format(dateClicked);
                dateSelec=selectedDate;;
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mois.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
            }
        });


    }
    private View.OnClickListener suivantListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (isOnline())
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                db = new Indentificateur2(); db.execute(username,String.valueOf(texte.getText()),dateSelec);
            }
            else
            {
                // Faire quelque chose s'il n'est pas connecté
            }



        }
    };
    private View.OnClickListener supprimerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (isOnline())
            {

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                db1 = new Indentificateur1(); db1.execute(username,dateSelec);

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

    private class Indentificateur1 extends AsyncTask<String, Void, Boolean> {
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
            //nameValuePairs.add(new BasicNameValuePair("texte",params[1]));
            nameValuePairs.add(new BasicNameValuePair("date",params[1]));


// Envoi de la requête avec HTTPPost
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://flnq.fr/pdf/disposup.php");
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
            intent.putExtra("antenne", intent2.getStringExtra("antenne"));
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
            intent.putExtra("antenne", intent2.getStringExtra("antenne"));
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
    private class Indentificateur3 extends AsyncTask<String, Void, Boolean> {
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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date d=null;
                    try {
                         d = sdf.parse(json_data.getString("date"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    //Date date2 = formatter.format(d);
                    //System.out.println(d);
                    //long milliseconds = date2.getTime();;

                    Event ev1 =new Event(Color.GREEN, d.getTime(),json_data.getString("commentaire") );
                    compactCalendarView.addEvent(ev1);
                   //System.out.println(d.getTime());

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