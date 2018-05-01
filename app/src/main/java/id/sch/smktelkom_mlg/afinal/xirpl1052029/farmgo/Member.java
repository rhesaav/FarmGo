package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import java.io.Serializable;

/**
 * Created by Xratch on 5/1/2018.
 */

public class Member implements Serializable {

    private String uid;
    private String nama;
    private String no_telp;
    private String kode_perusahaan;
    private String user_member;
    private String pass_member;

    public Member() {

    }

    public Member(String uid, String nama, String no_telp, String kode_perusahaan, String user_member, String pass_member) {
        this.uid = uid;
        this.nama = nama;
        this.no_telp = no_telp;
        this.kode_perusahaan = kode_perusahaan;
        this.user_member = user_member;
        this.pass_member = pass_member;
    }

    public void clear() {
        this.uid = "";
        this.nama = "";
        this.no_telp = "";
        this.kode_perusahaan = "";
        this.user_member = "";
        this.pass_member = "";
    }

    public String getUid() {
        return uid;
    }

    public String getNama() {
        return nama;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public String getKode_perusahaan() {
        return kode_perusahaan;
    }

    public String getUsername() {
        return user_member;
    }

    public String getPassword() {
        return pass_member;
    }
}
