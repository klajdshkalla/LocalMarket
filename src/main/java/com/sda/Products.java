package com.sda;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipi;

    @Column(nullable = false)
    private int sasia;

    @Column(nullable = false)
    private double cmimi;

    public Products() {
    }

    public Products(String tipi, int sasia, double cmimi) {
        this.tipi = tipi;
        this.sasia = sasia;
        this.cmimi = cmimi;
    }

    public Long getId() {
        return id;
    }

    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi = tipi;
    }

    public int getSasia() {
        return sasia;
    }

    public void setSasia(int sasia) {
        this.sasia = sasia;
    }

    public double getCmimi() {
        return cmimi;
    }

    public void setCmimi(double cmimi) {
        this.cmimi = cmimi;
    }

    public double getTotalCmimi() {
        return this.cmimi * this.sasia;
    }

    @Override
    public String toString() {
        return tipi + " - Sasia: " + sasia + " - Cmimi: " + cmimi;
    }
}