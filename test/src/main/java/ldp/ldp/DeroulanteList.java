package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by quentin on 02/06/2016.
 */
public class DeroulanteList extends AppCompatActivity {

    private Toolbar toolbar ;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    HashMap<String, String> listlien;
    WebView webview;
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getStringExtra("theme").equals("formation"))
            setContentView(R.layout.formation);
        else
            setContentView(R.layout.deroulantelist);

        Intent intent = getIntent();
        if(intent.getStringExtra("theme").equals("code")){
            //ajout des toolbar avec leur bouton d'utilisation
            toolbar = (Toolbar) findViewById(R.id.my_toolbar);
            toolbar.setTitle("atelier : educo-code");
            setSupportActionBar(toolbar);
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
        }else if(intent.getStringExtra("theme").equals("media")){
            //ajout des toolbar avec leur bouton d'utilisation
            toolbar = (Toolbar) findViewById(R.id.my_toolbar);
            toolbar.setTitle("atelier : educoMedia");
            setSupportActionBar(toolbar);
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
        }else if(intent.getStringExtra("theme").equals("media")){
            //ajout des toolbar avec leur bouton d'utilisation
            toolbar = (Toolbar) findViewById(R.id.my_toolbar);
            toolbar.setTitle("atelier : educoMedia");
            setSupportActionBar(toolbar);
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
        }
        else if(intent.getStringExtra("theme").equals("formation")){
            //ajout des toolbar avec leur bouton d'utilisation
            toolbar = (Toolbar) findViewById(R.id.my_toolbar);
            toolbar.setTitle("Formation");
            setSupportActionBar(toolbar);
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
            ArrayList<String> al2 = new ArrayList(intent.getStringArrayListExtra("value"));
            ArrayList<String> alTitre = new ArrayList<String>();
            String test[] = {"test", "test2"};
            String tabResume[][] = new String[al2.size()][4];

            expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
            expandableListDetail = ExpandableListDataPump.getData(al2, intent.getStringExtra("theme"));
            listlien = ExpandableListDataPump.getlien();
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
                    Intent intent2;
                    Intent intent = getIntent();
                    if(intent.getStringExtra("theme").equals("video"))
                        intent2 = new Intent(DeroulanteList.this, AfficheInternet.class);
                    else
                        intent2 = new Intent(DeroulanteList.this, InternetPage.class);
                    intent2.putExtra("url", listlien.get(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition)));
                    intent2.putExtra("nom", expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                    startActivity(intent2);
                    return false;
                }
            });
        }
        else if(intent.getStringExtra("theme").equals("extra")){
            //ajout des toolbar avec leur bouton d'utilisation
            toolbar = (Toolbar) findViewById(R.id.my_toolbar);
            toolbar.setTitle("fun : Extra");
            setSupportActionBar(toolbar);
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
        }
        else {
            toolbar = (Toolbar) findViewById(R.id.my_toolbar);
            if(intent.getStringExtra("theme").equals("video"))
                toolbar.setTitle("fun : " + intent.getStringExtra("theme"));
            else
                toolbar.setTitle("Fiche Peda : " + intent.getStringExtra("theme"));
            //definir notre toolbar en tant qu'actionBar
            setSupportActionBar(toolbar);
            //afficher le bouton retour
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.parseColor("#939292"));
            }


            ArrayList<String> al2 = new ArrayList(intent.getStringArrayListExtra("value"));
            ArrayList<String> alTitre = new ArrayList<String>();
            String test[] = {"test", "test2"};
            String tabResume[][] = new String[al2.size()][4];

            expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
            expandableListDetail = ExpandableListDataPump.getData(al2, intent.getStringExtra("theme"));
            listlien = ExpandableListDataPump.getlien();
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
                    Intent intent2;
                    Intent intent = getIntent();
                    if(intent.getStringExtra("theme").equals("video"))
                         intent2 = new Intent(DeroulanteList.this, AfficheInternet.class);
                    else
                         intent2 = new Intent(DeroulanteList.this, InternetPage.class);
                    intent2.putExtra("url", listlien.get(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition)));
                    intent2.putExtra("nom", expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
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


}
