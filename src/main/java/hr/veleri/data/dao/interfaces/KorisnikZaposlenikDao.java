package hr.veleri.data.dao.interfaces;

import hr.veleri.data.dataobjects.Aplikacija;
import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.KorisnikKlijent;
import hr.veleri.data.dataobjects.KorisnikZaposlenik;
import org.springframework.transaction.annotation.Transactional;

public interface KorisnikZaposlenikDao extends Dao<KorisnikZaposlenik> {
    KorisnikZaposlenik findByKorisnikAndAplikacija(Korisnik korisnik, Aplikacija aplikacija);
}
