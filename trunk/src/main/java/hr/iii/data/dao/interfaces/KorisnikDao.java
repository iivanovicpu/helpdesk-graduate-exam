package hr.iii.data.dao.interfaces;

import hr.iii.data.dataobjects.Korisnik;
import org.springframework.transaction.annotation.Transactional;

public interface KorisnikDao extends Dao<Korisnik> {

    @Transactional
    Korisnik getRadnik(String username, String password);
}
