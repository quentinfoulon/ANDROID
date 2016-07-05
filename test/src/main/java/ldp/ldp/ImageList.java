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


    public ImageList() {

    }
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
                        "cliquer sur l'image pour l'afficher", Toast.LENGTH_SHORT
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
        gru.setName("Commucnication");
        Child ch = new Child();ch.setName("Affiche café des sciences petits deb");ch.setImage(R.drawable.affiche_cafe_des_sciences_petits_debrouillards);ch_list.add(ch);
        Child ch2 = new Child();ch2.setName("l'air");ch2.setImage(R.drawable.air);ch_list.add(ch2);
        Child ch3 = new Child();ch3.setName("l'eau");ch3.setImage(R.drawable.eau);ch_list.add(ch3);
        Child ch4 = new Child();ch4.setName("Enquetes Scientifique");ch4.setImage(R.drawable.affiche_enquete_scientifique);ch_list.add(ch4);
        Child ch5 = new Child();ch5.setName("Festival sciences metisses");ch5.setImage(R.drawable.affiche_sciences_metisses_2_);ch_list.add(ch5);
        Child ch6 = new Child();ch6.setName("Fete de la science");ch6.setImage(R.drawable.fete_de_la_science);ch_list.add(ch6);
        Child ch7 = new Child();ch7.setName("Le science tour");ch7.setImage(R.drawable.le_science_tour);ch_list.add(ch7);
        Child ch8 = new Child();ch8.setName("Les architectes du vivants");ch8.setImage(R.drawable.les_architectes_du_vivants);ch_list.add(ch8);
        Child ch9 = new Child();ch9.setName("Ramene ta science pas ta fraise");ch9.setImage(R.drawable.ramene_ta_science_pas_ta_fraise);ch_list.add(ch9);
        //Child ch10 = new Child();ch10.setName("Science tour");ch10.setImage(R.drawable.sciencetour);ch_list.add(ch10);
        Child ch11 = new Child();ch11.setName("Transition");ch11.setImage(R.drawable.transition);ch_list.add(ch11);
        Child ch12 = new Child();ch12.setName("Club");ch12.setImage(R.drawable.evenementon174);ch_list.add(ch12);
        Child ch13 = new Child();ch13.setName("Mini-stage");ch13.setImage(R.drawable.ministage);ch_list.add(ch13);
        //Child ch12 = new Child();ch.setName("l'eau");ch.setImage(R.drawable.eau);ch_list.add(ch12);
        gru.setItems(ch_list);
        list.add(gru);
    //fin de l'ajout pour la partie com
        // ajout des info pour la partie modulothèque
            // partie biodiversité
        ArrayList<Child> ch_list2;
        ch_list2 = new ArrayList<Child>();
        Group gru2 = new Group();
        gru2.setName("Modulothèque Biodiversité");
        Child ch14 = new Child();ch14.setName("Difficile de faire le mur");ch14.setImage(R.drawable.difficiledefairelemur_biodiversite);ch_list2.add(ch14);
        Child ch15 = new Child();ch15.setName("Stands de Darwin");ch15.setImage(R.drawable.modulothequebiodiversite);ch_list2.add(ch15);
        Child ch16 = new Child();ch16.setName("Stands Concurrent ou associés");ch16.setImage(R.drawable.modulothequebiodiversite2);ch_list2.add(ch16);
        Child ch17 = new Child();ch17.setName("Stands Qui est mangé par qui");ch17.setImage(R.drawable.modulotheque_quiestmangepar_qui);ch_list2.add(ch17);
        gru2.setItems(ch_list2);
        list.add(gru2);
        // fin partie biodiversité
        // partie transition
        Group gru3 = new Group();
        gru3.setName("Modulothèque Transitions");
        ArrayList<Child> ch_list3;
        ch_list3 = new ArrayList<Child>();
        Child ch18 = new Child();ch18.setName("Stand Pollution");ch18.setImage(R.drawable.pollution_transition);ch_list3.add(ch18);
        Child ch19 = new Child();ch19.setName("Stand Assiette au pétrol");ch19.setImage(R.drawable.modulodd);ch_list3.add(ch19);
        Child ch20 = new Child();ch20.setName("Stand Passé d'une carotte");ch20.setImage(R.drawable.modulothequelepassedunecarotte);ch_list3.add(ch20);
        Child ch21 = new Child();ch21.setName("BSM fin mai 2015");ch21.setImage(R.drawable.modulothequebsmfinmai2015);ch_list3.add(ch21);
        Child ch22 = new Child();ch22.setName("BSM fin mai 2015 (2)");ch22.setImage(R.drawable.modulothequebsmfinmai20152);ch_list3.add(ch22);
        Child ch24 = new Child();ch24.setName("Stand boucle est bouclée");ch24.setImage(R.drawable.modulothequeboucleboucle_transition);ch_list3.add(ch24);

        gru3.setItems(ch_list3);
        list.add(gru3);
        // fin partie transition
        // partie Santé
        Group gru4 = new Group();
        gru4.setName("Modulothèque Santé");
        ArrayList<Child> ch_list4;
        ch_list4 = new ArrayList<Child>();
        Child ch23 = new Child();ch23.setName("Stand Livraison 24h/24h");ch23.setImage(R.drawable.modulothequebougetoncorps);ch_list4.add(ch23);
        Child ch25 = new Child();ch25.setName("Stand Cuisine ta santé");ch25.setImage(R.drawable.les_debrouillardscuisinent_ta_sante);ch_list4.add(ch25);
        Child ch33 = new Child();ch33.setName("Stand A l'eau");ch33.setImage(R.drawable.sante1);ch_list4.add(ch33);
        Child ch32 = new Child();ch32.setName("Stand Repas de cellules");ch32.setImage(R.drawable.sante2);ch_list4.add(ch32);

        gru4.setItems(ch_list4);
        list.add(gru4);
        // fin partie santé
        // partie Solidarité
        Group gru5 = new Group();
        gru5.setName("Modulothèque Solidarité");
        ArrayList<Child> ch_list5;
        ch_list5 = new ArrayList<Child>();
        Child ch26 = new Child();ch26.setName("Stand Les enfants ont des droits");ch26.setImage(R.drawable.solidarite1);ch_list5.add(ch26);
        Child ch27 = new Child();ch27.setName("Stand ce qui devait arriver areva");ch27.setImage(R.drawable.solidarite2);ch_list5.add(ch27);
        Child ch28 = new Child();ch28.setName("Stand Riches et pourtant pauvres");ch28.setImage(R.drawable.solidarite3);ch_list5.add(ch28);
        Child ch29 = new Child();ch29.setName("Stand Sur les pas des migrants");ch29.setImage(R.drawable.solidarite4);ch_list5.add(ch29);
        Child ch30 = new Child();ch30.setName("Stand Difficile de faire le mur");ch30.setImage(R.drawable.solidarite5);ch_list5.add(ch30);
        Child ch31 = new Child();ch31.setName("Stand Ou vive les migrants");ch31.setImage(R.drawable.solidarite6);ch_list5.add(ch31);

        gru5.setItems(ch_list5);
        list.add(gru5);
        // fin partie Solidarité
        // partie Solidarité
        Group gru6 = new Group();
        gru6.setName("Modulothèque Sexothèque");
        ArrayList<Child> ch_list6;
        ch_list6 = new ArrayList<Child>();
        Child ch34 = new Child();ch34.setName("Stand Appareil Genital Masculin ");ch34.setImage(R.drawable.sexo1);ch_list6.add(ch34);
        Child ch35 = new Child();ch35.setName("Stand Appareil Genital Feminin");ch35.setImage(R.drawable.sexo2);ch_list6.add(ch35);
        Child ch36 = new Child();ch36.setName("Stand Info-Intox Feminin");ch36.setImage(R.drawable.sexo3);ch_list6.add(ch36);
        Child ch37 = new Child();ch37.setName("Stand Info-Intox Masculin");ch37.setImage(R.drawable.sexo4);ch_list6.add(ch37);
        Child ch38 = new Child();ch38.setName("Stand Le Parcours du Combatant");ch38.setImage(R.drawable.sexo7);ch_list6.add(ch38);
        Child ch39 = new Child();ch39.setName("Stand Ovule contre Spermatozoide");ch39.setImage(R.drawable.sexo8);ch_list6.add(ch39);
        Child ch40 = new Child();ch40.setName("Stand Historique de l'Homosexualité");ch40.setImage(R.drawable.sexo6);ch_list6.add(ch40);
        Child ch41 = new Child();ch41.setName("Stand Les droits des femmes dans le temps");ch41.setImage(R.drawable.sexo5);ch_list6.add(ch41);
        gru6.setItems(ch_list6);
        list.add(gru6);
        // fin partie Solidarité
        // partie les architecte du vivant
        Group gru7 = new Group();
        gru7.setName("Modulothèque Architecte du Vivant");
        ArrayList<Child> ch_list7;
        ch_list7 = new ArrayList<Child>();
        Child ch42 = new Child();ch42.setName("Stand Corps sous controle");ch42.setImage(R.drawable.archi1);ch_list7.add(ch42);
        Child ch43 = new Child();ch43.setName("Stand Les cellules ,quelle peche");ch43.setImage(R.drawable.archi2);ch_list7.add(ch43);
        Child ch44 = new Child();ch44.setName("Stand Global");ch44.setImage(R.drawable.archi3);ch_list7.add(ch44);
        Child ch45 = new Child();ch45.setName("Stand La vie c'est quoi?");ch45.setImage(R.drawable.archi4);ch_list7.add(ch45);
        Child ch46 = new Child();ch46.setName("Stand La vie des cellules");ch46.setImage(R.drawable.archi5);ch_list7.add(ch46);

        gru7.setItems(ch_list7);
        list.add(gru7);
        // fin partie Solidarité


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
