package hr.veleri.data.dao.interfaces;

import hr.veleri.data.dataobjects.Aplikacija;
import hr.veleri.data.dataobjects.Korisnik;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AplikacijaDao extends Dao<Aplikacija> {
    List selectEntries(int first, int count, SortParam sortParam);
}
