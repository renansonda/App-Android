package br.edu.easylog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Rota extends AppCompatActivity {

    EditText txtOrigem, txtDestino, txtFretePeso, txtPedagio, txtGris, txtAdv, txtIcms, txtCodigo;
    Button b19, btnCadastrar, btnExcluir, btnLimpar ;
    ListView listViewRotas;


    bancoDados db = new bancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotas);
        setTitle("CADASTRO DE ROTA");

        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtOrigem = (EditText)findViewById(R.id.txtOrigem);
        txtDestino = (EditText)findViewById(R.id.txtDestino);
        txtFretePeso = (EditText)findViewById(R.id.txtFretePeso);
        txtPedagio = (EditText)findViewById(R.id.txtPedagio);
        txtGris = (EditText)findViewById(R.id.txtGris);
        txtAdv = (EditText)findViewById(R.id.txtAdv);
        txtIcms = (EditText)findViewById(R.id.txtIcms);

        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnExcluir = (Button)findViewById(R.id.btnExcluir);
        btnLimpar = (Button)findViewById(R.id.btnLimpar);

        //Ocultar teclado após o click no buttom com editText ativo
        imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);

        listViewRotas = (ListView)findViewById(R.id.listViewRotas);

        listarRotas();

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
            }
        });

        listViewRotas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String conteudo = (String) listViewRotas.getItemAtPosition(position);

                //Toast.makeText(Rota.this, "Select: " + conteudo, Toast.LENGTH_SHORT).show();
                String codigo = conteudo.substring(0 , conteudo.indexOf("-"));

                DatabaseHelper2 rota2 = db.selecionarRota(Integer.parseInt(codigo));

                txtCodigo.setText(String.valueOf(rota2.getCodigo()));
                txtOrigem.setText(rota2.getCidadeO());
                txtDestino.setText(rota2.getCidadeD());
                txtFretePeso.setText(String.valueOf(rota2.getFreteP()));
                txtPedagio.setText(String.valueOf(rota2.getPed()));
                txtGris.setText(String.valueOf(rota2.getGris()));
                txtAdv.setText(String.valueOf(rota2.getAdv()));
                txtIcms.setText(String.valueOf(rota2.getIcms()));

            }
        });

        b19 = (Button)findViewById(R.id.button19);
        b19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Rota.this,Cotacao.class);
                startActivity(k);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo = txtCodigo.getText().toString();
                String origem = txtOrigem.getText().toString();
                String destino = txtDestino.getText().toString();
                String fretepeso = txtFretePeso.getText().toString();
                String pedagio = txtPedagio.getText().toString();
                String gris = txtGris.getText().toString();
                String adv = txtAdv.getText().toString();
                String icms = txtIcms.getText().toString();

                if(origem.isEmpty() || destino.isEmpty() || fretepeso.isEmpty() || pedagio.isEmpty() || gris.isEmpty()
                        || adv.isEmpty() || icms.isEmpty()) {
                    txtOrigem.setError("Este campo é obrigatório.");
                    txtDestino.setError("Este campo é obrigatório.");
                    txtFretePeso.setError("Este campo é obrigatório.");
                    txtPedagio.setError("Este campo é obrigatório.");
                    txtGris.setError("Este campo é obrigatório.");
                    txtAdv.setError("Este campo é obrigatório.");
                    txtIcms.setError("Este campo é obrigatório.");


                } else if (codigo.isEmpty()) {
                    //insert
                    db.addRota(new DatabaseHelper2(origem,destino,Float.parseFloat(fretepeso),Float.parseFloat(pedagio),Float.parseFloat(gris),Float.parseFloat(adv),Float.parseFloat(icms)));

                    Toast.makeText(Rota.this, "Rota adicionada com sucesso.", Toast.LENGTH_SHORT).show();

                    limpaCampos();
                    listarRotas();
                    escodeTeclado();

                } else {
                    //update
                    db.atualizarRota(new DatabaseHelper2(Integer.parseInt(codigo),origem,destino,Float.parseFloat(fretepeso),Float.parseFloat(pedagio),Float.parseFloat(gris),Float.parseFloat(adv),Float.parseFloat(icms)));

                    Toast.makeText(Rota.this, "Rota atualizada com sucesso.", Toast.LENGTH_SHORT).show();

                    limpaCampos();
                    listarRotas();
                    escodeTeclado();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo = txtCodigo.getText().toString();

                if (codigo.isEmpty()) {
                    Toast.makeText(Rota.this, "Nenhuma rota está selecionada.", Toast.LENGTH_SHORT).show();
                } else {

                    DatabaseHelper2 databaseHelper2 = new DatabaseHelper2();
                    databaseHelper2.setCodigo(Integer.parseInt(codigo));
                    db.apagarRota(databaseHelper2);

                    Toast.makeText(Rota.this, "Rota excluida com sucesso.", Toast.LENGTH_SHORT).show();

                    limpaCampos();
                    listarRotas();
                }
            }
        });


        /*b19 = (Button)findViewById(R.id.button19);
        b19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Rota.this,Cotacao.class);
                startActivity(k);
            }
        });*/

         //TESTE CRUD

        /*//Insert OK
        db.addRota(new DatabaseHelper2("Curitiba","Sao Paulo",10,20,30,40,50));
        db.addRota(new DatabaseHelper2("HEJE","BUSQUE",10,20,30,40,50));
        db.addRota(new DatabaseHelper2("UISK","Sao Paulo",10,20,30,40,50));
        db.addRota(new DatabaseHelper2("CSAO PAULO","Sao Paulo",10,20,30,40,50));
        db.addRota(new DatabaseHelper2("asdasd","Sao Paulo",10,20,30,40,50));
        db.addRota(new DatabaseHelper2("iurtyertewrtw","asdasdasd",10,20,30,40,50));

        Toast.makeText(this, "REGISTRADO COM SUCESSO!", Toast.LENGTH_LONG).show();
        */
        //Delete OK
        //DatabaseHelper2 databaseHelper2 = new DatabaseHelper2();
        //databaseHelper2.setCodigo(6);
        //db.apagarRota(databaseHelper2);

        //Toast.makeText(this, "APAGADO COM SUCESSO!", Toast.LENGTH_LONG).show();

        //Select OK
        /*
        DatabaseHelper2 rota2 = db.selecionarRota(4);

        Log.d("Rota Selecionada.", "Código: " + rota2.getCodigo() + " Cidade Origem: " +
                rota2.getCidadeO() + " Cidade Destino: " + rota2.getCidadeD() +
                " Frete Peso: " + rota2.getFreteP() + " Pedágio: " + rota2.getPed() +
                " Gris: " + rota2.getGris() + " ADV: " + rota2.getAdv() + " ICMS: " + rota2.getIcms());
        */

        //Update OK
        /*
        DatabaseHelper2 rota = new DatabaseHelper2();
        rota.setCodigo(4);
        rota.setCidadeO("TESTE");
        rota.setCidadeD("MEUCU");
        rota.setFreteP(11111111);
        rota.setPed(22222222);
        rota.setGris(33333333);
        rota.setAdv(44444444);
        rota.setIcms(55555555);

        db.atualizarRota(rota);

        Toast.makeText(this, "ATUALIZADO COM SUCESSO!", Toast.LENGTH_LONG).show();
         */
    }

    void limpaCampos(){
        txtCodigo.setText("");
        txtOrigem.setText("");
        txtDestino.setText("");
        txtFretePeso.setText("");
        txtPedagio.setText("");
        txtGris.setText("");
        txtAdv.setText("");
        txtIcms.setText("");

        txtOrigem.requestFocus();
    }

    void escodeTeclado() {
        imm.hideSoftInputFromWindow(txtOrigem.getWindowToken(), 0);
    }

    public void listarRotas() {

        List<DatabaseHelper2> rotas = db.listaTodasRotas();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Rota.this, android.R.layout.simple_list_item_1, arrayList);

        listViewRotas.setAdapter(adapter);

        for(DatabaseHelper2 c : rotas) {
            Log.d("Lista", "\nID: " + c.getCodigo() + " Cidade Origem: " + c.getCidadeO() + " Cidade Destino: " + c.getCidadeD());
            arrayList.add(c.getCodigo() + "-" + c.getCidadeO() + " / " + c.getCidadeD());
            adapter.notifyDataSetChanged();
        }
    }
}








