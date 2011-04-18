package hr.veleri.data.dao.interfaces;

import hr.veleri.data.dataobjects.Korisnik;
import org.springframework.transaction.annotation.Transactional;

public interface KorisniciDao extends Dao<Korisnik> {

    @Transactional
    Korisnik getRadnik(String username, String password);
}
