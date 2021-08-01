package br.edu.easylog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Projecao extends AppCompatActivity {

    Button b11, btnAprovarProj, btnSalvarProj;

    EditText txtCliente,txtObsProj,txtEndereco,txtReferencia,txtQtdVol,txtPesoProj,
            txtDataCol,txtHoraCol,txtCodigoProj,txtStatusProj,

    txtCidadeOProj,txtCidadeDProj,txtValorCargaProj,txtPesoCobradoProj,txtFretePesoProj,txtPedProj,
            txtGrisProj,txtAdvProj,txtIcmsProj,txtFreteTotalProj;

    ListView listViewProj;

    bancoDadosCot dbCot = new bancoDadosCot(this);
    bancoDados db = new bancoDados(this);

    ArrayAdapter<String> adapterProj;
    ArrayList<String> arrayListProj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projecao);
        setTitle("PROJEÇÃO");


        txtCliente = (EditText) findViewById(R.id.txtCliente);
        txtEndereco = (EditText) findViewById(R.id.txtEndereco);
        txtReferencia = (EditText) findViewById(R.id.txtReferencia);
        txtQtdVol = (EditText) findViewById(R.id.txtQtdVol);
        txtDataCol = (EditText) findViewById(R.id.txtDataCol);
        txtHoraCol = (EditText) findViewById(R.id.txtHoraCol);
        txtObsProj = (EditText) findViewById(R.id.txtObsProj);

        txtPesoProj = (EditText) findViewById(R.id.txtPesoProj);

        txtCodigoProj = (EditText) findViewById(R.id.txtCodigoProj);
        txtStatusProj = (EditText) findViewById(R.id.txtStatus);


        txtCidadeOProj = (EditText) findViewById(R.id.txtCidadeOProj);
        txtCidadeDProj = (EditText) findViewById(R.id.txtCidadeDProj);
        txtValorCargaProj = (EditText) findViewById(R.id.txtValorCargaProj);
        txtPesoCobradoProj = (EditText) findViewById(R.id.txtPesoCobradoProj);
        txtFretePesoProj = (EditText) findViewById(R.id.txtFretePesoProj);
        txtPedProj = (EditText) findViewById(R.id.txtPedProj);
        txtGrisProj = (EditText) findViewById(R.id.txtGrisProj);
        txtAdvProj = (EditText) findViewById(R.id.txtAdvProj);
        txtIcmsProj = (EditText) findViewById(R.id.txtIcmsProj);
        txtFreteTotalProj = (EditText) findViewById(R.id.txtFreteTotalProj);

        btnAprovarProj = (Button) findViewById(R.id.btnAprovarProj);
        btnSalvarProj = (Button) findViewById(R.id.btnSalvarProj);

        listViewProj = (ListView) findViewById(R.id.listViewProj);

        listarCotacaoProj();


        b11 = (Button)findViewById(R.id.button11);
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Projecao.this,Modulos.class);
                startActivity(k);
            }
        });


        listViewProj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String conteudoCot = (String) listViewProj.getItemAtPosition(position);

                //Toast.makeText(Cotacao.this, "Select: " + conteudoCot, Toast.LENGTH_SHORT).show();
                String codigo = conteudoCot.substring(0, conteudoCot.indexOf("-"));

                DatabaseHelperCot cotacao = dbCot.selecionarCotacao(Integer.parseInt(codigo));
                DatabaseHelper2 rota2 = db.selecionarRota(Integer.parseInt(codigo));

                //EDITAVEIS


                txtCodigoProj.setText(String.valueOf(cotacao.getCodigocot()));

                txtCidadeOProj.setText(cotacao.getCidocot());
                txtCidadeDProj.setText(cotacao.getCiddcot());
                txtValorCargaProj.setText(String.valueOf(cotacao.getVlcarga()));

                txtPesoProj.setText(String.valueOf(cotacao.getPesoc()));

                txtFretePesoProj.setText(String.valueOf(cotacao.getFpeso()));
                txtPedProj.setText(String.valueOf(rota2.getPed()));
                txtGrisProj.setText(String.valueOf(rota2.getGris()));
                txtAdvProj.setText(String.valueOf((rota2.getAdv())));
                txtIcmsProj.setText(String.valueOf(cotacao.getIcmscot()));
                txtFreteTotalProj.setText(String.valueOf(cotacao.getFtotal()));


                //txtStatusProj.setText(cotacao.getStatus());


                /*
                txtCodigoProj.setText(String.valueOf(cotacao.getCodigocot()));
                txtCidadeOProj.setText(cotacao.getCidocot());
                txtCidadeDProj.setText(cotacao.getCiddcot());
                txtValorCargaProj.setText(String.valueOf(cotacao.getVlcarga()));
                txtPesoCobradoProj.setText(String.valueOf(cotacao.getPesoc()));
                txtStatusProj.setText(cotacao.getStatus());
                */


            }
        });

        btnSalvarProj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigoCot = txtCodigoProj.getText().toString();

                /*
                String cidOCot = txtCidadeOProj.getText().toString();
                String cidDCot = txtCidadeDProj.getText().toString();
                String vlcarga = txtValorCargaProj.getText().toString();
                String pesoc = txtPesoCobradoProj.getText().toString();
                String fretep = txtFretePesoProj.getText().toString();
                String pedcot = txtPedProj.getText().toString();
                String griscot = txtGrisProj.getText().toString();
                String advcot = txtAdvProj.getText().toString();
                String icmscot = txtIcmsProj.getText().toString();
                String fretefinal = txtFreteTotalProj.getText().toString();
                */


                String ncliente = txtCliente.getText().toString();
                String end = txtEndereco.getText().toString();
                String ref = txtReferencia.getText().toString();
                String qvol = txtQtdVol.getText().toString();
                String datacol = txtDataCol.getText().toString();
                String horacol = txtHoraCol.getText().toString();
                String obscol = txtObsProj.getText().toString();
                //String pesoproj = txtPesoProj.getText().toString();

                String cavalo = "";
                String carreta = "";
                String condutor = "";
                String cpf = "";
                String status = "AP1";


                if (ncliente.isEmpty()) {
                    txtCliente.setError("Este campo é obrigatório!");
                    txtEndereco.setError("Este campo é obrigatório!");
                    txtReferencia.setError("Este campo é obrigatório!");
                    txtQtdVol.setError("Este campo é obrigatório!");
                    txtDataCol.setError("Este campo é obrigatório!");
                    txtHoraCol.setError("Este campo é obrigatório!");
                    txtObsProj.setError("Este campo é obrigatório!");



                } else if (codigoCot.isEmpty()) {
                    //INSERT
                    //dbCot.atualizarProjecao(new DatabaseHelperCot(Integer.parseInt(codigoCot),ncliente,end,
                    //        ref,Integer.parseInt(qvol),datacol,horacol,obscol,cavalo,carreta,condutor,cpf,status));

                    Toast.makeText(Projecao.this, "É NECESSÁRIO SELECIONAR UMA COTAÇÃO.", Toast.LENGTH_SHORT).show();

                    /*dbCot.atualizarProjecao(new DatabaseHelperCot(Integer.parseInt(codigoCot),/*cidOCot,cidDCot,
                            Integer.parseInt(vlcarga),Integer.parseInt(pesoc),Integer.parseInt(fretep),
                            Integer.parseInt(pedcot),Integer.parseInt(griscot),Integer.parseInt(advcot),
                            Integer.parseInt(icmscot),Integer.parseInt(fretefinal),ncliente,end,ref,Integer.parseInt(qvol),
                            datacol,horacol,obscol,cavalo,carreta,condutor,cpf,status));*/


                    //limparCamposCot();
                    //listarCotacao();

                } else {
                    //UPDATE

                    dbCot.atualizarProjecao(new DatabaseHelperCot(Integer.parseInt(codigoCot),/*cidOCot,cidDCot,
                            Integer.parseInt(vlcarga),Integer.parseInt(pesoc),Integer.parseInt(fretep),
                            Integer.parseInt(pedcot),Integer.parseInt(griscot),Integer.parseInt(advcot),
                            Integer.parseInt(icmscot),Integer.parseInt(fretefinal),*/ncliente,end,ref,Integer.parseInt(qvol),
                            datacol,horacol,obscol,cavalo,carreta,condutor,cpf,status));


                    Toast.makeText(Projecao.this, "PROJEÇÃO SALVA COM SUCESSO", Toast.LENGTH_SHORT).show();
                    listarCotacaoProj();
                }
            }
        });

        btnAprovarProj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigoCot = txtCodigoProj.getText().toString();

                /*
                String cidOCot = txtCidadeOProj.getText().toString();
                String cidDCot = txtCidadeDProj.getText().toString();
                String vlcarga = txtValorCargaProj.getText().toString();
                String pesoc = txtPesoCobradoProj.getText().toString();
                String fretep = txtFretePesoProj.getText().toString();
                String pedcot = txtPedProj.getText().toString();
                String griscot = txtGrisProj.getText().toString();
                String advcot = txtAdvProj.getText().toString();
                String icmscot = txtIcmsProj.getText().toString();
                String fretefinal = txtFreteTotalProj.getText().toString();
                */


                String ncliente = txtCliente.getText().toString();
                String end = txtEndereco.getText().toString();
                String ref = txtReferencia.getText().toString();
                String qvol = txtQtdVol.getText().toString();
                String datacol = txtDataCol.getText().toString();
                String horacol = txtHoraCol.getText().toString();
                String obscol = txtObsProj.getText().toString();
                //String pesoproj = txtPesoProj.getText().toString();

                String cavalo = "";
                String carreta = "";
                String condutor = "";
                String cpf = "";
                String status = "AP2";


                if (ncliente.isEmpty()) {
                    txtCliente.setError("Este campo é obrigatório!");
                    txtEndereco.setError("Este campo é obrigatório!");
                    txtReferencia.setError("Este campo é obrigatório!");
                    txtQtdVol.setError("Este campo é obrigatório!");
                    txtDataCol.setError("Este campo é obrigatório!");
                    txtHoraCol.setError("Este campo é obrigatório!");
                    txtObsProj.setError("Este campo é obrigatório!");



                } else if (codigoCot.isEmpty()) {
                    //INSERT
                    //dbCot.atualizarProjecao(new DatabaseHelperCot(Integer.parseInt(codigoCot),ncliente,end,
                    //        ref,Integer.parseInt(qvol),datacol,horacol,obscol,cavalo,carreta,condutor,cpf,status));

                    Toast.makeText(Projecao.this, "É NECESSÁRIO SELECIONAR UMA COTAÇÃO.", Toast.LENGTH_SHORT).show();

                    /*dbCot.atualizarProjecao(new DatabaseHelperCot(Integer.parseInt(codigoCot),/*cidOCot,cidDCot,
                            Integer.parseInt(vlcarga),Integer.parseInt(pesoc),Integer.parseInt(fretep),
                            Integer.parseInt(pedcot),Integer.parseInt(griscot),Integer.parseInt(advcot),
                            Integer.parseInt(icmscot),Integer.parseInt(fretefinal),ncliente,end,ref,Integer.parseInt(qvol),
                            datacol,horacol,obscol,cavalo,carreta,condutor,cpf,status));*/


                    //limparCamposCot();
                    //listarCotacao();

                } else {
                    //UPDATE

                    dbCot.atualizarProjecao(new DatabaseHelperCot(Integer.parseInt(codigoCot),/*cidOCot,cidDCot,
                            Integer.parseInt(vlcarga),Integer.parseInt(pesoc),Integer.parseInt(fretep),
                            Integer.parseInt(pedcot),Integer.parseInt(griscot),Integer.parseInt(advcot),
                            Integer.parseInt(icmscot),Integer.parseInt(fretefinal),*/ncliente,end,ref,Integer.parseInt(qvol),
                            datacol,horacol,obscol,cavalo,carreta,condutor,cpf,status));


                    Toast.makeText(Projecao.this, "PROJEÇÃO APROVADA COM SUCESSO", Toast.LENGTH_SHORT).show();
                    listarCotacaoProj();
                    limparCamposProj();
                }
            }
        });

    }





    public void listarCotacaoProj() {

        List<DatabaseHelperCot> cotacao = dbCot.ListaTodasCotacoes();

        arrayListProj = new ArrayList<String>();

        adapterProj = new ArrayAdapter<String>(Projecao.this, android.R.layout.simple_list_item_1, arrayListProj);

        listViewProj.setAdapter(adapterProj);

        for(DatabaseHelperCot ccot : cotacao) {

            if (ccot.getStatus().equals("AP1")){

                //Log.d("Lista", "\nID: " + ccot.getCodigocot() + " Origem: " + ccot.getCidocot());
                arrayListProj.add(ccot.getCodigocot() + "-" + ccot.getCidocot());
                adapterProj.notifyDataSetChanged();
            }}
    }

    void limparCamposProj() {
        txtCliente.setText("");
        txtObsProj.setText("");
        txtEndereco.setText("");
        txtReferencia.setText("");
        txtQtdVol.setText("");
        txtPesoProj.setText("");
        txtDataCol.setText("");
        txtHoraCol.setText("");
    }

    /*public void listarCotacaoSol() {

        List<DatabaseHelperCot> cotacao = dbCot.ListaTodasCotacoes();

        arrayListSol = new ArrayList<String>();

        adapterSol = new ArrayAdapter<String>(Projecao.this, android.R.layout.simple_list_item_1, arrayListProj);

        listViewSol.setAdapter(adapterSol);

        for(DatabaseHelperCot ccot : cotacao) {

            if (ccot.getStatus().equals("AP1")){

                //Log.d("Lista", "\nID: " + ccot.getCodigocot() + " Origem: " + ccot.getCidocot());
                arrayListSol.add(ccot.getCodigocot() + "-" + ccot.getCidocot());
                adapterSol.notifyDataSetChanged();
            }}
    }

*/

}