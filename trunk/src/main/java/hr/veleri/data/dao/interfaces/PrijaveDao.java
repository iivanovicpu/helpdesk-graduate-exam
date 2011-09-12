package hr.veleri.data.dao.interfaces;

import hr.veleri.data.dataobjects.Prijava;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PrijaveDao extends Dao<Prijava> {

    List selectEntries(int first, int count, SortParam sortParam);

    Prijava findById(long prijavaId);

    @Transactional
    long findNextRbr();
}
