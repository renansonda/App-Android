package br.edu.easylog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static br.edu.easylog.bancoDadosCot.TESTE;

public class Cotacao extends AppCompatActivity {

    static String CODUP;

    static String CO1, CO2, CD1, CD2;

    Button btnCadRota, btnVoltarCot, btnCalc, btnSalvarCot, btnLimparCot, btnAprovarCot;


   static EditText txtCodigoCot, txtCidadeOcot, txtCidadeDcot, txtValorCarga, txtPesoCobrado, txtFretePesoCot, txtPedCot,
            txtGrisCot, txtAdvCot, txtIcmsCot, txtFreteTotal, txtStatus, txtNomeCliente, txtEndCot, txtQVolCot, txtDtColCot, txtHRColCot,
    txtObsCot, txtCavCot, txtCarCot, txtCondCot, txtCpfCot, txtRefCot;

    ListView listViewCot;

    bancoDadosCot dbCot = new bancoDadosCot(this);
    bancoDados db = new bancoDados(this);

    ArrayAdapter<String> adapterCot;
    ArrayList<String> arrayListCott;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotacao);
        setTitle("COTAÇÃO");



        txtCodigoCot = (EditText) findViewById(R.id.txtCodigoCot);
        txtCidadeOcot = (EditText) findViewById(R.id.txtCidadeOcot);
        txtCidadeDcot = (EditText) findViewById(R.id.txtCidadeDcot);
        txtValorCarga = (EditText) findViewById(R.id.txtValorCarga);
        txtPesoCobrado = (EditText) findViewById(R.id.txtPesoCobrado);
        txtFretePesoCot = (EditText) findViewById(R.id.txtFretePesoCot);
        txtPedCot = (EditText) findViewById(R.id.txtPedCot);
        txtGrisCot = (EditText) findViewById(R.id.txtGrisCot);
        txtAdvCot = (EditText) findViewById(R.id.txtAdvCot);
        txtIcmsCot = (EditText) findViewById(R.id.txtIcmsCot);
        txtFreteTotal = (EditText) findViewById(R.id.txtFreteTotal);

        txtNomeCliente = (EditText) findViewById(R.id.txtNomeCliente);
        txtEndCot = (EditText) findViewById(R.id.txtEndCot);
        txtRefCot = (EditText) findViewById(R.id.txtRefCot);
        txtQVolCot = (EditText) findViewById(R.id.txtQVolCot);
        txtDtColCot = (EditText) findViewById(R.id.txtDtColCot);
        txtHRColCot = (EditText) findViewById(R.id.txtHRColCot);
        txtObsCot = (EditText) findViewById(R.id.txtObsCot);
        txtCavCot = (EditText) findViewById(R.id.txtCavCot);
        txtCarCot = (EditText) findViewById(R.id.txtCarCot);
        txtCondCot = (EditText) findViewById(R.id.txtCondCot);
        txtCpfCot = (EditText) findViewById(R.id.txtCpfCot);
        txtStatus = (EditText) findViewById(R.id.txtStatus);


        btnCalc = (Button) findViewById(R.id.btnCalc);
        btnSalvarCot = (Button) findViewById(R.id.btnSalvarCot);
        btnLimparCot = (Button) findViewById(R.id.btnLimparCot);
        btnAprovarCot = (Button) findViewById(R.id.btnAprovarCot);

        listViewCot = (ListView) findViewById(R.id.listViewCot);

        listarCotacao();

        btnLimparCot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                limparCamposCot();
            }
        });

        listViewCot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String conteudoCot = (String) listViewCot.getItemAtPosition(position);

                //Toast.makeText(Cotacao.this, "Select: " + conteudoCot, Toast.LENGTH_SHORT).show();
                String codigo = conteudoCot.substring(0 , conteudoCot.indexOf("-"));

                DatabaseHelperCot cotacao = dbCot.selecionarCotacao(Integer.parseInt(codigo));
                DatabaseHelper2 rota2 = db.selecionarRota(Integer.parseInt(codigo));

                CO1 = String.valueOf(rota2.getCidadeO());
                CO2 = String.valueOf(cotacao.getCidocot());
                CD1 = String.valueOf(rota2.getCidadeD());
                CD2 = String.valueOf(cotacao.getCiddcot());

                //EDITAVEIS
                txtCodigoCot.setText(String.valueOf(cotacao.getCodigocot()));
                txtCidadeOcot.setText(cotacao.getCidocot());
                txtCidadeDcot.setText(cotacao.getCiddcot());
                txtValorCarga.setText(String.valueOf(cotacao.getVlcarga()));
                txtPesoCobrado.setText(String.valueOf(cotacao.getPesoc()));
                txtStatus.setText(cotacao.getStatus());

                CODUP = String.valueOf(cotacao.getCodigocot());



                if (CO1.equals(CO2) && CD1.equals(CD2)) {

                    Toast.makeText(Cotacao.this, "Rota encontrada. Precione o botão CALCULAR", Toast.LENGTH_SHORT).show();
                    /*
                    txtFretePesoCot.setText(String.valueOf(cotacao.getFpeso()));
                    txtPedCot.setText(String.valueOf(cotacao.getPedcot()));
                    txtGrisCot.setText(String.valueOf(cotacao.getGriscot()));
                    txtAdvCot.setText(String.valueOf(cotacao.getAdvcot()));
                    txtIcmsCot.setText(String.valueOf(cotacao.getIcmscot()));
                    txtFreteTotal.setText(String.valueOf(cotacao.getFtotal()));
                    */

                    //txtGrisCot.setText(String.valueOf(cotacao.getVlcarga() * TESTE));
                    //txtGrisCot.setText("" + TESTE);



                    //DIVISAO POR 100 NAO FUNCIONA POIS OS TIPOS SAO INTEIROS
                    /*txtAdvCot.setText(String.valueOf((cotacao.getVlcarga() * (rota2.getAdv()/100))));

                    // ICMS NAO ESTA CALCULANDO POIS NAO PERMITIU INCLUIR NO DATABASEHELPER 2 VERIFICAR COM VALDINEI
                    txtIcmsCot.setText(String.valueOf(cotacao.getFpeso() + cotacao.getPedcot() +
                            (cotacao.getVlcarga() * (rota2.getGris()/100)) +
                            (cotacao.getVlcarga() * (rota2.getAdv()/100))));

                    // FRETE TOTAL FALTANDO ICMS CONFORME COMENTARIO ANTERIOR
                    txtFreteTotal.setText(String.valueOf(cotacao.getFpeso() + cotacao.getPedcot() +
                            (cotacao.getVlcarga() * (rota2.getGris()/100)) +
                            (cotacao.getVlcarga() * (rota2.getAdv()/100))));*/

                } else {
                    Toast.makeText(Cotacao.this, "Rota não cadastrada. Favor cadastra-la.", Toast.LENGTH_SHORT).show();

                    txtFretePesoCot.setText("0");
                    txtPedCot.setText("0");
                    txtGrisCot.setText("0");
                    txtAdvCot.setText("0");
                    txtIcmsCot.setText("0");
                    txtFreteTotal.setText("0");

                }

                //NAO EDITAVEIS
                /*
                txtFretePesoCot.setText(String.valueOf(cotacao.getFpeso()));
                txtPedCot.setText(String.valueOf(cotacao.getPedcot()));
                txtGrisCot.setText(String.valueOf(cotacao.getGriscot()));
                txtAdvCot.setText(String.valueOf(cotacao.getAdvcot()));
                */

            }
        });


        btnCalc = (Button)findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String cidOCot = txtCidadeOcot.getText().toString();
                String cidDCot = txtCidadeDcot.getText().toString();
                String vlcarga = txtValorCarga.getText().toString();
                String pesoc = txtPesoCobrado.getText().toString();

                if (cidOCot.isEmpty() || cidDCot.isEmpty() || vlcarga.isEmpty() || pesoc.isEmpty()) {
                    txtCidadeOcot.setError("Este campo é obrigatório!");
                    txtCidadeDcot.setError("Este campo é obrigatório!");
                    txtValorCarga.setError("Este campo é obrigatório!");
                    txtPesoCobrado.setError("Este campo é obrigatório!");


                } else if (CO1.equals(CO2) && CD1.equals(CD2)) {

                    DatabaseHelperCot cotacao = dbCot.selecionarCotacao(Integer.parseInt(CODUP));
                    DatabaseHelper2 rota2 = db.selecionarRota(Integer.parseInt(CODUP));

                    txtFretePesoCot.setText(String.valueOf(rota2.getFreteP()));
                    txtPedCot.setText(String.valueOf(rota2.getPed()));
                    txtGrisCot.setText(String.valueOf(cotacao.getVlcarga() * rota2.getGris()));
                    txtAdvCot.setText(String.valueOf(cotacao.getVlcarga() * (rota2.getAdv())));

                    txtIcmsCot.setText(String.valueOf((cotacao.getVlcarga() * rota2.getGris() + cotacao.getVlcarga() * (rota2.getAdv()) +
                            rota2.getFreteP() * ((rota2.getIcms() / 100))  )));


                    txtFreteTotal.setText(String.valueOf((cotacao.getVlcarga() * rota2.getGris() + cotacao.getVlcarga() * (rota2.getAdv()) +
                            rota2.getFreteP() * ((rota2.getIcms() / 100)) + ((cotacao.getVlcarga() * rota2.getGris()) +
                            (cotacao.getVlcarga() * (rota2.getAdv()))) + rota2.getPed() + rota2.getFreteP() )));

                } else {

                    Toast.makeText(Cotacao.this, "Rota não cadastrada. Favor Cadastrá-la", Toast.LENGTH_SHORT).show();
                }
            }
        });




        btnCadRota = (Button)findViewById(R.id.btnCadRota);
        btnCadRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Cotacao.this, Rota.class);
                startActivity(k);
            }
        });

        btnVoltarCot = (Button)findViewById(R.id.btnVoltarCot);
        btnVoltarCot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Cotacao.this,Modulos.class);
                startActivity(k);
            }
        });

        btnSalvarCot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigoCot = txtCodigoCot.getText().toString();
                String cidOCot = txtCidadeOcot.getText().toString();
                String cidDCot = txtCidadeDcot.getText().toString();
                String vlcarga = txtValorCarga.getText().toString();
                String pesoc = txtPesoCobrado.getText().toString();


                String fretep = "0";
                String pedcot = "0";
                String griscot = "0";
                String advcot = "0";
                String icmscot = "0";
                String fretefinal = "0";

                /*
                String fretep = txtFretePesoCot.getText().toString();
                String pedcot = txtPedCot.getText().toString();
                String griscot = txtGrisCot.getText().toString();
                String advcot = txtAdvCot.getText().toString();
                String icmscot = txtIcmsCot.getText().toString();
                String fretefinal = txtFreteTotal.getText().toString();
                */


                String ncliente = "";
                String end = "";
                String ref = "";
                String qvol = "0";
                String datacol = "";
                String horacol = "";
                String obscol = "";
                String cavalo = "";
                String carreta = "";
                String condutor = "";
                String cpf = "";
                String status = "AP0";


                if (cidOCot.isEmpty() || cidDCot.isEmpty() || vlcarga.isEmpty() || pesoc.isEmpty()) {
                    txtCidadeOcot.setError("Este campo é obrigatório!");
                } else if (codigoCot.isEmpty()) {
                    //INSERT
                    dbCot.addcotacao(new DatabaseHelperCot(cidOCot,cidDCot,Float.parseFloat(vlcarga),Float.parseFloat(pesoc),
                            Float.parseFloat(fretep),Float.parseFloat(pedcot),Float.parseFloat(griscot),Float.parseFloat(advcot),
                            Float.parseFloat(icmscot),Float.parseFloat(fretefinal),ncliente,end,ref,Integer.parseInt(qvol),
                            datacol,horacol,obscol,cavalo,carreta,condutor,cpf,status));


                            /*cidOCot,cidDCot,Integer.parseInt(vlcarga),
                            Integer.parseInt(pesoc),Integer.parseInt(fretep),Integer.parseInt(pedcot),
                            Integer.parseInt(griscot),Integer.parseInt(advcot),Integer.parseInt(icmscot),Integer.parseInt(fretefinal),
                            status,"","","",10,"","","","","","","","")));*/

                    Toast.makeText(Cotacao.this, "COTAÇÃO SALVA COM SUCESSO", Toast.LENGTH_SHORT).show();

                    limparCamposCot();
                    listarCotacao();
                } else {
                    //UPDATE
                    dbCot.atualizarCotacao(new DatabaseHelperCot(Integer.parseInt(codigoCot),cidOCot,cidDCot,
                            Float.parseFloat(vlcarga),Float.parseFloat(pesoc),Float.parseFloat(fretep),
                            Float.parseFloat(pedcot),Float.parseFloat(griscot),Float.parseFloat(advcot),
                            Float.parseFloat(icmscot),Float.parseFloat(fretefinal),ncliente,end,ref,Integer.parseInt(qvol),
                            datacol,horacol,obscol,cavalo,carreta,condutor,cpf,status));

                    Toast.makeText(Cotacao.this, "Cotação atualizada com sucesso", Toast.LENGTH_SHORT).show();

                    limparCamposCot();
                    listarCotacao();
                }
            }
        });



        btnAprovarCot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String validar = txtFretePesoCot.getText().toString();

                if (validar.isEmpty() || validar == "0"){
                    Toast.makeText(Cotacao.this, "Selecione uma cotação e precione CALCULAR antes de aprovar", Toast.LENGTH_SHORT).show();

                } else {

                String codigoCot = txtCodigoCot.getText().toString();
                String cidOCot = txtCidadeOcot.getText().toString();
                String cidDCot = txtCidadeDcot.getText().toString();
                String vlcarga = txtValorCarga.getText().toString();
                String pesoc = txtPesoCobrado.getText().toString();
                String fretep = txtFretePesoCot.getText().toString();
                String pedcot = txtPedCot.getText().toString();
                String griscot = txtGrisCot.getText().toString();
                String advcot = txtAdvCot.getText().toString();
                String icmscot = txtIcmsCot.getText().toString();
                String fretefinal = txtFreteTotal.getText().toString();

                String ncliente = "";
                String end = "";
                String ref = "";
                String qvol = "0";
                String datacol = "";
                String horacol = "";
                String obscol = "";
                String cavalo = "";
                String carreta = "";
                String condutor = "";
                String cpf = "";
                String status = "AP1";


                dbCot.atualizarCotacao(new DatabaseHelperCot(Integer.parseInt(codigoCot),cidOCot,cidDCot,
                        Float.parseFloat(vlcarga),Float.parseFloat(pesoc),Float.parseFloat(fretep),
                        Float.parseFloat(pedcot),Float.parseFloat(griscot),Float.parseFloat(advcot),
                        Float.parseFloat(icmscot),Float.parseFloat(fretefinal),ncliente,end,ref,Integer.parseInt(qvol),datacol,horacol,
                        obscol,cavalo,carreta,condutor,cpf,status));




                Toast.makeText(Cotacao.this, "Cotação aprovada com sucesso", Toast.LENGTH_SHORT).show();

                limparCamposCot();
                listarCotacao();
                }}

        });







        //============================= BTN APROVAR =============================

        /*
        btnAprovarCot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigoCot = txtCodigoCot.getText().toString();
                String cidOCot = txtCidadeOcot.getText().toString();
                /*
                String cidDCot = txtCidadeDcot.getText().toString();
                String vlcarga = txtValorCarga.getText().toString();
                String pesoc = txtPesoCobrado.getText().toString();
                String fretep = txtFretePesoCot.getText().toString();
                String pedcot = txtPedCot.getText().toString();
                String griscot = txtGrisCot.getText().toString();
                String advcot = txtAdvCot.getText().toString();
                String icmscot = txtIcmsCot.getText().toString();
                String fretefinal = txtFreteTotal.getText().toString();
                */

                /*
                String ncliente = "";
                String end = "";
                String ref = "";
                String qvol = "0";
                String datacol = "";
                String horacol = "";
                String obscol = "";
                String cavalo = "";
                String carreta = "";
                String condutor = "";
                String cpf = "";
                */
                /*
                String status = "on2";


                if (cidOCot.isEmpty()) {
                    txtCidadeOcot.setError("Este campo é obrigatório!");
                } else if (codigoCot.isEmpty()) {
                    //INSERT
                    dbCot.addcotacao(new DatabaseHelperCot(Integer.parseInt(codigoCot),cidOCot,status));

                    Toast.makeText(Cotacao.this, "COTAÇÃO SALVA COM SUCESSO", Toast.LENGTH_SHORT).show();

                    //limparCamposCot();
                    listarCotacao();
                } else {

                    Toast.makeText(Cotacao.this, "Selecione uma cotação", Toast.LENGTH_SHORT).show();

                    //limparCamposCot();
                    listarCotacao();
                }
            }
        });
        */





        //INSERT
        /*
        dbCot.addcotacao(new DatabaseHelperCot("Curitiba","Sao Paulo",1,2,3,4,5,6,7,8));
        dbCot.addcotacao(new DatabaseHelperCot("ITABUNA","VITORIA",5,5,5,5,5,5,5,5));
        dbCot.addcotacao(new DatabaseHelperCot("Osasco","Registro",2,2,2,2,2,2,2,2));
        dbCot.addcotacao(new DatabaseHelperCot("Bertioga","Vila Velha",9,9,9,9,9,9,9,9));
        */

        //DELETE
        /*
        DatabaseHelperCot cotacao = new DatabaseHelperCot();
        cotacao.setCodigocot(2);
        dbCot.apagarcotacao(cotacao);
        */

        //Toast.makeText(this, "Apagado com sucesso!", Toast.LENGTH_SHORT).show();

        //SELECT
        /*
        DatabaseHelperCot cotacao = dbCot.selecionarCotacao(1);

        Log.d("Cotação selecionada", "Código: " + cotacao.getCodigocot() + " Origem: " + cotacao.getCidocot()
                + " Destino: " + cotacao.getCiddcot() + " Valor Carga: " + cotacao.getVlcarga()
                + " Peso Cobrado: " + cotacao.getPesoc() + " Frete Peso: " + cotacao.getFpeso()
                + " Pedágio: " + cotacao.getPedcot() + " Gris: " + cotacao.getGriscot() + " ADV: " + cotacao.getAdvcot()
                + " ICMS: " + cotacao.getIcmscot() + " Frete Total: " + cotacao.getFtotal());
        Toast.makeText(this, "SELECIONADO", Toast.LENGTH_SHORT).show();
     */
        //UPDATE
        /*
        DatabaseHelperCot cotacao = new DatabaseHelperCot();
        cotacao.setCodigocot(1);
        cotacao.setCidocot("BUCETAO");
        cotacao.setCiddcot("BUCETAO");
        cotacao.setVlcarga(8);
        cotacao.setPesoc(8);
        cotacao.setFpeso(8);
        cotacao.setPedcot(8);
        cotacao.setGriscot(8);
        cotacao.setAdvcot(8);
        cotacao.setIcmscot(8);
        cotacao.setFtotal(8);

        dbCot.atualizarCotacao(cotacao);

        Toast.makeText(Cotacao.this, "Atualizada com sucesso", Toast.LENGTH_SHORT).show();
        */
    }

    void limparCamposCot() {
        txtCodigoCot.setText("");
        txtCidadeOcot.setText("");
        txtCidadeDcot.setText("");
        txtValorCarga.setText("");
        txtPesoCobrado.setText("");
        txtFretePesoCot.setText("");
        txtPedCot.setText("");
        txtGrisCot.setText("");
        txtAdvCot.setText("");
        txtIcmsCot.setText("");
        txtFreteTotal.setText("");
        txtStatus.setText("");

        txtCidadeOcot.requestFocus();
    }

    public void listarCotacao() {

        List<DatabaseHelperCot> cotacao = dbCot.ListaTodasCotacoes();

        arrayListCott = new ArrayList<String>();

        adapterCot = new ArrayAdapter<String>(Cotacao.this, android.R.layout.simple_list_item_1, arrayListCott);

        listViewCot.setAdapter(adapterCot);

        for(DatabaseHelperCot ccot : cotacao) {

            if (ccot.getStatus().equals("AP0")){

            //Log.d("Lista", "\nID: " + ccot.getCodigocot() + " Origem: " + ccot.getCidocot());
            arrayListCott.add(ccot.getCodigocot() + "-" + ccot.getCidocot());
            adapterCot.notifyDataSetChanged();
        }}
    }
}




