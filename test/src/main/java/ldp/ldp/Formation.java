package ldp.ldp;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by quentin on 12/07/2016.
 */
public class Formation extends AppCompatActivity {

    private Toolbar toolbar ;
    private Button ajout ;
    private EditText code;
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
            File myFile = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "appli_test","idFormation.txt"); //on déclare notre futur fichier

            File myDir = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "appli_test"); //pour créer le repertoire dans lequel on va mettre notre fichier
            Boolean success=true;


                String data= String.valueOf(code.getText())+";";

                FileOutputStream output = null; //le true est pour écrire en fin de fichier, et non l'écraser
                try {
                    output = new FileOutputStream(myFile,true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    output.write(data.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileInputStream in = null;
            try {
                in = new FileInputStream(myFile);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int octet;
            String message="";
            try {
                while ((octet = in.read()) != -1) {
                    message = message +octet;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }
    };

}