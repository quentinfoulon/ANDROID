package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by quentin on 22/06/2016.
 */
public class QuizLogo extends AppCompatActivity {
    private ImageView imageSwitcher;
    private ProgressBar progressBar;
    private Button button;
    private int nombre;
    private Toolbar toolbar;
    private ArrayList<String> listeImage;
    private ArrayList<Integer> listeImage2;
    private EditText edittext1 ;
    private int compteur;
    private int resultat;
    private int test;
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_logo);
        Intent intent = getIntent();
        //ajout des toolbar avec leur bouton d'utilisation
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("atelier : quiz des Logos");
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
        button =(Button) findViewById(R.id.button);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        edittext1 = (EditText)findViewById(R.id.editText4);
        // int imageIds[]=[R.drawable.ic_action_name,R.drawable.information2];
        //imageSwitcher.setImageResource(R.drawable.information2);

        progressBar.setMax(20);
        listeImage2=new ArrayList<Integer>();
        listeImage=new ArrayList<String>();
        button.setOnClickListener(valideListener);


        listeImage2.add(R.drawable.adidas);listeImage.add("addidas");
        listeImage2.add(R.drawable.chanel);listeImage.add("chanel");
        listeImage2.add(R.drawable.disneyland);listeImage.add("disneyland");
        listeImage2.add(R.drawable.facebook);listeImage.add("facebook");
        listeImage2.add(R.drawable.google);listeImage.add("google");
        listeImage2.add(R.drawable.icewatch);listeImage.add("ice watch");
        listeImage2.add(R.drawable.instagram);listeImage.add("instagram");
        listeImage2.add(R.drawable.iphone);listeImage.add("iphone");

        listeImage2.add(R.drawable.kfc);listeImage.add("kfc");
        listeImage2.add(R.drawable.levis);listeImage.add("levis");
        listeImage2.add(R.drawable.mcdo);listeImage.add("mc do");
        listeImage2.add(R.drawable.nike);listeImage.add("nike");
        listeImage2.add(R.drawable.nutella);listeImage.add("nutella");
        listeImage2.add(R.drawable.pepsi);listeImage.add("pepsi");
        listeImage2.add(R.drawable.playstation);listeImage.add("playstation");
        listeImage2.add(R.drawable.prince);listeImage.add("prince");

        listeImage2.add(R.drawable.ps4);listeImage.add("ps4");
        listeImage2.add(R.drawable.puma);listeImage.add("puma");
        listeImage2.add(R.drawable.quick);listeImage.add("quick");
        listeImage2.add(R.drawable.samsung);listeImage.add("samsung");
        listeImage2.add(R.drawable.snap);listeImage.add("snapchat");
        listeImage2.add(R.drawable.starbuck);listeImage.add("starbuck");
        listeImage2.add(R.drawable.twitter);listeImage.add("twitter");
        listeImage2.add(R.drawable.carrefour);listeImage.add("carrefour");
        listeImage2.add(R.drawable.volkswagen);listeImage.add("volkswagen");

        listeImage2.add(R.drawable.xbox);listeImage.add("xbox");
        listeImage2.add(R.drawable.youtube);listeImage.add("youtube");
        listeImage2.add(R.drawable.ysl);listeImage.add("yves saint laurent");




        moveImage();
    }

    private View.OnClickListener valideListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            if(compteur<20){
                Toast.makeText(
                        getApplicationContext(),
                        "reponse correct : "+listeImage.get(nombre), Toast.LENGTH_SHORT
                ).show();
                if(String.valueOf(edittext1.getText()).equals(listeImage.get(nombre))){
                    resultat++;
                }

                moveImage();
            }
            else {
                if (test==0){

                    test++;
                    button.setText("afficher le resultat");
                }else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Resultat " + resultat + "/20", Toast.LENGTH_SHORT
                    ).show();
                    button.setText("afficher le resultat");
                }

            }
            edittext1.setText("");


        }
    };

    public void moveImage(){
        if(compteur!=0) {
            listeImage2.remove(nombre);
            listeImage.remove(nombre);
        }
        if(compteur<20) {
            Random rand = new Random();
            nombre = rand.nextInt(28 - compteur); //Entre 0 et 19
            compteur++;
            imageSwitcher.setImageResource(listeImage2.get(nombre));
            progressBar.setProgress(compteur);
            System.out.println(compteur+"");

        }

    }
}
