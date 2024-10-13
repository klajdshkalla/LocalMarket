import java.time.LocalDate;

public class Fatura {
    private double cmimi;
    private LocalDate data;
    private Bleresi bleresi;

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

    public Bleresi getbleresi() {
        return bleresi;
    }

    public void setbleresi(Bleresi bleresi) {
        this.bleresi = bleresi;
    }

    @Override
    public String toString() {
        return "Fatura{" +
                "cmimi=" + cmimi +
                ", data=" + data +
                ", bleresi=" + bleresi +
                '}';
    }

    public Fatura(double cmimi, LocalDate data, Bleresi bleresi) {
        this.cmimi = cmimi;
        this.data = data;
        this.bleresi = bleresi;
    }
}