package hr.veleri.data.dataobjects;

import hr.veleri.util.Utilities;
import org.hibernate.usertype.UserType;

import javax.persistence.*;

/**
 * User: iivanovic
 * Date: 04.10.2010.
 * Time: 13:42:39
 */
@Entity
@Table(name = "KORISNIK")
public class Korisnik  extends DomainObject{

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "IME")
    private String ime;

    @Column(name = "PREZIME")
    private String prezime;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LOZINKA")
    private String lozinka;

    private TipKorisnika tipKorisnika;

    public Korisnik() {
    }

    public Korisnik(String ime, String prezime, String email, String lozinka) {
        this.ime = ime;
        this.lozinka = lozinka;
        this.prezime = prezime;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public boolean authenticate(String username, String password) {
        return this.email.equals(username) && this.lozinka.equals(Utilities.getMD5(password));
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return lozinka;
    }

    public void setPassword(String password) {
        this.lozinka = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Korisnik korisnik = (Korisnik) o;

        if (!email.equals(korisnik.email)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    public TipKorisnika getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(TipKorisnika tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }
}
