package ldp.ldp;

import android.annotation.TargetApi;
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
 * Created by quentin on 14/06/2016.
 */
public class CryptageVigenere extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText edittext1, edittext2 ,edittext3;
    private String texte;
    private Switch switch1;
    CodDec_Vigenere crypt;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cryptagevigenere);
        Intent intent = getIntent();
        //ajout des toolbar avec leur bouton d'utilisation
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("atelier : code de cesar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(Color.parseColor("#939292"));
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
        edittext3 = (EditText) findViewById(R.id.editText3);
        crypt = new CodDec_Vigenere();
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
                            System.out.println("on" +crypt.Decode(String.valueOf(edittext2.getText()),String.valueOf(edittext3.getText())));
                            texte=crypt.Decode(String.valueOf(edittext2.getText()),String.valueOf(edittext3.getText())).toLowerCase();
                            if(String.valueOf(edittext1.getText()).equals(crypt.Decode(String.valueOf(edittext2.getText()),String.valueOf(edittext3.getText())).toLowerCase()))

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
                    //texteConvertie();
                    edittext2.setText(crypt.Code(String.valueOf(edittext1.getText()),String.valueOf(edittext3.getText())));

                }else{
                    //System.out.println("off");

                }
            }
        });
        //------------------------------------------------ fin action lors du click sur le switch-------------------------------------------------




    }
    public void decode(){

        if(!String.valueOf(edittext1.getText()).equals("")) {
            texte = String.valueOf(edittext1.getText());
            edittext2.setText(crypt.Code(String.valueOf(edittext1.getText()),String.valueOf(edittext3.getText())));
            System.out.println("on ,"+String.valueOf(edittext1.getText()));
        }else{

            edittext1.setText(crypt.Decode(String.valueOf(edittext2.getText()),String.valueOf(edittext3.getText())));
            //System.out.println("on 2");
        }
    }
}
