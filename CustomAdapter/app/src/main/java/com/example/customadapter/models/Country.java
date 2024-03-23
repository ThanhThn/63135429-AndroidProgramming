package com.example.customadapter.models;

public class Country {
    String tenQG;
    Integer soLuongDan;
    Integer tenFileAnhLaCo;

    public Country(String tenQG, Integer soLuongDan, Integer tenFileAnhLaCo) {
        this.tenQG = tenQG;
        this.soLuongDan = soLuongDan;
        this.tenFileAnhLaCo = tenFileAnhLaCo;
    }

    public String getTenQG() {
        return tenQG;
    }

    public void setTenQG(String tenQG) {
        this.tenQG = tenQG;
    }

    public Integer getSoLuongDan() {
        return soLuongDan;
    }

    public void setSoLuongDan(Integer soLuongDan) {
        this.soLuongDan = soLuongDan;
    }

    public Integer getTenFileAnhLaCo() {
        return tenFileAnhLaCo;
    }

    public void setTenFileAnhLaCo(Integer tenFileAnhLaCo) {
        this.tenFileAnhLaCo = tenFileAnhLaCo;
    }
}
