package br.edu.easylog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Modulos extends AppCompatActivity {
    Button b5, b6, b7, b8, b23, b33, b34, b35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulos);
        setTitle("MÓDULOS");
        b5 = (Button)findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Modulos.this,Cotacao.class);
                startActivity(k);
            }
        });

        b6 = (Button)findViewById(R.id.button6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Modulos.this,Projecao.class);
                startActivity(k);
            }
        });

        b7 = (Button)findViewById(R.id.button7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Modulos.this,Solicitacao.class);
                startActivity(k);
            }
        });

        b8 = (Button)findViewById(R.id.button8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Modulos.this,Relatorio.class);
                startActivity(k);
            }
        });

        b23 = (Button)findViewById(R.id.button23);
        b23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Modulos.this,Login.class);
                startActivity(k);

                Toast.makeText(Modulos.this, "Para acessar novamente efetue o Login!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}