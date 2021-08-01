package br.edu.easylog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.CredentialProtectedWhileLockedViolation;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//import static br.edu.easylog.Cotacao.CYTYO;
import static br.edu.easylog.Cotacao.txtCidadeOcot;
import static br.edu.easylog.Cotacao.txtValorCarga;

public class bancoDadosCot extends SQLiteOpenHelper {

    static int TESTE;

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_COTACAO = "db_cotacao";

    private static final String TABELA_COTACAO = "tb_cotacao";
    //COTACAO
    private static final String COLUNA_CODIGOCOT = "codigocot";

    private static final String COLUNA_CIDADEOCOT = "cidocot";
    private static final String COLUNA_CIDADEDCOT = "ciddcot";
    private static final String COLUNA_VALORCARGA = "vlcarga";
    private static final String COLUNA_PESOCOBRADO = "pesoc";
    private static final String COLUNA_FRETEPESO = "fpeso";
    private static final String COLUNA_PEDCOT = "pedcot";
    private static final String COLUNA_GRISCOT = "griscot";
    private static final String COLUNA_ADVCOT = "advcot";
    private static final String COLUNA_ICMSCOT = "icmscot";
    private static final String COLUNA_FRETEFINAL = "ftotal";
    private static final String COLUNA_STATUS = "status";

    private static final int VERSAO_BANCOS = 2;
    private static final String BANCO_ROTAS = "db_rotas";

    private static final String TABELA_ROTAS = "tb_rotas";
    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_CIDADEO = "cidadeo";
    private static final String COLUNA_CIDADED = "cidaded";
    private static final String COLUNA_FRETEP = "fretep";
    private static final String COLUNA_PED = "ped";
    private static final String COLUNA_GRIS = "gris";
    private static final String COLUNA_ADV = "adv";
    private static final String COLUNA_ICMS = "icms";

    //PROJECAO
    private static final String COLUNA_NOMECLIENTE = "cliente";
    private static final String COLUNA_ENDERECOCOL = "endereco";
    private static final String COLUNA_REFERENCIA = "ref";
    private static final String COLUNA_QUANTIDADEVOL = "volumes";
    private static final String COLUNA_DATACOLETA = "datacol";
    private static final String COLUNA_HORACOLETA = "horacol";
    private static final String COLUNA_OBSERVACAO = "obs";
    //SOLICITACAO
    private static final String COLUNA_PLACATRACAO = "cavalo";
    private static final String COLUNA_PLACACARRETA = "carreta";
    private static final String COLUNA_NOMECONDUTOR = "condutor";
    private static final String COLUNA_CPF = "cpf";



