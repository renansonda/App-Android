package br.edu.easylog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.pdf.PdfDocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solicitacao extends AppCompatActivity {

    Button btnProjecao, btnVoltarSol, btnAprovarSol;

    EditText txtPlacaCavalo, txtPlacaCarreta, txtCondutor, txtCpf, txtStatusSol, txtCodigoSol;

    EditText txtCidadeOSol, txtCidadeDSol, txtValorCargaSol, txtPesoSol, txtFretePesoSol, txtPedSol, txtGrisSol, txtAdvSol, txtIcmsSol,
            txtFreteTotalSol;

    EditText txtClienteSol, txtEnderecoSol, txtReferenciaSol, txtQtdVolSol, txtDataSol, txtHoraSol, txtObsSol;

    TextView textViewNome;

    ListView listViewSol;

    bancoDadosCot dbCot = new bancoDadosCot(this);
    bancoDados db = new bancoDados(this);

    ArrayAdapter<String> adapterSol;
    ArrayList<String> arrayListSol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);
        setTitle("SOLICITAÇÃO");

        textViewNome = (TextView) findViewById(R.id.textViewNome);

        txtPlacaCavalo = (EditText) findViewById(R.id.txtPlacaCavalo);
        txtPlacaCarreta = (EditText) findViewById(R.id.txtPlacaCarreta);
        txtCondutor = (EditText) findViewById(R.id.txtCondutor);
        txtCpf = (EditText) findViewById(R.id.txtCpf);

        txtCodigoSol = (EditText) findViewById(R.id.txtCodigoSol);
        txtStatusSol = (EditText) findViewById(R.id.txtStatus);

        txtCidadeOSol = (EditText) findViewById(R.id.txtCidadeOSol);
        txtCidadeDSol = (EditText) findViewById(R.id.txtCidadeDSol);
        txtValorCargaSol = (EditText) findViewById(R.id.txtValorCargaSol);
        txtPesoSol = (EditText) findViewById(R.id.txtPesoSol);
        txtFretePesoSol = (EditText) findViewById(R.id.txtFretePesoSol);
        txtPedSol = (EditText) findViewById(R.id.txtPedSol);
        txtGrisSol = (EditText) findViewById(R.id.txtGrisSol);
        txtAdvSol = (EditText) findViewById(R.id.txtAdvSol);
        txtIcmsSol = (EditText) findViewById(R.id.txtIcmsSol);
        txtFreteTotalSol = (EditText) findViewById(R.id.txtFreteTotalSol);

        btnAprovarSol = (Button) findViewById(R.id.btnAprovarSol);
        btnProjecao = (Button) findViewById(R.id.btnProjecao);
        btnVoltarSol = (Button) findViewById(R.id.btnVoltarSol);

        txtClienteSol = (EditText) findViewById(R.id.txtClienteSol);
        txtEnderecoSol = (EditText) findViewById(R.id.txtEnderecoSol);
        txtReferenciaSol = (EditText) findViewById(R.id.txtReferenciaSol);
        txtQtdVolSol = (EditText) findViewById(R.id.txtQtdVolSol);
        txtDataSol = (EditText) findViewById(R.id.txtDataSol);
        txtHoraSol = (EditText) findViewById(R.id.txtHoraSol);
        txtObsSol = (EditText) findViewById(R.id.txtObsSol);

        listViewSol = (ListView) findViewById(R.id.listViewSol);

        listarCotacaoSol();

        iniciarAplicativo();

        /*
        btnProjecao = (Button)findViewById(R.id.btnProjecao);
        btnProjecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Solicitacao.this,Projecao.class);
                startActivity(k);
            }
        });
        */

        btnVoltarSol = (Button)findViewById(R.id.btnVoltarSol);
        btnVoltarSol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Solicitacao.this,Modulos.class);
                startActivity(k);
            }
        });

       /* btnProjecao = (Button)findViewById(R.id.btnProjecao);
        btnProjecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Solicitacao.this,Projecao.class);
                startActivity(k);
            }
        });*/


        listViewSol.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String conteudoCot = (String) listViewSol.getItemAtPosition(position);

                String codigo = conteudoCot.substring(0, conteudoCot.indexOf("-"));

                DatabaseHelperCot cotacao = dbCot.selecionarCotacao(Integer.parseInt(codigo));
                DatabaseHelper2 rota2 = db.selecionarRota(Integer.parseInt(codigo));

                //EDITAVEIS
                txtCodigoSol.setText(String.valueOf(cotacao.getCodigocot()));

                txtCidadeOSol.setText(cotacao.getCidocot());
                txtCidadeDSol.setText(cotacao.getCiddcot());
                txtValorCargaSol.setText(String.valueOf(cotacao.getVlcarga()));

                txtPesoSol.setText(String.valueOf(cotacao.getPesoc()));

                txtFretePesoSol.setText(String.valueOf(cotacao.getFpeso()));
                txtPedSol.setText(String.valueOf(rota2.getPed()));
                txtGrisSol.setText(String.valueOf(rota2.getGris()));
                txtAdvSol.setText(String.valueOf((rota2.getAdv())));
                txtIcmsSol.setText(String.valueOf(cotacao.getIcmscot()));
                txtFreteTotalSol.setText(String.valueOf(cotacao.getFtotal()));

                txtClienteSol.setText(cotacao.getNcliente());
                txtEnderecoSol.setText(cotacao.getEnd());
                txtReferenciaSol.setText(cotacao.getRef());
                txtQtdVolSol.setText(String.valueOf(cotacao.getQvol()));
                txtDataSol.setText(cotacao.getDatacol());
                txtHoraSol.setText(cotacao.getHoracol());
                txtObsSol.setText(cotacao.getObscol());

            }
        });

        btnAprovarSol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigoCot = txtCodigoSol.getText().toString();




                String ncliente = txtClienteSol.getText().toString();
                String end = txtEnderecoSol.getText().toString();
                String ref = txtReferenciaSol.getText().toString();
                String qvol = txtQtdVolSol.getText().toString();
                String datacol = txtDataSol.getText().toString();
                String horacol = txtHoraSol.getText().toString();
                String obscol = txtObsSol.getText().toString();


                String cavalo = txtPlacaCavalo.getText().toString();
                String carreta = txtPlacaCarreta.getText().toString();
                String condutor = txtCondutor.getText().toString();
                String cpf = txtCpf.getText().toString();
                String status = "AP2";


                if (cavalo.isEmpty() || carreta.isEmpty() || condutor.isEmpty() || cpf.isEmpty()) {
                    txtPlacaCavalo.setError("Este campo é obrigatório!");
                    txtPlacaCarreta.setError("Este campo é obrigatório!");
                    txtCondutor.setError("Este campo é obrigatório!");
                    txtCpf.setError("Este campo é obrigatório!");


                } else if (codigoCot.isEmpty()) {
                    //INSERT
                    Toast.makeText(Solicitacao.this, "É NECESSÁRIO SELECIONAR UMA PROJEÇÃO.", Toast.LENGTH_SHORT).show();

                } else {
                    //UPDATE
                    dbCot.atualizarProjecao(new DatabaseHelperCot(Integer.parseInt(codigoCot),ncliente,end,ref,Integer.parseInt(qvol),
                            datacol,horacol,obscol,cavalo,carreta,condutor,cpf,status));

                    Toast.makeText(Solicitacao.this, "PROJEÇÃO SALVA COM SUCESSO", Toast.LENGTH_SHORT).show();
                    listarCotacaoSol();
                }
            }
        });




        /*
        btnAprovarSol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigoCot = txtCodigoSol.getText().toString();

                String ncliente = txtClienteSol.getText().toString();
                String end = txtEnderecoSol.getText().toString();
                String ref = txtReferenciaSol.getText().toString();
                String qvol = txtQtdVolSol.getText().toString();
                String datacol = txtDataSol.getText().toString();
                String horacol = txtHoraSol.getText().toString();
                String obscol = txtObsSol.getText().toString();

                String cavalo = "";
                String carreta = "";
                String condutor = "";
                String cpf = "";
                String status = "AP2";


                if (ncliente.isEmpty()) {
                    txtClienteSol.setError("Este campo é obrigatório!");
                    txtEnderecoSol.setError("Este campo é obrigatório!");
                    txtReferenciaSol.setError("Este campo é obrigatório!");
                    txtQtdVolSol.setError("Este campo é obrigatório!");
                    txtDataSol.setError("Este campo é obrigatório!");
                    txtHoraSol.setError("Este campo é obrigatório!");
                    txtObsSol.setError("Este campo é obrigatório!");



                } else if (codigoCot.isEmpty()) {

                    Toast.makeText(Solicitacao.this, "É NECESSÁRIO SELECIONAR UMA COTAÇÃO.", Toast.LENGTH_SHORT).show();

                } else {
                    //UPDATE
                    dbCot.atualizarProjecao(new DatabaseHelperCot(Integer.parseInt(codigoCot),ncliente,end,ref,Integer.parseInt(qvol),
                            datacol,horacol,obscol,cavalo,carreta,condutor,cpf,status));

                    Toast.makeText(Solicitacao.this, "PROJEÇÃO APROVADA COM SUCESSO", Toast.LENGTH_SHORT).show();
                    listarCotacaoSol();
                }
            }
        });
        */
    }

    public void listarCotacaoSol() {

        List<DatabaseHelperCot> cotacao = dbCot.ListaTodasCotacoes();

        arrayListSol = new ArrayList<String>();

        adapterSol = new ArrayAdapter<String>(Solicitacao.this, android.R.layout.simple_list_item_1, arrayListSol);

        listViewSol.setAdapter(adapterSol);

        for(DatabaseHelperCot ccot : cotacao) {

            if (ccot.getStatus().equals("AP2")){

                //Log.d("Lista", "\nID: " + ccot.getCodigocot() + " Origem: " + ccot.getCidocot());
                arrayListSol.add(ccot.getCodigocot() + "-" + ccot.getCidocot());
                adapterSol.notifyDataSetChanged();
            }}
    }

    private void iniciarAplicativo() {

        txtPlacaCavalo = findViewById(R.id.txtPlacaCavalo);
        btnProjecao = findViewById(R.id.btnAprovarSol);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void gerarPDF(View view) {
        //Cria um documento para gerar o PDF
        PdfDocument documentoPDF = new PdfDocument();

        //Especifica detalhes da página
        PdfDocument.PageInfo detalhesDaPagina =
                new PdfDocument.PageInfo.Builder(500,600,1).create();

        //Cria primeira página
        PdfDocument.Page novaPagina = documentoPDF.startPage(detalhesDaPagina);

        Canvas canvas = novaPagina.getCanvas();

        Paint corDoTexto = new Paint();
        corDoTexto.setColor(Color.MAGENTA);

        canvas.drawText(textViewNome.getText().toString(), 105, 100, corDoTexto);
        canvas.drawText("Placa cavalo: " + txtPlacaCarreta.getText().toString(), 105, 120, corDoTexto);
        canvas.drawText("Placa carreta: " + txtPlacaCavalo.getText().toString(), 105, 140, corDoTexto);
        canvas.drawText("Motorista: " + txtCondutor.getText().toString(), 105, 160, corDoTexto);
        canvas.drawText("CPF: " + txtCpf.getText().toString(), 105, 180, corDoTexto);
        canvas.drawText("Endereço de Coleta: " + txtEnderecoSol.getText().toString(), 105, 200, corDoTexto);
        canvas.drawText("Data Coleta: " + txtDataSol.getText().toString(), 105, 220, corDoTexto);
        canvas.drawText("Horário da Coleta: " + txtHoraSol.getText().toString(), 105, 240, corDoTexto);
        canvas.drawText("Quantidade de volumes " + txtQtdVolSol.getText().toString(), 105, 260, corDoTexto);
        canvas.drawText("Peso Carga " + txtPesoSol.getText().toString(), 105, 280, corDoTexto);

        corDoTexto.setColor(Color.BLUE);

        //Finaliza a pagina
        documentoPDF.finishPage(novaPagina);

        //Cria o arquivo no SDCARD PDF
        @SuppressLint("SdCardPath") String targetPDF = "/sdcard/pdfModelo4.pdf";
        File filePath = new File(targetPDF);

        try {
            documentoPDF.writeTo(new FileOutputStream(filePath));
            Toast.makeText(Solicitacao.this, "PDF Gerado com Sucesso...", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(Solicitacao.this, "Falha ao Gerar PDF erro1: " + e.toString(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(Solicitacao.this, "Falha ao Gerar PDF erro 2: " + e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}