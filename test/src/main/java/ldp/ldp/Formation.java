package ldp.ldp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by quentin on 12/07/2016.
 */
public class Formation extends AppCompatActivity {

    private Toolbar toolbar ;
    private Button ajout ;
    private Button zero ;
    private EditText code;
    private String texte;
    private String FILENAME = "file";
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    List<String> expandableListTitle2;
    HashMap<String, List<String>> expandableListDetail;
    HashMap<String, String> listlien;
    WebView webview;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.formation);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Fiche Formation");
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        //afficher le bouton retour.
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
        zero = (Button) findViewById(R.id.zero);
        code = (EditText) findViewById(R.id.editTextCode);
        ajout.setOnClickListener(ajoutListener);
        zero.setOnClickListener(zeroListener);

        affiche();

    }
    private View.OnClickListener zeroListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {



            // ecriture sur le fichier de sauvegarde


            String content = String.valueOf(code.getText())+";";

            FileOutputStream fos = null;
            try {
                fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
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
        //lire();
            affiche();
        }
    };
    private View.OnClickListener ajoutListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
           /* Toast.makeText(
                    getApplicationContext(),
                    "je clique sur le bouton", Toast.LENGTH_SHORT
            ).show();*/


            // ecriture sur le fichier de sauvegarde


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
        //lire();
            affiche();
        }
    };
    protected String  lire() {
        FileInputStream fos2 = null;
        // permet de créée le fichier si il n'est pas present
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //fin de creation
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
           /* Toast.makeText(getBaseContext(),"Lecture fichier"+"  "+temp,
                    Toast.LENGTH_SHORT).show();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String tabnumerique[][] = new String[soustheme][];

        return temp;
    }

    protected void  affiche() {
        Intent intent = getIntent();
        ArrayList<String> al2 = new ArrayList(intent.getStringArrayListExtra("value"));
        ArrayList<String> alTitre = new ArrayList<String>();
        String test[] = {"test", "test2"};
        String tabResume[][] = new String[al2.size()][4];

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData(al2, intent.getStringExtra("theme"));
        listlien = ExpandableListDataPump.getlien();
        System.out.println("" + intent.getStringExtra("theme") + "");
        String tabSplit[]=  lire().split(";");
        expandableListTitle2 = new ArrayList<String>(expandableListDetail.keySet());
        expandableListTitle =new ArrayList<String>();
        boolean trouve;

        for(int i=0; i<tabSplit.length; i++) {
            trouve = false;
            for(int j=0; j<expandableListTitle2.size(); j++) {
                if(tabSplit[i].equals(expandableListTitle2.get(j))) {
                    // si les deux chaines correspondent, on met la variable à true et on interrompe la boucle
                    trouve = true;
                    expandableListTitle.add(tabSplit[i]);
                    break;
                }
            }
            if (trouve == false) {
                // dans ce cas, la chaine n'existe pas dans s2

            }
        }
       // if(expandableListTitle.size()!=0) {
            expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
            expandableListView.setAdapter(expandableListAdapter);
            expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    Toast.makeText(getApplicationContext(),
                            expandableListTitle.get(groupPosition) + " Liste étendue .",
                            Toast.LENGTH_SHORT).show();
                }
            });

            expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                @Override
                public void onGroupCollapse(int groupPosition) {
                    Toast.makeText(getApplicationContext(),
                            expandableListTitle.get(groupPosition) + " Liste réduite.",
                            Toast.LENGTH_SHORT).show();

                }
            });

            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    //listlien.get(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                    Toast.makeText(
                            getApplicationContext(),
                            expandableListTitle.get(groupPosition)
                                    + " -> "
                                    + listlien.get(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition)), Toast.LENGTH_SHORT
                    ).show();
                    Intent intent2;
                    Intent intent = getIntent();
                    if (intent.getStringExtra("theme").equals("video"))
                        intent2 = new Intent(Formation.this, AfficheInternet.class);
                    else
                        intent2 = new Intent(Formation.this, InternetPage.class);
                    intent2.putExtra("url", listlien.get(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition)));
                    intent2.putExtra("nom", expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                    startActivity(intent2);
                    return false;
                }
            });

       // }
    }

}