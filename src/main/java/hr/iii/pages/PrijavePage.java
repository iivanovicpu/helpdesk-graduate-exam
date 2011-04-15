package hr.iii.pages;

import hr.iii.data.dao.interfaces.PrijaveDao;
import hr.iii.data.dataobjects.Prijava;
import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxNavigationToolbar;
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
 * Date: 11.10.2010.
 * Time: 14:54:49
 */
public class PrijavePage extends AuthenticatedPage {

    @SpringBean
    private PrijaveDao prijaveDao;

    public PrijavePage(final PageParameters pp) {
        wmc = new WebMarkupContainer("listPrijavaContainer");

        IColumn[] columns = {
                new PropertyColumn(new Model("ID"), "priid"),
//                new AbstractColumn(new Model("First name")) {
//                    public void populateItem(
//                            Item cellItem, String componentId, IModel rowModel) {
//                        cellItem.add(new FirstNamePanel(componentId, rowModel));
//                    }
//                },
                new PropertyColumn(new Model("Rbr"), "prirbr", "prirbr"),
                new PropertyColumn(new Model("Phone number"), "pridatum", "pridatum"),
//                new AbstractColumn(new Model("Delete")) {
//                    public void populateItem(Item cellItem, String componentId, IModel rowModel) {
//                        int entryId = ((PhoneBookEntry) rowModel.getObject()).getId();
//                        cellItem.add(new DeleteEntryPanel(componentId, entryId));
//                    }
//                }
        };
        SortableDataProvider provider = new SortableDataProvider() {
            public int size() {
                return prijaveDao.countAll();
            }

            public IModel model(Object object) {
                Prijava entry = (Prijava) object;
                return new Model(entry);
            }

            public Iterator iterator(int first, int count) {
                SortParam sortParam = getSort();
                return prijaveDao.selectEntries(first, count, sortParam).iterator();
            }
        };
//        DefaultDataTable dataTable = new DefaultDataTable("entries", columns, provider.get(), 3);
        /* za custom tablicu ne može se koristiti DefaultDataTable klasa */
        DataTable dataTable = new DataTable("entries", columns, provider, 3) {
            protected Item newRowItem(String id, int index, IModel model) {
                return new OddEvenItem(id, index, model);
            }
        };
        dataTable.addTopToolbar(new HeadersToolbar(dataTable, provider));
        dataTable.addBottomToolbar(new NavigationToolbar(dataTable){
        });

        /* ajax paginacija  -  */
//        dataTable.setOutputMarkupId(true);
//        AjaxNavigationToolbar navigationToolbar = new AjaxNavigationToolbar(dataTable);
//        dataTable.addBottomToolbar(navigationToolbar);

        wmc.add(dataTable);
        init(PrijavePage.this);
        contentFragment.add(wmc);



    }
}
