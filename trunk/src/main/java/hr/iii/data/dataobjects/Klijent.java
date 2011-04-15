package hr.iii.data.dataobjects;

import javax.persistence.*;

/**
 * User: iivanovic
 * Date: 12.10.2010.
 * Time: 13:24:58
 */
@Entity
@Table(name = "KLIJENT")
public class Klijent extends DomainObject {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "SIFRA")
    private String sifra;

    @Column(name = "NAZIV")
    private String naziv;


    public Klijent() {
    }

    public Klijent(String sifra, String naziv ) {
        this.sifra = sifra;
        this.naziv = naziv;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Klijent klijent = (Klijent) o;

        if (!sifra.equals(klijent.sifra)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sifra.hashCode();
    }
}
