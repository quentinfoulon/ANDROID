package ldp.ldp;

import android.os.AsyncTask;
import android.util.Log;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBConnexion extends AsyncTask<Void, Void, Boolean> {
    private InputStream is = null;
    private  Statement statement ;
    public ResultSet résultats;
    public ArrayList al = new ArrayList();
    public ArrayList al2 = new ArrayList();

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

    @Override
    protected Boolean doInBackground(Void... params) {
        Connection conn=null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver O.K.");
            String url = "jdbc:postgresql://91.121.178.210:5432/educomedia"//+"ssl=true"+
                   // "sslfactory=org.postgresql.ssl.NonValidatingFactory"
                    ; // IP d'exemple
            Properties props = new Properties();
            props.setProperty("user","");
            props.setProperty("password","");
            props.setProperty("ssl","false");
            DriverManager.setLoginTimeout(1);
            try {

                //conn = DriverManager.getConnection(url, "foulon", "foulon59551");
                 conn = DriverManager.getConnection(url, props);
                System.out.println("connexion O.K.");
            }
            catch(SQLException i){

                System.out.println("connexion k.o.");
                i.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        résultats = null;
        String requete = "select * from public.theme";

        statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //requete();
        requetecountnumerique();
        //webservice();
        return null;
    }

    @Override
    protected void onPostExecute(Boolean a) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */
        a=Boolean.TRUE;

    }
    protected void requete() {
        try {
            résultats = statement.executeQuery("select * from public.theme ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {

            while ( résultats.next() ) {

                try {
                    String soustheme = résultats.getString( "soustheme" );
                    //System.out.println(soustheme);
                    String theme = résultats.getString( "theme" );
                    //System.out.println(theme);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected ArrayList requetecountnumerique() {
        int i=0;
        int indice=0;

        try {
            résultats = statement.executeQuery("select * from public.contenu  ;");

            while ( résultats.next() ) {
                al.add(résultats.getString("theme")+";"+résultats.getString("soustheme")+";"+résultats.getString("titre")+";"+résultats.getString("lien"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
        //String tabnumerique[][] = new String[soustheme][];
    }
    protected ArrayList requetelien() {
        int i=0;
        int indice=0;

        try {
            résultats = statement.executeQuery("select * from public.lien  ;");

            while ( résultats.next() ) {
                al2.add(résultats.getString("group")+";"+résultats.getString("titre")+";"+résultats.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
        //String tabnumerique[][] = new String[soustheme][];
    }
    protected  void webservice(){
        String result = "";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

// Envoi de la requête avec HTTPPost
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://flnq.fr/pdf/pdf.php");
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
                al.add(json_data.getString("theme")+";"+json_data.getString("soustheme")+";"+json_data.getString("titre")+";"+json_data.getString("lien"));

                //result=json_data.getString("date")+";"+json_data.getString("username")+";"+json_data.getString("password")+";"+json_data.getString("nom")+";"+json_data.getString("prenom");
            }
        }
        catch(JSONException e){
            Log.e("log_tag", "Error parsing data "+e.toString());
            result=null;
        }

    }

}