public class Produkte {
    private String tipi;
    private int sasia;
    private double cmimi;

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

    public Produkte(String tipi, int sasia, double cmimi) {
        this.tipi = tipi;
        this.sasia = sasia;
        this.cmimi = cmimi;
    }

    public double getTotalCmimi() {
        return this.cmimi * this.sasia;
    }

    @Override
    public String toString() {
        return "Produkte{" +
                "tipi='" + tipi + '\'' +
                ", sasia=" + sasia +
                ", cmimi=" + cmimi +
                '}';
    }
}