    public bancoDadosCot(Context context) {
        super(context, BANCO_COTACAO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase dbCot) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_COTACAO + "("+ COLUNA_CODIGOCOT + " INTEGER PRIMARY KEY, " + COLUNA_CIDADEOCOT + " TEXT, "
                + COLUNA_CIDADEDCOT + " TEXT, " + COLUNA_VALORCARGA + " FLOAT, " + COLUNA_PESOCOBRADO + " FLOAT, "
                + COLUNA_FRETEPESO + " FLOAT, " + COLUNA_PEDCOT + " FLOAT, " + COLUNA_GRISCOT + " FLOAT, "
                + COLUNA_ADVCOT + " FLOAT, " + COLUNA_ICMSCOT + " FLOAT, " + COLUNA_FRETEFINAL + " FLOAT, "
                + COLUNA_NOMECLIENTE + " TEXT, " + COLUNA_ENDERECOCOL + " TEXT, " + COLUNA_REFERENCIA + " TEXT, "
                + COLUNA_QUANTIDADEVOL + " INTEGER, " + COLUNA_DATACOLETA + " DATE, " + COLUNA_HORACOLETA + " TIME, "
                + COLUNA_OBSERVACAO + " TEXT, " + COLUNA_PLACATRACAO + " TEXT, " + COLUNA_PLACACARRETA + " TEXT, "
                + COLUNA_NOMECONDUTOR + " TEXT, " + COLUNA_CPF + " TEXT, " + COLUNA_STATUS + " TEXT)";

        dbCot.execSQL(QUERY_COLUNA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //CRUD ABAIXO

    void addcotacao(DatabaseHelperCot cotacao) {

        SQLiteDatabase dbCot = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_CIDADEOCOT, cotacao.getCidocot());
        values.put(COLUNA_CIDADEDCOT, cotacao.getCiddcot());
        values.put(COLUNA_VALORCARGA, cotacao.getVlcarga());
        values.put(COLUNA_PESOCOBRADO, cotacao.getPesoc());
        values.put(COLUNA_FRETEPESO, cotacao.getFpeso());
        values.put(COLUNA_PEDCOT, cotacao.getPedcot());
        values.put(COLUNA_GRISCOT, cotacao.getGriscot());
        values.put(COLUNA_ADVCOT, cotacao.getAdvcot());
        values.put(COLUNA_ICMSCOT, cotacao.getIcmscot());
        values.put(COLUNA_FRETEFINAL, cotacao.getFtotal());


        values.put(COLUNA_NOMECLIENTE, cotacao.getNcliente());
        values.put(COLUNA_ENDERECOCOL, cotacao.getEnd());
        values.put(COLUNA_REFERENCIA, cotacao.getRef());
        values.put(COLUNA_QUANTIDADEVOL, cotacao.getQvol());
        values.put(COLUNA_DATACOLETA, cotacao.getDatacol());
        values.put(COLUNA_HORACOLETA, cotacao.getHoracol());
        values.put(COLUNA_OBSERVACAO, cotacao.getObscol());

        values.put(COLUNA_PLACATRACAO, cotacao.getCavalo());
        values.put(COLUNA_PLACACARRETA, cotacao.getCarreta());
        values.put(COLUNA_NOMECONDUTOR, cotacao.getCondutor());
        values.put(COLUNA_CPF, cotacao.getCpf());

        values.put(COLUNA_STATUS, cotacao.getStatus());

        dbCot.insert(TABELA_COTACAO, null, values);
        dbCot.close();
    }

    void apagarcotacao(DatabaseHelperCot cotacao){

        SQLiteDatabase dbCot = this.getWritableDatabase();

        dbCot.delete(TABELA_COTACAO, COLUNA_CODIGOCOT + " = ?", new String[] { String.valueOf(cotacao.getCodigocot())});

        dbCot.close();
    }

    DatabaseHelperCot selecionarCotacao(int codigo) {
        SQLiteDatabase dbCot = this.getReadableDatabase();

        Cursor cursorCot = dbCot.query(TABELA_COTACAO, new String[] {COLUNA_CODIGOCOT, COLUNA_CIDADEOCOT,
        COLUNA_CIDADEDCOT, COLUNA_VALORCARGA, COLUNA_PESOCOBRADO, COLUNA_FRETEPESO,
        COLUNA_PEDCOT, COLUNA_GRISCOT, COLUNA_ADVCOT, COLUNA_ICMSCOT, COLUNA_FRETEFINAL,
        COLUNA_NOMECLIENTE, COLUNA_ENDERECOCOL, COLUNA_REFERENCIA, COLUNA_QUANTIDADEVOL,
       COLUNA_DATACOLETA, COLUNA_HORACOLETA, COLUNA_OBSERVACAO, COLUNA_PLACATRACAO,
        COLUNA_PLACACARRETA, COLUNA_NOMECONDUTOR, COLUNA_CPF, COLUNA_STATUS}, COLUNA_CODIGOCOT +
                " = ?", new String[] {String.valueOf(codigo)}, null, null, null, null);

        if(cursorCot != null) {
            cursorCot.moveToFirst();
        }

        DatabaseHelperCot cotacao = new DatabaseHelperCot(Integer.parseInt(cursorCot.getString(0)),
                cursorCot.getString(1), cursorCot.getString(2), cursorCot.getFloat(3), cursorCot.getFloat(4),
                cursorCot.getFloat(5), cursorCot.getFloat(6), cursorCot.getFloat(7), cursorCot.getFloat(8),
                cursorCot.getFloat(9), cursorCot.getFloat(10), cursorCot.getString(11), cursorCot.getString(12), cursorCot.getString(13),
                cursorCot.getInt(14), cursorCot.getString(15), cursorCot.getString(16), cursorCot.getString(17),
                cursorCot.getString(18), cursorCot.getString(19), cursorCot.getString(20), cursorCot.getString(21),
                cursorCot.getString(22));

        return cotacao;

        /*DatabaseHelperCot cotacao = new DatabaseHelperCot(Integer.parseInt(cursorCot.getString(0)),
                cursorCot.getString(1), cursorCot.getString(2), cursorCot.getInt(3), cursorCot.getInt(4),
                cursorCot.getInt(5), cursorCot.getInt(6), cursorCot.getInt(7), cursorCot.getInt(8), cursorCot.getInt(9),
                cursorCot.getInt(10)/*, cursorCot.getString(11), cursorCot.getString(12), cursorCot.getString(13),
                cursorCot.getInt(14), cursorCot.getString(15), cursorCot.getString(16), cursorCot.getString(17),
                cursorCot.getString(18), cursorCot.getString(19), cursorCot.getString(20), cursorCot.getString(21),
                cursorCot.getString(22));

        return cotacao;
    }*/

    /*void atualizarCotacao(DatabaseHelperCot cotacao) {

        SQLiteDatabase dbCotacao = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_CIDADEOCOT, cotacao.getCidocot());
        values.put(COLUNA_CIDADEDCOT, cotacao.getCiddcot());
        values.put(COLUNA_VALORCARGA, cotacao.getVlcarga());
        values.put(COLUNA_PESOCOBRADO, cotacao.getPesoc());
        values.put(COLUNA_FRETEPESO, cotacao.getFpeso());
        values.put(COLUNA_PEDCOT, cotacao.getPedcot());
        values.put(COLUNA_GRISCOT, cotacao.getGriscot());
        values.put(COLUNA_ADVCOT, cotacao.getAdvcot());
        values.put(COLUNA_ICMSCOT, cotacao.getIcmscot());
        values.put(COLUNA_FRETEFINAL, cotacao.getFtotal());

        /*values.put(COLUNA_NOMECLIENTE, cotacao.getNcliente());
        values.put(COLUNA_ENDERECOCOL, cotacao.getEnd());
        values.put(COLUNA_REFERENCIA, cotacao.getRef());
        values.put(COLUNA_QUANTIDADEVOL, cotacao.getQvol());
        values.put(COLUNA_DATACOLETA, cotacao.getDatacol());
        values.put(COLUNA_HORACOLETA, cotacao.getHoracol());
        values.put(COLUNA_OBSERVACAO, cotacao.getObscol());

        values.put(COLUNA_PLACATRACAO, cotacao.getCavalo());
        values.put(COLUNA_PLACACARRETA, cotacao.getCarreta());
        values.put(COLUNA_NOMECONDUTOR, cotacao.getCondutor());
        values.put(COLUNA_CPF, cotacao.getCpf());
        values.put(COLUNA_STATUS, cotacao.getStatus());

        dbCotacao.update(TABELA_COTACAO, values, COLUNA_CODIGOCOT + " = ?",
                new String[] {String.valueOf(cotacao.getCodigocot())});
    }*/

   /* public List<DatabaseHelperCot> ListaTodasCotacoes() {
        List<DatabaseHelperCot> listaCotacao = new ArrayList<DatabaseHelperCot>();

        String query = "SELECT * FROM " + TABELA_COTACAO;

        SQLiteDatabase dbcot = this.getWritableDatabase();
        Cursor ccot = dbcot.rawQuery(query, null);

        if (ccot.moveToFirst()) {
            do {
                DatabaseHelperCot cotacao = new DatabaseHelperCot();
                cotacao.setCodigocot(Integer.parseInt(ccot.getString(0)));
                cotacao.setCidocot(ccot.getString(1));
                cotacao.setCiddcot(ccot.getString(2));
                cotacao.setVlcarga(Integer.parseInt(ccot.getString(3)));
                cotacao.setPesoc(Integer.parseInt(ccot.getString(4)));
                cotacao.setFpeso(Integer.parseInt(ccot.getString(5)));
                cotacao.setPedcot(Integer.parseInt(ccot.getString(6)));
                cotacao.setGriscot(Integer.parseInt(ccot.getString(7)));
                cotacao.setAdvcot(Integer.parseInt(ccot.getString(8)));
                cotacao.setIcmscot(Integer.parseInt(ccot.getString(9)));
                cotacao.setFtotal(Integer.parseInt(ccot.getString(10)));
                /*cotacao.setNcliente(ccot.getString(11));
                cotacao.setEnd(ccot.getString(12));
                cotacao.setRef(ccot.getString(13));
                cotacao.setQvol(Integer.parseInt(ccot.getString(14)));
                cotacao.setDatacol(ccot.getString(15));
                cotacao.setHoracol(ccot.getString(16));
                cotacao.setObscol(ccot.getString(17));
                cotacao.setCavalo(ccot.getString(18));
                cotacao.setCarreta(ccot.getString(19));
                cotacao.setCondutor(ccot.getString(20));
                cotacao.setCpf(ccot.getString(21));
                cotacao.setStatus(ccot.getString(22));

                listaCotacao.add(cotacao);
            } while (ccot.moveToNext());
        }

        return listaCotacao;
    }*/

    }

    void atualizarCotacao(DatabaseHelperCot cotacao) {

        SQLiteDatabase dbCot = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_CIDADEOCOT, cotacao.getCidocot());
        values.put(COLUNA_CIDADEDCOT, cotacao.getCiddcot());
        values.put(COLUNA_VALORCARGA, cotacao.getVlcarga());
        values.put(COLUNA_PESOCOBRADO, cotacao.getPesoc());
        values.put(COLUNA_FRETEPESO, cotacao.getFpeso());
        values.put(COLUNA_PEDCOT, cotacao.getPedcot());
        values.put(COLUNA_GRISCOT, cotacao.getGriscot());
        values.put(COLUNA_ADVCOT, cotacao.getAdvcot());
        values.put(COLUNA_ICMSCOT, cotacao.getIcmscot());
        values.put(COLUNA_FRETEFINAL, cotacao.getFtotal());

        values.put(COLUNA_NOMECLIENTE, cotacao.getNcliente());
        values.put(COLUNA_ENDERECOCOL, cotacao.getEnd());
        values.put(COLUNA_REFERENCIA, cotacao.getRef());
        values.put(COLUNA_QUANTIDADEVOL, cotacao.getQvol());
        values.put(COLUNA_DATACOLETA, cotacao.getDatacol());
        values.put(COLUNA_HORACOLETA, cotacao.getHoracol());
        values.put(COLUNA_OBSERVACAO, cotacao.getObscol());

        values.put(COLUNA_PLACATRACAO, cotacao.getCavalo());
        values.put(COLUNA_PLACACARRETA, cotacao.getCarreta());
        values.put(COLUNA_NOMECONDUTOR, cotacao.getCondutor());
        values.put(COLUNA_CPF, cotacao.getCpf());
        values.put(COLUNA_STATUS, cotacao.getStatus());


        dbCot.update(TABELA_COTACAO, values, COLUNA_CODIGOCOT + " = ?",
                new String[] { String.valueOf(cotacao.getCodigocot()) });
        }



