package hr.veleri.data.dataobjects;

import javax.persistence.*;
import java.util.Date;

/**
 * User: iivanovic
 * Date: 04.10.2010.
 * Time: 13:42:39
 */
@Entity
@Table(name = "PRIJAVA")
public class Prijava extends DomainObject {

//    private static final long serialVersionUID = 1259377496669050427L;


    @Id
    @GeneratedValue
    private long priid;

    private long prirbr;

    private Date pridatum;

    private Date pridatumzap;

    @ManyToOne(targetEntity = KorisnikKlijent.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "KORISNIK_ID")
    private KorisnikKlijent prijavio;

    @ManyToOne(targetEntity = Aplikacija.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "APLIKACIJA_ID")
    private Aplikacija aplikacija;
    
    private String opis;

    private String napomena;

    public String getNapomena() {
        return napomena;
    }

    public Prijava() {
    }

    public Prijava(long prirbr, Aplikacija aplikacija, String napomena, String opis, Date pridatum, Date pridatumzap, KorisnikKlijent prijavio) {
        this.prirbr = prirbr;
        this.aplikacija = aplikacija;
        this.napomena = napomena;
        this.opis = opis;
        this.pridatum = pridatum;
        this.pridatumzap = pridatumzap;
        this.prijavio = prijavio;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getPridatum() {
        return pridatum;
    }

    public void setPridatum(Date pridatum) {
        this.pridatum = pridatum;
    }

    public Date getPridatumzap() {
        return pridatumzap;
    }

    public void setPridatumzap(Date pridatumzap) {
        this.pridatumzap = pridatumzap;
    }

    public long getPriid() {
        return priid;
    }

    public void setPriid(long priid) {
        this.priid = priid;
    }

    public long getPrirbr() {
        return prirbr;
    }

    public void setPrirbr(long prirbr) {
        this.prirbr = prirbr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prijava prijava = (Prijava) o;

        if (priid != prijava.priid) return false;
        if (prirbr != prijava.prirbr) return false;
        if (pridatum != null ? !pridatum.equals(prijava.pridatum) : prijava.pridatum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (priid ^ (priid >>> 32));
        result = 31 * result + (int) (prirbr ^ (prirbr >>> 32));
        result = 31 * result + (pridatum != null ? pridatum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Prijava{" +
                "napomena='" + napomena + '\'' +
                ", priid=" + priid +
                ", prirbr=" + prirbr +
                ", pridatum=" + pridatum +
                ", pridatumzap=" + pridatumzap +
                ", opis='" + opis + '\'' +
                '}';
    }

    public KorisnikKlijent getPrijavio() {
        return prijavio;
    }

    public Aplikacija getAplikacija() {
        return aplikacija;
    }
}
