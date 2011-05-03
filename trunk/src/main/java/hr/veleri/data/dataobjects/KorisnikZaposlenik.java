package hr.veleri.data.dataobjects;

import javax.persistence.*;

/**
 * User: iivanovic
 * Date: 12.10.2010.
 * Time: 13:24:58
 */
@Entity
@Table(name = "KORISNIK_ZAPOSLENIK")
public class KorisnikZaposlenik extends DomainObject {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @ManyToOne(targetEntity = Aplikacija.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "APLIKACIJA_ID")
    private Aplikacija aplikacija;

    @ManyToOne(targetEntity = Korisnik.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "KORISNIK_ID")
    private Korisnik korisnik;

    public KorisnikZaposlenik() {
    }

    public KorisnikZaposlenik(Aplikacija aplikacija, Korisnik korisnik) {
        this.aplikacija = aplikacija;
        this.korisnik = korisnik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aplikacija getAplikacija() {
        return aplikacija;
    }

    public void setAplikacija(Aplikacija aplikacija) {
        this.aplikacija = aplikacija;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KorisnikZaposlenik that = (KorisnikZaposlenik) o;

        if (!aplikacija.equals(that.aplikacija)) return false;
        if (!korisnik.equals(that.korisnik)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aplikacija.hashCode();
        result = 31 * result + korisnik.hashCode();
        return result;
    }
}
