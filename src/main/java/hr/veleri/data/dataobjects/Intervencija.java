package hr.veleri.data.dataobjects;

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

    public Intervencija() {
    }

    public Intervencija(Date datum, String opis, Prijava prijava, KorisnikZaposlenik zaposlenik) {
        this.datum = datum;
        this.opis = opis;
        this.prijava = prijava;
        this.zaposlenik = zaposlenik;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