    void atualizarProjecao(DatabaseHelperCot cotacao) {

        SQLiteDatabase dbCot = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        /*
        values.put(COLUNA_CIDADEOCOT, cotacao.getCidocot());
        values.put(COLUNA_CIDADEDCOT, cotacao.getCiddcot());
        values.put(COLUNA_VALORCARGA, cotacao.getVlcarga());
        values.put(COLUNA_PESOCOBRADO, cotacao.getPesoc());
        values.put(COLUNA_FRETEPESO, cotacao.getFpeso());
        values.put(COLUNA_PEDCOT, cotacao.getPedcot());
        values.put(COLUNA_GRISCOT, cotacao.getGriscot());
        values.put(COLUNA_ADVCOT, cotacao.getAdvcot());
        values.put(COLUNA_ICMSCOT, cotacao.getIcmscot());
        values.put(COLUNA_FRETEFINAL, cotacao.getFtotal());
        */
        values.put(COLUNA_NOMECLIENTE, cotacao.getNcliente());
        values.put(COLUNA_ENDERECOCOL, cotacao.getEnd());
        values.put(COLUNA_REFERENCIA, cotacao.getRef());
        values.put(COLUNA_QUANTIDADEVOL, cotacao.getQvol());
        values.put(COLUNA_DATACOLETA, cotacao.getDatacol());
        values.put(COLUNA_HORACOLETA, cotacao.getHoracol());
        values.put(COLUNA_OBSERVACAO, cotacao.getObscol());

        values.put(COLUNA_PLACATRACAO, cotacao.getCavalo());
        values.put(COLUNA_PLACACARRETA, cotacao.getCarreta());
        values.put(COLUNA_NOMECONDUTOR, cotacao.getCondutor());
        values.put(COLUNA_CPF, cotacao.getCpf());
        values.put(COLUNA_STATUS, cotacao.getStatus());


        dbCot.update(TABELA_COTACAO, values, COLUNA_CODIGOCOT + " = ?",
                new String[] { String.valueOf(cotacao.getCodigocot()) });
    }




