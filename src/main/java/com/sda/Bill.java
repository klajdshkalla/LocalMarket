package com.sda;

import java.time.LocalDate;

public class Bill {
    private double cmimi;
    private LocalDate data;
    private Buyer buyer;

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

    public Buyer getbleresi() {
        return buyer;
    }

    public void setbleresi(Buyer buyer) {
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

    public Bill(double cmimi, LocalDate data, Buyer buyer) {
        this.cmimi = cmimi;
        this.data = data;
        this.buyer = buyer;
    }
}