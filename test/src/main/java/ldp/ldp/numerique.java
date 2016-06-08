package ldp.ldp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ldp.ldp.R.layout.page2;

import static ldp.ldp.R.layout.activity_main;

/**
 * Created by quentin on 02/06/2016.
 */
public class numerique extends AppCompatActivity {

    private Toolbar toolbar ;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    HashMap<String, String> listlien;
    WebView webview;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numerique);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        Intent intent = getIntent();
        toolbar.setTitle("Fiche Peda : "+intent.getStringExtra("theme"));
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(Color.parseColor("#939292"));


        ArrayList<String> al2= new ArrayList(intent.getStringArrayListExtra("value"));
        ArrayList<String> alTitre=new ArrayList<String>();
        String test[] = {"test","test2"};
        String tabResume[][]=new String[al2.size()][4];
       /* int indice =0 ,i=0 ;
        for(String elem: al2)
        {
            String tabSplit[]=  elem.split(";");
            for(i = 0 ; i < 4 ; i++){
                tabResume[indice][i]=tabSplit[i];
                if(i==1){
                    if(!alTitre.contains(tabSplit[i]))
                        alTitre.add(tabSplit[i]);
                }
            }
               indice++;
        }
        i=0;
        //probleme sur le getString arraylistextra
        // la récuperation du arraylist ne marche pas
        ListView mListView = (ListView)findViewById(R.id.listViewnumerique);

        String tabTitre[]= new String[alTitre.size()];
        for(String elem: alTitre){
            tabTitre[i]=elem;
            i++;
        }
        System.out.println("" + tabTitre[0] + "");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(numerique.this,
                android.R.layout.simple_list_item_1, tabTitre);
        mListView.setAdapter(adapter);*/


        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData(al2,intent.getStringExtra("theme"));
        listlien= ExpandableListDataPump.getlien();
        System.out.println("" + intent.getStringExtra("theme") + "");
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
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
                Intent intent2 = new Intent(numerique.this, InternetPage.class);
                intent2.putExtra("url",listlien.get(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition)));
                intent2.putExtra("nom",expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                startActivity(intent2);
                return false;
            }
        });
        //action du retour a la page .
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}