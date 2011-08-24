package hr.veleri.data.dataobjects;

import javax.persistence.*;

/**
 * User: iivanovic
 * Date: 12.10.2010.
 * Time: 13:24:58
 */
@Entity
@Table(name = "APLIKACIJA")
public class Aplikacija extends DomainObject{

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "SIFRA")
    private String sifra;

    @Column(name = "NAZIV")
    private String naziv;

    public Aplikacija() {
    }

    public Aplikacija(String naziv, String sifra) {
        this.naziv = naziv;
        this.sifra = sifra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aplikacija klijent = (Aplikacija) o;

        if (!sifra.equals(klijent.sifra)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sifra.hashCode();
    }

    @Override
    public String toString() {
        return sifra + " (" + naziv + ")";
    }
}
