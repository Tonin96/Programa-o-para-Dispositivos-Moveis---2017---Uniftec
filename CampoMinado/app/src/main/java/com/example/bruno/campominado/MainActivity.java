package com.example.bruno.campominado;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private final int x = 6;
    private final int y = 6;
    private final int qtdBombas = 10;
    private int bombasAcertadas = 0;
    private Button[][] buttons = new Button[x][y];
    private Integer[][] localBombas = new Integer[x][y];
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.texto);
        resetar();
        embarralhar();
        adicionaFuncao();
    }


    public void adicionaFuncao() {
        Button recomecar = (Button) findViewById(R.id.recomecar);
        recomecar.setOnClickListener(this);
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                String buttonId = "btn" + i + "_" + j;
                int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = (Button) findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }


    public void resetar() {
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                String buttonId = "btn" + i + "_" + j;
                int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = (Button) findViewById(resID);
                buttons[i][j].setClickable(true);
                buttons[i][j].setText("*");
                localBombas[i][j] = 0;
                buttons[i][j].setBackgroundColor(Color.GRAY);

            }
        }
        textView.setText("Cliques:");
        bombasAcertadas = 0;
    }

    public void embarralhar() {
        Random gerador = new Random();
        int qtdAux = 0;

        while (qtdAux < qtdBombas) {
            for (int i = 1; i < x; i++) {
                for (int j = 1; j < y; j++) {
                    if (localBombas[i][j] == 0) {
                        if (gerador.nextInt(100) > 50) {
                            localBombas[i][j] = 1;
                            qtdAux++;
                        }
                    }
                }
            }
        }
    }

    public void perdeu(){
        textView.setText("Você Perdeu!");

        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                String buttonId = "btn" + i + "_" + j;
                int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = (Button) findViewById(resID);
                buttons[i][j].setClickable(false);
                if(localBombas[i][j] == 0){
                    buttons[i][j].setText("0");
                }else{
                    buttons[i][j].setText("X");
                    buttons[i][j].setBackgroundColor(Color.RED);
                }

            }
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.recomecar){
            resetar();
            embarralhar();
        }else{

            String auxButtonId[] = getResources().getResourceName(view.getId()).split("btn");
            String buttonId = auxButtonId[1];
            Integer auxX = Integer.valueOf(buttonId.split("_")[0]);
            Integer auxY = Integer.valueOf(buttonId.split("_")[1]);

            if(localBombas[auxX][auxY] == 0){
                bombasAcertadas++;
                textView.setText("Cliques: " + bombasAcertadas);
                buttons[auxX][auxY].setText("O");
                buttons[auxX][auxY].setBackgroundColor(Color.BLUE);
            }else{
                buttons[auxX][auxY].setText("X");
                perdeu();
            }
            buttons[auxX][auxY].setClickable(false);
        }
    }
}
