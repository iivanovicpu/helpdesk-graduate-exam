package hr.veleri.pages;

import hr.veleri.data.dao.interfaces.KlijentDao;
import hr.veleri.data.dataobjects.Klijent;
import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Iterator;

/**
 * User: iivanovic
 * Date: 12.10.2010.
 * Time: 13:52:41
 */
public class KlijentiPage extends AuthenticatedPage {
    @SpringBean
    private KlijentDao klijentDao;

    public KlijentiPage(final PageParameters pp) {
        wmc = new WebMarkupContainer("listKlijentContainer");

        IColumn[] columns = {
                new PropertyColumn(new Model("Šifra"), "sifra", "sifra"),
                new PropertyColumn(new Model("Naziv"), "naziv", "naziv"),
        };
        SortableDataProvider provider = new SortableDataProvider() {
            public int size() {
                return klijentDao.countAll();
            }

            public IModel model(Object object) {
                Klijent entry = (Klijent) object;
                return new Model(entry);
            }

            public Iterator iterator(int first, int count) {
                SortParam sortParam = getSort();
                return klijentDao.selectEntries(first, count, sortParam).iterator();
            }
        };
//        DefaultDataTable dataTable = new DefaultDataTable("entries", columns, provider.get(), 3);
        /* za custom tablicu ne može se koristiti DefaultDataTable klasa */
        DataTable dataTable = new DataTable("entries", columns, provider, 15) {
            protected Item newRowItem(String id, int index, IModel model) {
                return new OddEvenItem(id, index, model);
            }
        };
        dataTable.addTopToolbar(new HeadersToolbar(dataTable, provider));
        dataTable.addBottomToolbar(new NavigationToolbar(dataTable) {
        });

        /* ajax paginacija  -  */
//        dataTable.setOutputMarkupId(true);
//        AjaxNavigationToolbar navigationToolbar = new AjaxNavigationToolbar(dataTable);
//        dataTable.addBottomToolbar(navigationToolbar);

        wmc.add(dataTable);
        init(KlijentiPage.this);
        contentFragment.add(wmc);
    }


}
