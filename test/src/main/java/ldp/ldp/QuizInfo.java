package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by quentin on 22/06/2016.
 */
public class QuizInfo extends AppCompatActivity {
    private ImageView imageSwitcher;
    private RadioGroup radioGroup ;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private ProgressBar progressBar;
    private Button button;
    private int nombre;
    private Toolbar toolbar;
    private ArrayList<String> listeImage;
    private ArrayList<Integer> listeImage2;
    private int compteur;
    private int resultat;
    private int test;
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_info);
        Intent intent = getIntent();
        //ajout des toolbar avec leur bouton d'utilisation
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("atelier : quiz de l'info");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#939292"));
        }
        compteur=0;
        resultat=0;
        test=0;
        //action du retour a la page .
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imageSwitcher = (ImageView)findViewById(R.id.imageView);
        radioGroup =(RadioGroup) findViewById(R.id.radioGroup);
        radioButton1 =(RadioButton) findViewById(R.id.radioButton);
        radioButton2 =(RadioButton) findViewById(R.id.radioButton2);
        radioButton3 =(RadioButton) findViewById(R.id.radioButton3);
        button =(Button) findViewById(R.id.button);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
       // int imageIds[]=[R.drawable.ic_action_name,R.drawable.information2];
        //imageSwitcher.setImageResource(R.drawable.information2);

        progressBar.setMax(20);
        listeImage2=new ArrayList<Integer>();
        listeImage=new ArrayList<String>();
        button.setOnClickListener(valideListener);
        for(int i=0;i<6;i++)
            listeImage.add("information");
        for(int i=0;i<7;i++)
            listeImage.add("rumeur");
        for(int i=0;i<7;i++)
            listeImage.add("vie privee");

        listeImage2.add(R.drawable.information);
        listeImage2.add(R.drawable.information9);
        listeImage2.add(R.drawable.information_deux);
        listeImage2.add(R.drawable.information_trois);
        listeImage2.add(R.drawable.information_trois);
        listeImage2.add(R.drawable.information_quatres);
        listeImage2.add(R.drawable.information_cinq);
        listeImage2.add(R.drawable.rumeur1);
        listeImage2.add(R.drawable.rumeur2);
        listeImage2.add(R.drawable.rumeur3);
        listeImage2.add(R.drawable.rumeur4);
        listeImage2.add(R.drawable.rumeur5);
        listeImage2.add(R.drawable.rumeur6);
        listeImage2.add(R.drawable.rumeur7);
        listeImage2.add(R.drawable.vp1);
        listeImage2.add(R.drawable.vp2);
        listeImage2.add(R.drawable.vp3);
        listeImage2.add(R.drawable.vp4);
        listeImage2.add(R.drawable.vp5);
        listeImage2.add(R.drawable.vp6);
        listeImage2.add(R.drawable.vp7);

        moveImage();
    }

    private View.OnClickListener valideListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
        if(compteur<20){
            Toast.makeText(
                    getApplicationContext(),
                    "Réponse correcte : "+listeImage.get(nombre), Toast.LENGTH_SHORT
            ).show();
            if(listeImage.get(nombre).equals("information")){
                if(radioButton1.isChecked()) {
                    resultat++;
                    //System.out.println("vraie");
                }

            }
            if(listeImage.get(nombre).equals("rumeur")){
                if(radioButton2.isChecked())
                    resultat++;
            }
            if(listeImage.get(nombre).equals("vie privee")){
                if(radioButton3.isChecked())
                    resultat++;
            }
            moveImage();
        }
        else {
            if (test==0){
                if(listeImage.get(nombre).equals("information")){
                    if(radioButton1.isChecked()) {
                        resultat++;
                        //System.out.println("vraie");
                    }

                }
                if(listeImage.get(nombre).equals("rumeur")){
                    if(radioButton2.isChecked())
                        resultat++;
                }
                if(listeImage.get(nombre).equals("vie privee")){
                    if(radioButton3.isChecked())
                        resultat++;
                }
                test++;
            }else {
                Toast.makeText(
                        getApplicationContext(),
                        "Résultat " + resultat + "/20", Toast.LENGTH_SHORT
                ).show();
                button.setText("Afficher le résultat");
            }

        }


        }
    };

    public void moveImage(){
        if(compteur!=0) {
            listeImage2.remove(nombre);
            listeImage.remove(nombre);
        }
        if(compteur<20) {
            Random rand = new Random();
            nombre = rand.nextInt(20 - compteur); //Entre 0 et 19
            compteur++;
            imageSwitcher.setImageResource(listeImage2.get(nombre));
            progressBar.setProgress(compteur);
            System.out.println(compteur+"");

        }

    }
}
