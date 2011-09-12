package hr.veleri.data.dao.interfaces;

import hr.veleri.data.dataobjects.Korisnik;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KorisnikDao extends Dao<Korisnik> {

    @Transactional
    public Korisnik getKorisnik(String username, String password);

    @Transactional
    boolean isKorisnikKlijent(Korisnik korisnik);

    @Transactional
    boolean isKorisnikZaposlenik(Korisnik korisnik);

    @Transactional
    List selectEntries(int first, int count, SortParam sortParam);

}
