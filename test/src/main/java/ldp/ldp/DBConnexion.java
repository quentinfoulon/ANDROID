package ldp.ldp;

import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class DBConnexion extends AsyncTask<Void, Void, ArrayList> {
    private  Statement statement ;
    public ResultSet résultats;
    public ArrayList al = new ArrayList();

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
    protected ArrayList doInBackground(Void... params) {
        Connection conn=null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver O.K.");
            String url = "jdbc:postgresql://dlx-server.com:5432/foulon"; // IP d'exemple
            DriverManager.setLoginTimeout(10);
            try {

                conn = DriverManager.getConnection(url, "foulon", "foulon59551");
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

       /* Création de l'objet gérant les requêtes */
        statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        requete();
        requetecountnumerique();
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList al) {
        /*
         *    do something with data here
         *    display it or send to mainactivity
         *    close any dialogs/ProgressBars/etc...
        */

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
                    System.out.println(soustheme);
                    String theme = résultats.getString( "theme" );
                    System.out.println(theme);
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

}