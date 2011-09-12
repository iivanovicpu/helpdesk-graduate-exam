package hr.veleri.data.dao.interfaces;

import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.KorisnikKlijent;
import org.springframework.transaction.annotation.Transactional;

public interface KorisnikKlijentDao extends Dao<KorisnikKlijent> {

    @Transactional
    Korisnik getKorisnik(String username, String password);

    @Transactional
    KorisnikKlijent findByKorisnik(Korisnik loggedInUser);
}
