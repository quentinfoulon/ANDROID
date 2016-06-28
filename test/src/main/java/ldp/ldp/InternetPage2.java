package ldp.ldp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by quentin on 05/06/2016.
 */
public class InternetPage2 extends AppCompatActivity {
    private Toolbar toolbar ;
    WebView webview;
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.internetpage);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        Intent intent = getIntent();
        toolbar.setTitle(" "+intent.getStringExtra("nom"));
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        //afficher le bouton retour
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#939292"));
        }
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);

        webview.loadUrl(
                "http://172.20.10.4/educomedias");

        //action du retour a la page .
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}