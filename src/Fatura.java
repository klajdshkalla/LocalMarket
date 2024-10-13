import java.time.LocalDate;

public class Fatura {
    private double cmimi;
    private LocalDate data;
    private Blersi blersi;


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

    public Blersi getBlersi() {
        return blersi;
    }

    public void setBlersi(Blersi blersi) {
        this.blersi = blersi;

    }

    @Override
    public String toString() {
        return "Fatura{" +
                "cmimi=" + cmimi +
                ", data=" + data +
                ", blersi=" + blersi +
                '}';
    }

    public Fatura(double cmimi, LocalDate data, Blersi blersi) {
        this.cmimi = cmimi;
        this.data = data;
        this.blersi = blersi;

    }
}
