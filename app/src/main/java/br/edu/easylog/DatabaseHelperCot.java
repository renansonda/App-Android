package br.edu.easylog;

import java.util.Date;

public class DatabaseHelperCot {

    int codigocot;
    String cidocot, ciddcot, status;
    Float vlcarga, pesoc, fpeso, pedcot, griscot, advcot, icmscot, ftotal;


    Integer qvol;
    String ncliente, end, ref, datacol, horacol, obscol;
    String cavalo, carreta, condutor, cpf;
    
    public DatabaseHelperCot(){

    }

    //update
    public  DatabaseHelperCot(int _codigocot, String _cidocot, String _ciddcot, Float _vlcarga, Float _pesoc, Float _fpeso,
                              Float _pedcot, Float _griscot, Float _adccot, Float _icmscot, Float _ftotal,
                              String _ncliente, String _end, String _ref, Integer _qvol, String _datacol, String _horacol,
                              String _obscol, String _cavalo, String _carreta, String _condutor, String _cpf, String _status) {

        this.codigocot = _codigocot;
        this.cidocot = _cidocot;
        this.ciddcot = _ciddcot;
        this.vlcarga = _vlcarga;
        this.pesoc = _pesoc;
        this.fpeso = _fpeso;
        this.pedcot = _pedcot;
        this.griscot = _griscot;
        this.advcot = _adccot;
        this.icmscot = _icmscot;
        this.ftotal = _ftotal;

        this.ncliente = _ncliente;
        this.end = _end;
        this.ref = _ref;
        this.qvol = _qvol;
        this.datacol = _datacol;
        this.horacol = _horacol;
        this.obscol = _obscol;
        this.cavalo = _cavalo;
        this.carreta = _carreta;
        this.condutor = _condutor;
        this.cpf = _cpf;
        this.status = _status;

    }
    //insert
    public  DatabaseHelperCot(String _cidocot, String _ciddcot, Float _vlcarga, Float _pesoc, Float _fpeso,
                              Float _pedcot, Float _griscot, Float _adccot, Float _icmscot, Float _ftotal,
                              String _ncliente, String _end, String _ref, Integer _qvol, String _datacol, String _horacol,
                              String _obscol, String _cavalo, String _carreta, String _condutor, String _cpf, String _status) {

        this.cidocot = _cidocot;
        this.ciddcot = _ciddcot;
        this.vlcarga = _vlcarga;
        this.pesoc = _pesoc;
        this.fpeso = _fpeso;
        this.pedcot = _pedcot;
        this.griscot = _griscot;
        this.advcot = _adccot;
        this.icmscot = _icmscot;
        this.ftotal = _ftotal;

        this.ncliente = _ncliente;
        this.end = _end;
        this.ref = _ref;
        this.qvol = _qvol;
        this.datacol = _datacol;
        this.horacol = _horacol;
        this.obscol = _obscol;
        this.cavalo = _cavalo;
        this.carreta = _carreta;
        this.condutor = _condutor;
        this.cpf = _cpf;
        this.status = _status;
    }


    public  DatabaseHelperCot(int _codigocot, String _cidocot, String _status) {

        this.codigocot = _codigocot;
        this.cidocot = _cidocot;
        this.status = _status;
    }

    public  DatabaseHelperCot(int _codigocot, String _ncliente, String _end, String _ref, int _qvol,
                              String _datacol, String _horacol, String _obscol, String _cavalo, String _carreta,
                              String _condutor, String _cpf, String _status) {

        this.codigocot = _codigocot;
        this.ncliente = _ncliente;
        this.end = _end;
        this.ref = _ref;
        this.qvol = _qvol;
        this.datacol = _datacol;
        this.horacol = _horacol;
        this.obscol = _obscol;
        this.cavalo = _cavalo;
        this.carreta = _carreta;
        this.condutor = _condutor;
        this.cpf = _cpf;
        this.status = _status;

    }


    public  DatabaseHelperCot(int _codigocot, String _status) {

        this.codigocot = _codigocot;
        this.status = _status;
    }


    //=================================================================================================


    public int getCodigocot() {
        return codigocot;
    }

    public void setCodigocot(int codigocot) {
        this.codigocot = codigocot;
    }

    public String getCidocot() {
        return cidocot;
    }

    public void setCidocot(String cidocot) {
        this.cidocot = cidocot;
    }

    public String getCiddcot() {
        return ciddcot;
    }

    public void setCiddcot(String ciddcot) {
        this.ciddcot = ciddcot;
    }

    public Float getVlcarga() {
        return vlcarga;
    }

    public void setVlcarga(Float vlcarga) {
        this.vlcarga = vlcarga;
    }

    public Float getPesoc() {
        return pesoc;
    }

    public void setPesoc(Float pesoc) {
        this.pesoc = pesoc;
    }

    public Float getFpeso() {
        return fpeso;
    }

    public void setFpeso(Float fpeso) {
        this.fpeso = fpeso;
    }

    public Float getPedcot() {
        return pedcot;
    }

    public void setPedcot(Float pedcot) {
        this.pedcot = pedcot;
    }

    public Float getGriscot() {
        return griscot;
    }

    public void setGriscot(Float griscot) {
        this.griscot = griscot;
    }

    public Float getAdvcot() {
        return advcot;
    }

    public void setAdvcot(Float advcot) {
        this.advcot = advcot;
    }

    public Float getIcmscot() {
        return icmscot;
    }

    public void setIcmscot(Float icmscot) {
        this.icmscot = icmscot;
    }

    public Float getFtotal() {
        return ftotal;
    }

    public void setFtotal(Float ftotal) {
        this.ftotal = ftotal;
    }


    public Integer getQvol() {
        return qvol;
    }

    public void setQvol(Integer qvol) {
        this.qvol = qvol;
    }

    public String getNcliente() {
        return ncliente;
    }

    public void setNcliente(String ncliente) {
        this.ncliente = ncliente;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDatacol() {
        return datacol;
    }

    public void setDatacol(String datacol) {
        this.datacol = datacol;
    }

    public String getHoracol() {
        return horacol;
    }

    public void setHoracol(String horacol) {
        this.horacol = horacol;
    }

    public String getObscol() {
        return obscol;
    }

    public void setObscol(String obscol) {
        this.obscol = obscol;
    }


    public String getCavalo() {
        return cavalo;
    }

    public void setCavalo(String cavalo) {
        this.cavalo = cavalo;
    }

    public String getCarreta() {
        return carreta;
    }

    public void setCarreta(String carreta) {
        this.carreta = carreta;
    }

    public String getCondutor() {
        return condutor;
    }

    public void setCondutor(String condutor) {
        this.condutor = condutor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
