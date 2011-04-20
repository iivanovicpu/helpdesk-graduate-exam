package hr.veleri.data.dao.interfaces;

import hr.veleri.data.dataobjects.Korisnik;
import org.springframework.transaction.annotation.Transactional;

public interface KorisnikDao extends Dao<Korisnik> {

    @Transactional
    public Korisnik getKorisnik(String username, String password);
}
