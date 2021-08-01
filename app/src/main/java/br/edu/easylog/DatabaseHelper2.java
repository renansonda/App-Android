package br.edu.easylog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper2 {

   int codigo;
   String cidadeO;
   String cidadeD;
   Float vlrCarga;
   Float pesoCob;
   Float freteP;
   Float ped;
   Float gris;
   Float adv;
   Float icms;
   Float freteFinal;
   String nomeCli;
   String endCli;
   String refCli;
   Integer qtdVol;
   String dataCol;
   String HoraCol;
   String obsCol;
   String placaCav;
   String placaCar;
   String nomeCond;
   String cpfCond;
   String statusSol;


   public DatabaseHelper2() {
   }
    //update
   public  DatabaseHelper2(int _codigo, String _cidadeO, String _cidadeD, Float _freteP, Float _ped,
                           Float _gris, Float _adv, Float _icms) {
        this.codigo = _codigo;
        this.cidadeO = _cidadeO;
        this.cidadeD = _cidadeD;
        this.freteP = _freteP;
        this.ped = _ped;
        this.gris = _gris;
        this.adv = _adv;
        this.icms = _icms;
   }
   //insert
    public  DatabaseHelper2(String _cidadeO, String _cidadeD, Float _freteP, Float _ped, Float _gris, Float _adv, Float _icms) {
        this.cidadeO = _cidadeO;
        this.cidadeD = _cidadeD;
        this.freteP = _freteP;
        this.ped = _ped;
        this.gris = _gris;
        this.adv = _adv;
        this.icms = _icms;
    }

    //CALC
    public  DatabaseHelper2(int _codigo, String _cidadeO, String _cidadeD, Float _freteP, Float _ped,
                            Float _gris, Float _adv) {
        this.cidadeO = _cidadeO;
        this.cidadeD = _cidadeD;
        this.freteP = _freteP;
        this.ped = _ped;
        this.gris = _gris;
        this.adv = _adv;
    }



    //=================================================================================================

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCidadeO() {
        return cidadeO;
    }

    public void setCidadeO(String cidadeO) {
        this.cidadeO = cidadeO;
    }

    public String getCidadeD() {
        return cidadeD;
    }

    public void setCidadeD(String cidadeD) {
        this.cidadeD = cidadeD;
    }

    public Float getFreteP() {
        return freteP;
    }

    public void setFreteP(Float freteP) {
        this.freteP = freteP;
    }

    public Float getPed() {
        return ped;
    }

    public void setPed(Float ped) {
        this.ped = ped;
    }

    public Float getGris() {
        return gris;
    }

    public void setGris(Float gris) {
        this.gris = gris;
    }

    public Float getAdv() {
        return adv;
    }

    public void setAdv(Float adv) {
        this.adv = adv;
    }

    public Float getIcms() {
        return icms;
    }

    public void setIcms(Float icms) {
        this.icms = icms;
    }
}
