package ldp.ldp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by quentin on 28/09/2016.
 */
public class Accueil extends AppCompatActivity {

    private ImageView imagelpd ;
    private TextView hTextView;
    private Timer myTimer;
    private MyTimerTask myTask;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.accueil);
        hTextView=(TextView) findViewById(R.id.textView8);
        imagelpd = (ImageView) findViewById(R.id.imagelpd);

        imagelpd.setOnClickListener(mediaListener);
        Intent intent = getIntent();

         myTask = new MyTimerTask();
         myTimer = new Timer();
//        public void schedule (TimerTask task, long delay, long period)
//        Schedule a task for repeated fixed-delay execution after a specific delay.
//
//        Parameters
//        task  the task to schedule.
//        delay  amount of time in milliseconds before first execution.
//        period  amount of time in milliseconds between subsequent executions.

        myTimer.schedule(myTask, 7000,1000);
        //definir notre toolbar en tant qu'actionBar
        //afficher le bouton retour


    }
    class MyTimerTask extends TimerTask {
        public void run() {
            // ERROR
            //hTextView.setText("appuyé sur l'image");
            //hTextView.setText("Impossible");
            myTimer.cancel();
            Intent intent2 = new Intent(Accueil.this, MainActivity.class);
            startActivity(intent2);
            finish();
            // how update TextView in link below
            // http://android.okhelp.cz/timer-task-timertask-run-cancel-android-example/

            //System.out.println("ca marche");
        }
    }
    private View.OnClickListener mediaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //setContentView(page2);
            myTimer.cancel();
            Intent intent2 = new Intent(Accueil.this, MainActivity.class);
            startActivity(intent2);
            finish();
        }
    };
}
