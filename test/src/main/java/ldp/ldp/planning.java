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
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by quentin on 18/10/2016.
 */
public class Planning extends AppCompatActivity {
    private Toolbar toolbar ;
    private CalendarView date;
    private TextView mois;
    private String dateSelec;
    private Intent intent2;
    private String username;
    private String antenne;
    private Indentificateur2 db;
    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM/yyyy", Locale.FRENCH);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent2 = getIntent();
        username=intent2.getStringExtra("username");
        antenne=intent2.getStringExtra("antenne");
        setContentView(R.layout.planning);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
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

        db = new Indentificateur2(); db.execute(antenne);
        //date = (CalendarView) findViewById(R.id.calendarView);
        mois=(TextView) findViewById(R.id.mois);
        //date.updateDate(2016,9,1);
        //date.date
        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setLocale(TimeZone.getTimeZone("Europe/Paris"), Locale.FRANCE);
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
        //db2 = new Indentificateur3(); db2.execute(intent2.getStringExtra("username"));
        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            public  final String TAG = null;


            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String selectedDate = sdf.format(dateClicked);
                dateSelec=selectedDate;
                compactCalendarView.getEvents(dateClicked).size();
                System.out.println(compactCalendarView.getEvents(dateClicked).size());
                if(compactCalendarView.getEvents(dateClicked).size()>0){
                    Intent intent = new Intent(Planning.this, Planning2.class);
                    intent.putExtra("date", sdf2.format(dateClicked));
                    intent.putExtra("antenne", antenne);
                    intent.putExtra("theme","Planning");

                    startActivity(intent);
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mois.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
            }
        });


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
            nameValuePairs.add(new BasicNameValuePair("antenne",s));

// Envoi de la requête avec HTTPPost
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://flnq.fr/pdf/planning.php");
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
                    System.out.println(json_data.getString("date"));
                    //list.add(json_data.getString("titre"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date d=null;
                    try {
                        d = sdf.parse(json_data.getString("date"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (json_data.getString("type").equals("R")){
                    Event ev1 =new Event(Color.RED, d.getTime(),json_data.getString("commentaire") );
                    compactCalendarView.addEvent(ev1);
                    }else if (json_data.getString("type").equals("P")){
                        Event ev1 =new Event(Color.BLUE, d.getTime(),json_data.getString("commentaire") );
                        compactCalendarView.addEvent(ev1);
                    }else if (json_data.getString("type").equals("E")){
                        Event ev1 =new Event(Color.DKGRAY, d.getTime(),json_data.getString("commentaire") );
                        compactCalendarView.addEvent(ev1);
                    }

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
            //listeViewRemplir(list);
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
