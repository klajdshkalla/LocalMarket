package com.sda;

import jakarta.persistence.*;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String emri;

    @Column(nullable = false)
    private String mbiemri;

    public Buyer() {}

    public Buyer(String emri, String mbiemri) {
        this.emri = emri;
        this.mbiemri = mbiemri;
    }

    public Long getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return emri + " " + mbiemri;
    }
}