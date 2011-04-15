package hr.veleri.data.dao.interfaces;

import hr.veleri.data.dataobjects.Klijent;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import java.util.List;

public interface KlijentiDao extends Dao<Klijent> {

    List selectEntries(int first, int count, SortParam sortParam);
}
