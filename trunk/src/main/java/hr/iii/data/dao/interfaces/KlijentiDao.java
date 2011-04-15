package hr.iii.data.dao.interfaces;

import hr.iii.data.dataobjects.Klijent;
import hr.iii.data.dataobjects.Prijava;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import java.util.List;

public interface KlijentiDao extends Dao<Klijent> {

    List selectEntries(int first, int count, SortParam sortParam);
}
