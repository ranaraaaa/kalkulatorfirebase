package com.example.kalkulatorjelek;

public class Kalkulator {
    private String angka1;
    private String angka2;
    private String hasil;
    private String method;
    private String key;

    public Kalkulator() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Kalkulator(String angka1, String angka2, String hasil, String method) {
        this.angka1 = angka1;
        this.angka2 = angka2;
        this.hasil = hasil;
        this.method = method;
    }

    public String getAngka1() {
        return angka1;
    }

    public void setAngka1(String angka1) {
        this.angka1 = angka1;
    }

    public String getAngka2() {
        return angka2;
    }

    public void setAngka2(String angka2) {
        this.angka2 = angka2;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
