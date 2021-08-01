package br.edu.easylog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaProjecao extends AppCompatActivity {

    Button b29, b37;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_projecao);

        b29 = (Button)findViewById(R.id.button29);
        b29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(ListaProjecao.this,Projecao.class);
                startActivity(k);
            }
        });

        b37 = (Button)findViewById(R.id.button37);
        b37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(ListaProjecao.this,Modulos.class);
                startActivity(k);
            }
        });
    }
}