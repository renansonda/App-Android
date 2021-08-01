package br.edu.easylog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class bancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
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

    public bancoDados(@Nullable Context context) {
        super(context, BANCO_ROTAS, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_ROTAS + "("+ COLUNA_CODIGO + " INTEGER PRIMARY KEY, " + COLUNA_CIDADEO + " TEXT, " + COLUNA_CIDADED + " TEXT, " + COLUNA_FRETEP + " FLOAT, " + COLUNA_PED + " FLOAT, " + COLUNA_GRIS + " FLOAT, " + COLUNA_ADV + " FLOAT, " + COLUNA_ICMS + " FLOAT)";

        db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
    }

    // CRUD

    void addRota(DatabaseHelper2 rota){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_CIDADEO, rota.getCidadeO());
        values.put(COLUNA_CIDADED, rota.getCidadeD());
        values.put(COLUNA_FRETEP, rota.getFreteP());
        values.put(COLUNA_PED, rota.getPed());
        values.put(COLUNA_GRIS, rota.getGris());
        values.put(COLUNA_ADV, rota.getAdv());
        values.put(COLUNA_ICMS, rota.getIcms());

        db.insert(TABELA_ROTAS, null, values);
        db.close();
    }

    void apagarRota(DatabaseHelper2 rota){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_ROTAS, COLUNA_CODIGO + " = ?", new String[] { String.valueOf(rota.getCodigo())});

        db.close();
    }

    DatabaseHelper2 selecionarRota(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_ROTAS, new String[] {COLUNA_CODIGO,
        COLUNA_CIDADEO, COLUNA_CIDADED, COLUNA_FRETEP, COLUNA_PED, COLUNA_GRIS, COLUNA_ADV, COLUNA_ICMS}, COLUNA_CODIGO + " = ?",
                new String[] {String.valueOf(codigo)}, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        DatabaseHelper2 rota = new DatabaseHelper2(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getFloat(4),
                cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(7));

        return rota;
    }

    //================ ESSA AQUI =======================


    /*DatabaseHelper2 selecionarRotaCalc(int codigoCalc){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_ROTAS, new String[] {COLUNA_CODIGO,
                        COLUNA_CIDADEO, COLUNA_CIDADED, COLUNA_FRETEP, COLUNA_PED, COLUNA_GRIS, COLUNA_ADV, COLUNA_ICMS}, COLUNA_CODIGO + " = ?",
                new String[] {String.valueOf(codigoCalc)}, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                //if (txtCidadeOcot.getText().toString().equals(cursor.getString(1))) {

                if (   .equals(cursor.getString(1))) {

                    = cursor.getInt(5);

                }

        DatabaseHelper2 rota = new DatabaseHelper2(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4),
                cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));

        return rota;
    }*/


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
    }*/

    // =================================================







    void atualizarRota(DatabaseHelper2 rota){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_CIDADEO, rota.getCidadeO());
        values.put(COLUNA_CIDADED, rota.getCidadeD());
        values.put(COLUNA_FRETEP, rota.getFreteP());
        values.put(COLUNA_PED, rota.getPed());
        values.put(COLUNA_GRIS, rota.getGris());
        values.put(COLUNA_ADV, rota.getAdv());
        values.put(COLUNA_ICMS, rota.getIcms());

        db.update(TABELA_ROTAS, values, COLUNA_CODIGO + " = ?",
                new String[] { String.valueOf(rota.getCodigo()) });
    }

    public List<DatabaseHelper2> listaTodasRotas() {
        List<DatabaseHelper2> listaRotas = new ArrayList<DatabaseHelper2>();

        String query = "SELECT * FROM " + TABELA_ROTAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()) {
            do {
                DatabaseHelper2 rota = new DatabaseHelper2();
                rota.setCodigo(Integer.parseInt(c.getString(0)));
                rota.setCidadeO(c.getString(1));
                rota.setCidadeD(c.getString(2));
                rota.setFreteP(Float.parseFloat(c.getString(3)));
                rota.setPed(Float.parseFloat(c.getString(4)));
                rota.setGris(Float.parseFloat(c.getString(5)));
                rota.setAdv(Float.parseFloat(c.getString(6)));
                rota.setIcms(Float.parseFloat(c.getString(7)));

                listaRotas.add(rota);
            } while (c.moveToNext());
        }

        return listaRotas;
    }


    //   TESTE   // ==============================================================
    // Verificar com Valdinei

    /*public List<DatabaseHelper2> ListarRotasCalculo() {
        List<DatabaseHelper2> listaRotasCalc = new ArrayList<DatabaseHelper2>();

        String query = "SELECT GRIS FROM " + TABELA_ROTAS + " WHERE CODIGO = 1";

        SQLiteDatabase dbCalc = this.getWritableDatabase();
        Cursor cCalc = dbCalc.rawQuery(query, null);

        if (cCalc.moveToFirst()) {
            do {
                DatabaseHelper2 rota = new DatabaseHelper2();
                //rota.setCodigo(Integer.parseInt(cCalc.getString(0)));
                //rota.setCidadeO(cCalc.getString(1));
                //rota.setCidadeD(cCalc.getString(2));
                //rota.setFreteP(Integer.parseInt(cCalc.getString(3)));
                //rota.setPed(Integer.parseInt(cCalc.getString(4)));
                rota.setGrisCalc(Integer.parseInt(cCalc.getString(5)));
                //rota.setAdv(Integer.parseInt(cCalc.getString(6)));
                //rota.setIcms(Integer.parseInt(cCalc.getString(7)));

                listaRotasCalc.add((DatabaseHelper2) listaRotasCalc);
            } while (cCalc.moveToNext());
        }

        return listaRotasCalc;
    }*/

    // =============================================




}


