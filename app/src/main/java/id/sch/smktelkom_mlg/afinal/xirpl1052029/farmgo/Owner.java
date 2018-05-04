package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

/**
 * Created by ASUS on 5/1/2018.
 */

import java.io.Serializable;

public class Owner implements Serializable {

    private String uid;
    private String name;
    private String telp;
    private String username;
    private String password;
    private String namaperusahaan;
    private String alamat;
    private String kodemember;
    public Owner() {

    }

    public Owner(String uid, String name, String telp, String username, String password, String namaperusahaan, String alamat, String kodemember) {
        this.uid = uid;
        this.name = name;
        this.telp = telp;
        this.username = username;
        this.password = password;
        this.namaperusahaan = namaperusahaan;
        this.alamat = alamat;
        this.kodemember = kodemember;
    }

    public void clear() {
        this.uid = "";
        this.name = "";
        this.telp = "";
        this.namaperusahaan = "";
        this.username = "";
        this.password = "";
        this.alamat = "";
        this.kodemember = "";
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getTelp() {
        return telp;
    }

    public String getUsername() {
        return username;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getPassword() {
        return password;
    }

    public String getNamaperusahaan() {
        return namaperusahaan;
    }

    public String kodemember() {
        return kodemember;
    }
}



