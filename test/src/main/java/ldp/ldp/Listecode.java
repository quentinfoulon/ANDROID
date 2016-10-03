package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by quentin on 13/06/2016.
 */
public class Listecode extends AppCompatActivity {

    private Toolbar toolbar ;
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.listcode);
        Intent intent = getIntent();
        if(intent.getStringExtra("theme").equals("code")) {
            toolbar = (Toolbar) findViewById(R.id.my_toolbar);
            toolbar.setTitle("Educo code");
            //definir notre toolbar en tant qu'actionBar
            setSupportActionBar(toolbar);
            //afficher le bouton retour
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.parseColor("#939292"));
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            //------------------modifiacation de la list des activité  pour le educocode--------------------------
            final ArrayList<String> list = new ArrayList<String>();
            list.add("convertiseur");
            list.add("code cesar");
            list.add("code vigenere");
            final ListView listview = (ListView) findViewById(R.id.listView);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, list);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {

                    String item = ((TextView) view).getText().toString();

                    Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                    if (item.equals("convertiseur")) {
                        Intent intent2 = new Intent(Listecode.this, Change.class);
                        intent2.putExtra("theme", "code");
                        startActivity(intent2);
                    } else if (item.equals("code cesar")) {
                        Intent intent2 = new Intent(Listecode.this, CryptageCesar.class);
                        intent2.putExtra("theme", "code");
                        startActivity(intent2);
                    } else if (item.equals("code vigenere")) {
                        Intent intent2 = new Intent(Listecode.this, CryptageVigenere.class);
                        intent2.putExtra("theme", "code");
                        startActivity(intent2);
                    }


                }
            });
        }else if (intent.getStringExtra("theme").equals("media")){
            toolbar = (Toolbar) findViewById(R.id.my_toolbar);
            toolbar.setTitle("Educo-media");
            //definir notre toolbar en tant qu'actionBar
            setSupportActionBar(toolbar);
            //afficher le bouton retour
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.parseColor("#939292"));
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            //------------------modifiacation de la list des activité  pour le educomedia--------------------------
            final ArrayList<String> list = new ArrayList<String>();
            list.add("quiz de l'info");
            list.add("quiz des logos");
            list.add("quiz des Stéréotypes");
            list.add("convertiseur");
            list.add("code cesar");
            list.add("code vigenere");
            //list.add("site Internet");
            final ListView listview = (ListView) findViewById(R.id.listView);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, list);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {

                    String item = ((TextView) view).getText().toString();

                    Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                    if (item.equals("quiz de l'info")) {
                        Intent intent2 = new Intent(Listecode.this, QuizInfo.class);
                        intent2.putExtra("theme", "media");
                        startActivity(intent2);
                    }
                    else if (item.equals("quiz des logos")) {
                        Intent intent2 = new Intent(Listecode.this, QuizLogo.class);
                        intent2.putExtra("theme", "media");
                        startActivity(intent2);
                    }
                    else if (item.equals("quiz des Stéréotypes")) {
                        Intent intent2 = new Intent(Listecode.this, QuizStereo.class);
                        intent2.putExtra("theme", "media");
                        startActivity(intent2);
                    }
                    else if (item.equals("convertiseur")) {
                        Intent intent2 = new Intent(Listecode.this, Change.class);
                        intent2.putExtra("theme", "code");
                        startActivity(intent2);
                    } else if (item.equals("code cesar")) {
                        Intent intent2 = new Intent(Listecode.this, CryptageCesar.class);
                        intent2.putExtra("theme", "code");
                        startActivity(intent2);
                    } else if (item.equals("code vigenere")) {
                        Intent intent2 = new Intent(Listecode.this, CryptageVigenere.class);
                        intent2.putExtra("theme", "code");
                        startActivity(intent2);
                    }


                }
            });
        }
    }
}
