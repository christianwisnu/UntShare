package model;

/**
 * Created by Christ on 4/15/2016.
 */
public class ColJenisKain {
    private String kode;
    private String name;

    public ColJenisKain(){}

    public ColJenisKain(String kode, String name){
        this.kode=kode;
        this.name=name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
