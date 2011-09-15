package hr.veleri.data.dao.interfaces;

import hr.veleri.data.dataobjects.Klijent;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KlijentDao extends Dao<Klijent> {

    @Transactional
    List selectEntries(int first, int count, SortParam sortParam);

    @Transactional
    Klijent findBySifra(String sifra);
}
