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
    private String id;

    private Date datum;

    @ManyToOne(targetEntity = Prijava.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PRIJAVA_ID")
    private Prijava prijava;

    @ManyToOne(targetEntity = KorisnikZaposlenik.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "KORISNIK_ID")
    private KorisnikZaposlenik zaposlenik;

    private String opis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
