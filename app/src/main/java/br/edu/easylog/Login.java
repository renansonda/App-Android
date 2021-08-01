package br.edu.easylog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    Button b1, b21;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("EASYLOG");
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean Chkemailpass = db.emailpassword(email,password);
                if (Chkemailpass==true) {
                    Intent j = new Intent(Login.this, Modulos.class);
                    startActivity(j);
                    Toast.makeText(getApplicationContext(), "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Email e(ou) senha incorreto(s)! ", Toast.LENGTH_LONG).show();
            }
        });

        b21 = (Button)findViewById(R.id.button21);
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Login.this,MainActivity.class);
                startActivity(k);
            }
        });

    }
}

