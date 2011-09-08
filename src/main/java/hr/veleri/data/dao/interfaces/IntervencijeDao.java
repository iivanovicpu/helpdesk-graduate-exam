package hr.veleri.data.dao.interfaces;

import hr.veleri.data.dataobjects.Intervencija;
import hr.veleri.data.dataobjects.Klijent;
import hr.veleri.data.dataobjects.Prijava;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IntervencijeDao extends Dao<Intervencija> {

    List<Intervencija> selectEntries(int first, int count, SortParam sortParam, Prijava prijava);

    @Transactional
    List<Intervencija> findAllByPrijava(Prijava prijava);

    @Transactional
    int countAllByPrijava(Prijava prijava);

    @Transactional
    Intervencija findById(long intid);
}
