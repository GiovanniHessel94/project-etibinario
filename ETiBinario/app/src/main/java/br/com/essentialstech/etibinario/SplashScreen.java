package br.com.essentialstech.etibinario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Giovanni Hessel on 05/03/2017.
 */

public class SplashScreen extends Activity{
    private ProgressBar progressBar;
    //Método onCreate
    /* É inicializado a váriavel progressBar, juntamente com um nova thread onde é acionado
    * o método splashScreen que faz o carregamento da tela*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        progressBar = (ProgressBar) findViewById(R.id.prgs);
        new Thread(new Runnable() {
            public void run() {
                SystemClock.sleep(500);
                splashScreen();
            }
        }).start();
    }
    //Método SplashScreen
    private void splashScreen(){
        //for responsavel incremento da propriedade progress
        for (int progress=0; progress<100; progress+=1) {
            SystemClock.sleep(20);
            progressBar.setProgress(progress);
            }
        //Intent responsável pela abertura da Activity Principal
        Intent intent = new Intent(SplashScreen.this, MainCode.class);
        startActivity(intent);
    }
}
