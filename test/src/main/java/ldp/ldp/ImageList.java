package ldp.ldp;

/**
 * Created by quentin on 27/06/2016.
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class ImageList extends AppCompatActivity {
    private ExpandListAdapter ExpAdapter;
    private ArrayList<Group> ExpListItems;
    private ExpandableListView ExpandList;
    private Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagelist);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        Intent intent = getIntent();
        toolbar.setTitle(" "+intent.getStringExtra("theme"));
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
        ExpandList = (ExpandableListView) findViewById(R.id.exp_list);

        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandListAdapter(ImageList.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
        final TextView textview =(TextView) findViewById(R.id.country_name);
        final ImageView imageView =(ImageView) findViewById(R.id.flag);

        ExpandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                //Intent intent2 = new Intent(ImageList.this, InternetPage.class);
                //intent2.putExtra("titre",String.valueOf(textview.getText()));
                //intent2.putExtra("image", (CharSequence) imageView.getDrawable());
                Toast.makeText(
                        getApplicationContext(),
                        "", Toast.LENGTH_SHORT
                ).show();

                //startActivity(intent2);
                return false;
            }
        });

    }

    public ArrayList<Group> SetStandardGroups() {

        String group_names[] = { "outils","communication","modulothèque","autre" };

        String images_name[] = { " "};

        int Images[] = {  };

        ArrayList<Group> list = new ArrayList<Group>();

        ArrayList<Child> ch_list;
        ch_list = new ArrayList<Child>();
    // ajout des info pour la partie com
        Group gru = new Group();
        gru.setName("commucnication");
        Child ch = new Child();ch.setName("Affiche café des sciences petits deb");ch.setImage(R.drawable.affiche_cafe_des_sciences_petits_debrouillards);ch_list.add(ch);
        Child ch2 = new Child();ch2.setName("l'air");ch2.setImage(R.drawable.air);ch_list.add(ch2);
        Child ch3 = new Child();ch3.setName("l'eau");ch3.setImage(R.drawable.eau);ch_list.add(ch3);
        Child ch4 = new Child();ch4.setName("Enquetes Scientifique");ch4.setImage(R.drawable.enquete_scientifique);ch_list.add(ch4);
        Child ch5 = new Child();ch5.setName("Festival sciencers metisse");ch5.setImage(R.drawable.festival_science_metisse);ch_list.add(ch5);
        Child ch6 = new Child();ch6.setName("Fete de la science");ch6.setImage(R.drawable.fete_de_la_science);ch_list.add(ch6);
        Child ch7 = new Child();ch7.setName("Le science tour");ch7.setImage(R.drawable.le_science_tour);ch_list.add(ch7);
        Child ch8 = new Child();ch8.setName("Les architectes du vivants");ch8.setImage(R.drawable.les_architectes_du_vivants);ch_list.add(ch8);
        Child ch9 = new Child();ch9.setName("Ramene ta science pas ta fraise");ch9.setImage(R.drawable.ramene_ta_science_pas_ta_fraise);ch_list.add(ch9);
        Child ch10 = new Child();ch10.setName("Science tour");ch10.setImage(R.drawable.science_tour);ch_list.add(ch10);
        Child ch11 = new Child();ch11.setName("Transition");ch11.setImage(R.drawable.transition);ch_list.add(ch11);
        //Child ch12 = new Child();ch.setName("l'eau");ch.setImage(R.drawable.eau);ch_list.add(ch12);
        gru.setItems(ch_list);
    //fin de l'ajout pour la partie com
        list.add(gru);
       /* int size = 4;
        int j = 0;

        for (String group_name : group_names) {
            Group gru = new Group();
            gru.setName(group_name);

            ch_list = new ArrayList<Child>();
            for (; j < size; j++) {
                Child ch = new Child();
                ch.setName(images_name[j]);
                ch.setImage(Images[j]);
                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);

            size = size + 4;
        }*/
        // ajout d'image dans la partie images , utiliser des goupes :
            // Group gru = new Group();
            // gru.setName(group_name);
        // utiliser les childs :
            // Child ch = new Child();
            // ch.setName(images_name[j]);
            // ch.setImage(Images[j]);
        // ajout des childs dans les group approprié pour cela ajouté les child dans une list pouis ajouté cette list au group
            // gru.setItems(ch_list);

        return list;
    }



}