        public List<DatabaseHelperCot> ListaTodasCotacoes() {
            List<DatabaseHelperCot> listaCotacao = new ArrayList<DatabaseHelperCot>();

        String query = "SELECT * FROM " + TABELA_COTACAO;

        SQLiteDatabase dbCot = this.getWritableDatabase();
        Cursor ccot = dbCot.rawQuery(query, null);

        if (ccot.moveToFirst()) {
            do {
                DatabaseHelperCot cotacao = new DatabaseHelperCot();
                cotacao.setCodigocot(Integer.parseInt(ccot.getString(0)));
                cotacao.setCidocot(ccot.getString(1));
                cotacao.setCiddcot(ccot.getString(2));
                cotacao.setVlcarga(Float.parseFloat(ccot.getString(3)));
                cotacao.setPesoc(Float.parseFloat(ccot.getString(4)));
                cotacao.setFpeso(Float.parseFloat(ccot.getString(5)));
                cotacao.setPedcot(Float.parseFloat(ccot.getString(6)));
                cotacao.setGriscot(Float.parseFloat(ccot.getString(7)));
                cotacao.setAdvcot(Float.parseFloat(ccot.getString(8)));
                cotacao.setIcmscot(Float.parseFloat(ccot.getString(9)));
                cotacao.setFtotal(Float.parseFloat(ccot.getString(10)));

                cotacao.setNcliente(ccot.getString(11));
                cotacao.setEnd(ccot.getString(12));
                cotacao.setRef(ccot.getString(13));
                cotacao.setQvol(Integer.parseInt(ccot.getString(14)));
                cotacao.setDatacol(ccot.getString(15));
                cotacao.setHoracol(ccot.getString(16));
                cotacao.setObscol(ccot.getString(17));
                cotacao.setCavalo(ccot.getString(18));
                cotacao.setCarreta(ccot.getString(19));
                cotacao.setCondutor(ccot.getString(20));
                cotacao.setCpf(ccot.getString(21));
                cotacao.setStatus(ccot.getString(22));

                listaCotacao.add(cotacao);
            } while (ccot.moveToNext());
        }

        return listaCotacao;
    }



    // VERIFICAR COM VALDINEI A LOGICA DO REGISTRO NO CURSOR
    // PARA VERIFICAR SE DA PARA COLOCAR UMA CONDICIONAL E VALIDAR O EQUAL NO CURSOR

    // VERIFICAR COM VALDINEI SOBRE O DATABASEHELPER2 para colocar o último campo o ICMS (não deixa)



    /*DatabaseHelper2 selecionarRota(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABELA_ROTAS, new String[] {COLUNA_CODIGO,
                        COLUNA_CIDADEO}, COLUNA_CODIGO + " = ?",
                new String[] {String.valueOf(codigo)}, null, null, null, null);

        //if(cursor != null) {
            cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            //if (txtCidadeOcot.getText().toString().equals(cursor.getString(1))) {

            if (CYTYO.equals(cursor.getString(1))) {

               TESTE = cursor.getInt(5);

            }}

           // cursor.moveToFirst();

        //}

        DatabaseHelper2 rotaCalc = new DatabaseHelper2(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4),
                cursor.getInt(5), cursor.getInt(6));

        return rotaCalc;
    }
*/
}
