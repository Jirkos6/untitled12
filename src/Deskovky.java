import java.math.BigDecimal;

public  class Deskovky {

    private String jmeno;
    private BigDecimal pocetHodu;
    private int pocetKopu;

    public Deskovky(String jmeno, BigDecimal pocetHodu, int pocetKopu) {
        this.jmeno = jmeno;
        this.pocetHodu = pocetHodu;
        this.pocetKopu = pocetKopu;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public BigDecimal getPocetHodu() {
        return pocetHodu;
    }

    public void setPocetHodu(BigDecimal pocetHodu) {
        this.pocetHodu = pocetHodu;
    }

    public int getPocetKopu() {
        return pocetKopu;
    }

    @Override
    public String toString() {
        return "Deskovky{" +
                "jmeno='" + jmeno + '\'' +
                ", pocetHodu=" + pocetHodu +
                ", pocetKopu=" + pocetKopu +
                '}';
    }

    public void setPocetKopu(int pocetKopu) {
        this.pocetKopu = pocetKopu;


    }
}