package ldp.ldp;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by quentin on 12/07/2016.
 */
public class Formation extends AppCompatActivity {

    private Toolbar toolbar ;
    private Button ajout ;
    private EditText code;
    private String texte;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.formation);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Fiche Formation");
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
        ajout = (Button) findViewById(R.id.ajoutCode);
        code = (EditText) findViewById(R.id.editTextCode);
        ajout.setOnClickListener(ajoutListener);

    }
    private View.OnClickListener ajoutListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            Toast.makeText(
                    getApplicationContext(),
                    "je clique sur le bouton", Toast.LENGTH_SHORT
            ).show();


            // ecriture sur le fichier de sauvegarde

            String FILENAME = "file";
            String content = String.valueOf(code.getText())+";";

            FileOutputStream fos = null;
            try {
                fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fos.write(content.getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
               }
            FileInputStream fos2 = null;
            try {
                 fos2 =  openFileInput(FILENAME);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String temp="";
            int c;
            try {
                while( (c = fos2.read()) != -1){
                    temp = temp + Character.toString((char)c);
                }
                //et.setText(temp);
                Toast.makeText(getBaseContext(),"Lecture fichier"+"  "+temp,
                        Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}