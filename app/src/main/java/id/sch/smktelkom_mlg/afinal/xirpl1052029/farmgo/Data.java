package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import java.io.Serializable;

/**
 * Created by haniivn on 4/29/2018.
 */

public class Data implements Serializable {

    String uid;
    String etanggal;
    String umur;
    String jayam;
    String jmati;
    String jtelur;
    String btelur;
    String bmakan;


    public Data() {

    }


    public Data(String uid, String etanggal, String umur, String jayam, String jmati, String jtelur, String btelur, String bmakan) {
        this.uid = uid;
        this.etanggal = etanggal;
        this.umur = umur;
        this.jayam = jayam;
        this.jmati = jmati;
        this.jtelur = jtelur;
        this.btelur = btelur;
        this.bmakan = bmakan;
    }


    public void clear() {
        this.uid = "";
        this.etanggal = "";
        this.umur = "";
        this.jayam = "";
        this.jmati = "";
        this.jtelur = "";
        this.btelur = "";
        this.bmakan = "";
    }



}
