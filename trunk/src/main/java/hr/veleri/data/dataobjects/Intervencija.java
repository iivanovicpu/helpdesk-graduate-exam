package hr.veleri.data.dataobjects;

import hr.veleri.util.UtilitiesDate;

import javax.persistence.*;
import java.util.Date;

/**
 * User: iivanovic
 * Date: 09.08.11.
 * Time: 18:39
 */
@Entity
@Table(name = "INTERVENCIJA")
public class Intervencija  extends DomainObject{

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    private Date datum;

    @ManyToOne(targetEntity = Prijava.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRIJAVA_ID")
    private Prijava prijava;

    @ManyToOne(targetEntity = KorisnikZaposlenik.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "KORISNIK_ID")
    private KorisnikZaposlenik zaposlenik;

    private String opis;

    private int minutaTrajanja;

    public Intervencija() {
    }

    public Intervencija(Date datum, String opis, Prijava prijava, KorisnikZaposlenik zaposlenik, int trajanje) {
        this.datum = datum;
        this.opis = opis;
        this.prijava = prijava;
        this.zaposlenik = zaposlenik;
        this.minutaTrajanja = trajanje;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public String getDatumFormatted() {
        return UtilitiesDate.formatDateTime(datum);
    }

    public KorisnikZaposlenik getZaposlenik() {
        return zaposlenik;
    }

    public Prijava getPrijava() {
        return prijava;
    }

    public String getOpis() {
        return opis;
    }

    public int getMinutaTrajanja() {
        return minutaTrajanja;
    }

    public void setPrijava(Prijava prijava) {
        this.prijava = prijava;
    }

    public void setZaposlenik(KorisnikZaposlenik korisnikZaposlenik) {
        this.zaposlenik = korisnikZaposlenik;
    }
}
