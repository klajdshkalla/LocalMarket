package com.sda;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double cmimi;
    private LocalDate data;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    // Add default constructor required by Hibernate
    public Bill() {}

    public Bill(double cmimi, LocalDate data, Buyer buyer) {
        this.cmimi = cmimi;
        this.data = data;
        this.buyer = buyer;
    }

    public Long getId() {
        return id;
    }

    public double getCmimi() {
        return cmimi;
    }

    public void setCmimi(double cmimi) {
        this.cmimi = cmimi;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "Fatura{" +
                "cmimi=" + cmimi +
                ", data=" + data +
                ", bleresi=" + buyer +
                '}';
    }
}