package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by quentin on 13/06/2016.
 */
public class CryptageCesar extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText edittext1, edittext2;
    private String texte;
    private Switch switch1;
    private static final int TAILLE_ALPHABET = 26;

    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cryptage_cesar);
        Intent intent = getIntent();
        //ajout des toolbar avec leur bouton d'utilisation
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("atelier : code de cesar");
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
        switch1 = (Switch) findViewById(R.id.switch1);
        edittext1 = (EditText) findViewById(R.id.editText);
        edittext2 = (EditText) findViewById(R.id.editText2);
        //------------------------------------------------action lors du done sur le 2eme textebox-------------------------------------------------
        edittext2
                .setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId,
                                                  KeyEvent event) {
                        boolean handled = false;
                        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO) {
                    /* handle action here */
                            //testconvertionChiffreVers();
                            texte= String.valueOf(edittext2.getText());
                            System.out.println(dechiffre(3));
                            if(String.valueOf(edittext1.getText()).equals(dechiffre(3)))

                                switch1.setChecked(true);
                            else{
                                switch1.setChecked(false);

                            }

                            handled = true;
                        }
                        return handled;
                    }
                });
        //------------------------------------------------fin action lors du done sur le 2eme textebox-------------------------------------------------
        //------------------------------------------------action lors du done sur le 2eme textebox-------------------------------------------------
        edittext1
                .setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId,
                                                  KeyEvent event) {
                        boolean handled = false;
                        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO) {
                    /* handle action here */
                            //testconvertionChiffreVers();
                            texte= String.valueOf(edittext1.getText());
                            System.out.println(dechiffre(3));
                            if(String.valueOf(edittext2.getText()).equals(dechiffre(3)))

                                switch1.setChecked(true);
                            else{
                                switch1.setChecked(false);

                            }

                            handled = true;
                        }
                        return handled;
                    }
                });
        //------------------------------------------------fin action lors du done sur le 2eme textebox-------------------------------------------------
        //------------------------------------------------action lors du click sur le switch-------------------------------------------------
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    //System.out.println("on");
                    texteConvertie();
                }else{
                    //System.out.println("off");

                }
            }
        });
        //------------------------------------------------ fin action lors du click sur le switch-------------------------------------------------

    }
    public void texteConvertie(){
        if(!String.valueOf(edittext1.getText()).equals("")) {
            texte = String.valueOf(edittext1.getText());
            edittext2.setText(chiffre(3));
            System.out.println("on ,"+String.valueOf(edittext1.getText()));
        }else{
            texte = String.valueOf(edittext2.getText());
            edittext1.setText(dechiffre(3));
            System.out.println("on 2");
        }
    }
    public String chiffre(int decalage) {
        StringBuilder sb = new StringBuilder(texte.length());
        for (char c : texte.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sb.append(decaleVar(c, decalage, 'a'));
            } else if (c >= 'A' && c <= 'Z') {
                sb.append(decaleVar(c, decalage, 'A'));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String dechiffre(int decalage) {
        return chiffre(-decalage);
    }

    private char decaleVar(char caractere, int decalage, char caractereBase) {
        int base = (int) caractereBase;
        if (decalage < 0) {
            base += TAILLE_ALPHABET - 1;
        }
        return (char) ((((int) caractere) - base + decalage) % TAILLE_ALPHABET + base);
    }
}
