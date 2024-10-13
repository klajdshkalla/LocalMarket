public class Bleresi {
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

    public Bleresi(String emri, String mbiemri) {
        this.emri = emri;
        this.mbiemri = mbiemri;
    }
}