package br.edu.easylog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaCotacao extends AppCompatActivity {

    Button b28, b36;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cotacao);

        b28 = (Button)findViewById(R.id.button28);
        b28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(ListaCotacao.this,Cotacao.class);
                startActivity(k);
            }
        });

        b36 = (Button)findViewById(R.id.button36);
        b36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(ListaCotacao.this,Modulos.class);
                startActivity(k);
            }
        });
    }
}