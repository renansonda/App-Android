package br.edu.easylog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaSolicitacao extends AppCompatActivity {

    Button b30, b38;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_solicitacao);

        b30 = (Button)findViewById(R.id.button30);
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(ListaSolicitacao.this,Solicitacao.class);
                startActivity(k);
            }
        });

        b38 = (Button)findViewById(R.id.button38);
        b38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(ListaSolicitacao.this,Modulos.class);
                startActivity(k);
            }
        });
    }
}