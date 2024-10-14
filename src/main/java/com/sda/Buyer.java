package com.sda;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String emri;
    private String mbiemri;

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public Buyer(String emri, String mbiemri) {
        this.emri = emri;
        this.mbiemri = mbiemri;
    }
}