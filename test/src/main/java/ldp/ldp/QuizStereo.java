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
public class QuizStereo extends AppCompatActivity {
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
    private ArrayList<String> listeQ1;
    private ArrayList<String> listeQ2;
    private ArrayList<String> listeQ3;
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
        toolbar.setTitle("atelier : quiz des stéréotype");
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

        progressBar.setMax(10);
        listeImage2=new ArrayList<Integer>();
        listeImage=new ArrayList<String>();
        listeQ1=new ArrayList<String>();
        listeQ2=new ArrayList<String>();
        listeQ3=new ArrayList<String>();
        button.setOnClickListener(valideListener);
        listeImage2.add(R.drawable.sexyhumour02);listeImage.add("Les femmes ne devraient pas conduire");listeQ1.add("Les femmes ne devraient pas conduire");listeQ2.add("Les location de voitures sont interdits aux femmes");listeQ3.add("les femmes sont des professionnels en voiture");
        listeImage2.add(R.drawable.darty);listeImage.add("Les blondes sont stupide");listeQ1.add("Les blondes ont peur de la technologie");listeQ2.add("Les blondes sont stupide");listeQ3.add("Face à l'inconnu on a tous peur et on se sent blonde");
        listeImage2.add(R.drawable.eden_park);listeImage.add("Le travail des femmes c'est d'attendre les hommes à la maison avec les taches ménagère déjà  préparé ");listeQ1.add("Les femmes sont douées aux taches ménagère");listeQ2.add("Les hommes ne sont pas douées pour les taches ménagère");listeQ3.add("Le travail des femmes c'est d'attendre les hommes à la maison avec les taches ménagère déjà  préparé");
        listeImage2.add(R.drawable.post_it);listeImage.add("Les hommes ne prennent pas la peine d'apprendre le prénom de leur partenaire");listeQ1.add("Les hommes ne prennent pas la peine d'apprendre le prénom de leur partenaire");listeQ2.add("C'est bien connu, les hommes n'ont pas de mémoire");listeQ3.add("Le post-it est le meilleur ami des hommes");
        listeImage2.add(R.drawable.savon);listeImage.add("Il faut être blanc");listeQ1.add("Le savon Dirtoff nettoie tout");listeQ2.add("Le savon dirtoff blanchit les personnes de couleur pour les rendre à leurs normes");listeQ3.add("Il faut être blanc  ");
        listeImage2.add(R.drawable.homme);listeImage.add("Les hommes sont des professionnels du maquillage");listeQ1.add("Les hommes ne sont pas doués dans le domaine du  maquillage");listeQ2.add("Les hommes sont des professionnels du maquillage");listeQ3.add("Les hommes sont ne connaissent pas le mot maquillage et à quoi il peut servir");
        listeImage2.add(R.drawable.football);listeImage.add("Le football est un sport pour les femmes");listeQ1.add("Le football n'est pas réservé aux femmes");listeQ2.add("Le football est un sport pour les femmes");listeQ3.add("Les femmes et le football , elle n'y connaissent rien");
        listeImage2.add(R.drawable.billielliot);listeImage.add("Les hommes apprécient la danse classique");listeQ1.add("La danse classique est un sport exclusivement pour les femmes");listeQ2.add("Les hommes et la danse classique ? JAMAIS !");listeQ3.add("Les hommes apprécient la danse classique");
        listeImage2.add(R.drawable.bouchere);listeImage.add("une femme dans une boucherie, en tant que cliente? Oui !");listeQ1.add("une femme dans une boucherie, en tant que cliente? Oui !");listeQ2.add("Le métier de boucher est un métier exclusivement pour les hommes");listeQ3.add("Les femmes + prendre soin d'elle = non bouchère");
        listeImage2.add(R.drawable.armee);listeImage.add("Les femmes et les hommes , tout deux ont la possibilité de travailler dans l'armée");listeQ1.add("L'armée ne recrutent pas de femmes");listeQ2.add("Les femmes et les hommes , tout deux ont la possibilité de travailler dans l'armée");listeQ3.add("L'armée c'est trop physique pour les femmes");


        moveImage();
    }

    private View.OnClickListener valideListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            if(compteur<10){
                Toast.makeText(
                        getApplicationContext(),
                        "reponse correct : "+listeImage.get(nombre), Toast.LENGTH_SHORT
                ).show();
                if(listeImage.get(nombre).equals(listeQ1.get(nombre))){
                    if(radioButton1.isChecked()) {
                        resultat++;
                    }

                }
                if(listeImage.get(nombre).equals(listeQ2.get(nombre))){
                    if(radioButton2.isChecked())
                        resultat++;
                }
                if(listeImage.get(nombre).equals(listeQ3.get(nombre))){
                    if(radioButton3.isChecked())
                        resultat++;
                }
                moveImage();
            }
            else {
                if (test==0){
                    if(listeImage.get(nombre).equals(listeQ1.get(nombre))){
                        if(radioButton1.isChecked()) {
                            resultat++;
                        }

                    }
                    if(listeImage.get(nombre).equals(listeQ2.get(nombre))){
                        if(radioButton2.isChecked())
                            resultat++;
                    }
                    if(listeImage.get(nombre).equals(listeQ3.get(nombre))){
                        if(radioButton3.isChecked())
                            resultat++;
                    }
                    test++;
                }else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Resultat " + resultat + "/10", Toast.LENGTH_SHORT
                    ).show();
                    button.setText("afficher le resultat");
                }

            }


        }
    };

    public void moveImage(){
        if(compteur!=0) {
            listeImage2.remove(nombre);
            listeImage.remove(nombre);
            listeQ1.remove(nombre);
            listeQ2.remove(nombre);
            listeQ3.remove(nombre);
        }
        if(compteur<10) {
            Random rand = new Random();
            nombre = rand.nextInt(10 - compteur); //Entre 0 et 19
            compteur++;
            imageSwitcher.setImageResource(listeImage2.get(nombre));
            radioButton1.setText(listeQ1.get(nombre));
            radioButton2.setText(listeQ2.get(nombre));
            radioButton3.setText(listeQ3.get(nombre));
            progressBar.setProgress(compteur);
            System.out.println(compteur+"");

        }

    }
}