package hr.veleri.data.dataobjects;

import javax.persistence.*;

/**
 * User: iivanovic
 * Date: 12.10.2010.
 * Time: 13:24:58
 */
@Entity
@Table(name = "KORISNIK_KLIJENT")
public class KorisnikKlijent  extends DomainObject {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @ManyToOne(targetEntity = Klijent.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "KLIJENT_ID")
    private Klijent klijent;

    @ManyToOne(targetEntity = Korisnik.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "KORISNIK_ID")
    private Korisnik korisnik;

    public KorisnikKlijent() {
    }

    public KorisnikKlijent(Klijent klijent, Korisnik korisnik) {
        this.klijent = klijent;
        this.korisnik = korisnik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
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

        KorisnikKlijent that = (KorisnikKlijent) o;

        if (!klijent.equals(that.klijent)) return false;
        if (!korisnik.equals(that.korisnik)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = klijent.hashCode();
        result = 31 * result + korisnik.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return korisnik.getPrezime() + " "+ korisnik.getIme() + " (" + klijent.getNaziv() + ")";
    }
}
