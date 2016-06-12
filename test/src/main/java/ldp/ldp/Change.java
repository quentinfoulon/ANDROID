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
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by quentin on 10/06/2016.
 */
public class Change extends AppCompatActivity {
    private Toolbar toolbar ;
    private EditText edittext1 ,edittext2;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change);
        Intent intent = getIntent();
        edittext1 = (EditText) findViewById(R.id.editText);
        edittext2 = (EditText) findViewById(R.id.editText2);
        edittext2.setOnClickListener(edittext2Listener);
        edittext2
                .setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId,
                                                  KeyEvent event) {
                        boolean handled = false;
                        if (actionId == EditorInfo.IME_ACTION_DONE ||actionId == EditorInfo.IME_ACTION_GO) {
                    /* handle action here */
                            System.out.println("test  O.K.");
                            handled = true;
                        }
                        return handled;
                    }
                });

        if (intent.getStringExtra("theme").equals("code")) {
            //ajout des toolbar avec leur bouton d'utilisation
            toolbar = (Toolbar) findViewById(R.id.my_toolbar);
            toolbar.setTitle("atelier : educo-code");
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

            }
        String[] arraySpinner = new String[] {
                "binaire", "hexadecimal"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);


        }
    public void convertionChiffreVersBinaire(int chiffre){


         }
    private View.OnClickListener edittext2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("test 2 O.K.");
        }

    };
    }

