package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by quentin on 10/06/2016.
 */
public class Change extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Toolbar toolbar;
    private EditText edittext1, edittext2;
    private Switch switch1;
    private String item;
    private int index=0;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change);
        Intent intent = getIntent();
        switch1 = (Switch) findViewById(R.id.switch1);
        edittext1 = (EditText) findViewById(R.id.editText);
        edittext2 = (EditText) findViewById(R.id.editText2);
        edittext2.setOnClickListener(edittext2Listener);
        //------------------------------------------------action lors du click sur le switch-------------------------------------------------
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    //System.out.println("on");
                    int chiffre=0;
                    try{
                        chiffre= Integer.parseInt(String.valueOf(edittext1.getText()));}
                    catch(Exception e){
                        switch1.setChecked(false);
                    }
                    if(item.equals("binaire")) {
                        edittext2.setText(Integer.toBinaryString(chiffre));

                    }else if(item.equals("octal")){

                        edittext2.setText(Integer.toOctalString(chiffre));

                    }else if(item.equals("hexadecimal")){
                        edittext2.setText(Integer.toHexString(chiffre));
                    }

                }else{
                    //System.out.println("off");
                    edittext2.setText("");
                }

            }
        });
        //------------------------------------------------ fin action lors du click sur le switch-------------------------------------------------
        item="binaire";
        //------------------------------------------------action lors du done sur le 2eme textebox-------------------------------------------------
        edittext2
                .setOnEditorActionListener(new OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId,
                                                  KeyEvent event) {
                        boolean handled = false;
                        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO) {
                    /* handle action here */
                            testconvertionChiffreVers();

                            handled = true;
                        }
                        return handled;
                    }
                });
        //------------------------------------------------fin action lors du done sur le 2eme textebox-------------------------------------------------
        //------------------------------------------ formatage de la page ---------------------------------------------------------
        if (intent.getStringExtra("theme").equals("code")) {
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

        }
        //-------------------------------------------fin de formatage ------------------------------------------------------------
        //-------------------------------------------creation du spinner ------------------------------------------------------------
        String[] arraySpinner = new String[]{
                "binaire", "hexadecimal","octal"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void testconvertionChiffreVers() {
        int chiffre=0;
        try{
            chiffre= Integer.parseInt(String.valueOf(edittext1.getText()));}
        catch(Exception e){
            switch1.setChecked(false);
        }
        if(item.equals("binaire")) {
            //partie pour la convertion en binaire
            String convertion = Integer.toBinaryString(chiffre);
           //System.out.println(convertion);

            if (convertion.equals(String.valueOf(edittext2.getText()))) {
               // System.out.println("test 3  O.K.");
                switch1.setChecked(true);
            } else {
                switch1.setChecked(false);
            }
        }else if(item.equals("hexadecimal")){
            try {
                if (chiffre == Integer.valueOf(String.valueOf(edittext2.getText()), 16)) {
                    switch1.setChecked(true);
                } else {
                    switch1.setChecked(false);
                }
            }catch(Exception e){
                switch1.setChecked(false);
            }
        }else if(item.equals("octal")){ try {
            Integer.valueOf(String.valueOf(edittext2.getText()), 8);

            if (chiffre == Integer.valueOf(String.valueOf(edittext2.getText()), 8)) {
                switch1.setChecked(true);
            } else {
                switch1.setChecked(false);
            }
            }catch(Exception e){
            switch1.setChecked(false);
        }
        }

    }

    private View.OnClickListener edittext2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //System.out.println("test 2 O.K.");
            testconvertionChiffreVers();
        }

    };

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Change Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://ldp.ldp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Change Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://ldp.ldp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        // test a réalisé sinon bug de lappli du a la non initialisation de certain composant
        if (index>=1)
        testconvertionChiffreVers();
        else
        index++;
        // Showing selected spinner item
        Toast.makeText(parent.getContext(),   item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

