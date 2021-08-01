package br.edu.easylog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Relatorio extends AppCompatActivity {

    Button b24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        setTitle("RELATÓRIO");

        b24 = (Button)findViewById(R.id.button24);
        b24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Relatorio.this,Modulos.class);
                startActivity(k);
            }
        });
    }
}