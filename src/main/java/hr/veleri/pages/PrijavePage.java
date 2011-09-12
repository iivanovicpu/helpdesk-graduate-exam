package hr.veleri.pages;

import hr.veleri.data.dao.interfaces.PrijaveDao;
import hr.veleri.data.dataobjects.Prijava;
import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.breadcrumb.BreadCrumbBar;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanelLink;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import sun.plugin.javascript.navig.Link;

import java.util.Iterator;

/**
 * User: iivanovic
 * Date: 11.10.2010.
 * Time: 14:54:49
 */
public class PrijavePage extends AuthenticatedPage {

    @SpringBean
    private PrijaveDao prijaveDao;

    public PrijavePage() {
        this(null);
    }

    public PrijavePage(final PageParameters pp) {
        wmc = new WebMarkupContainer("listPrijavaContainer");

        Form<Prijava> form = new Form<Prijava>("addForm",new Model<Prijava>(new Prijava())){
            @Override
            protected void onSubmit() {
                setResponsePage(new DodajPrijavuPage());
            }
        };
        IColumn[] columns = {
//                new PropertyColumn(new Model("ID"), "priid"),
//                new AbstractColumn(new Model("First name")) {
//                    public void populateItem(
//                            Item cellItem, String componentId, IModel rowModel) {
//                        cellItem.add(new FirstNamePanel(componentId, rowModel));
//                    }
//                },
                new PropertyColumn(new Model("Rbr"), "prirbr", "prirbr"),
                new PropertyColumn(new Model("Datum"), "pridatum", "pridatum"),
                new PropertyColumn(new Model("Korisnik"), "prijavio", "prijavio"),
                new PropertyColumn(new Model("Aplikacija"), "aplikacija", "aplikacija"),
                new PropertyColumn(new Model("Opis"), "opis", "opis"),
                new PropertyColumn(new Model("Napomena"), "napomena", "napomena"),
                new AbstractColumn(new Model("")) {
                    public void populateItem(Item cellItem, String componentId, IModel rowModel) {
                        long entryId = ((Prijava) rowModel.getObject()).getPriid();
                        cellItem.add(new EditPrijavaPanel(componentId, entryId));
                    }
                }
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
        DataTable dataTable = new DataTable("entries", columns, provider, 3) {
            protected Item newRowItem(String prirbr, int index, IModel model) {
                return new OddEvenItem(prirbr, index, model);
            }
        };
        dataTable.addTopToolbar(new HeadersToolbar(dataTable, provider));
        dataTable.addBottomToolbar(new NavigationToolbar(dataTable){
        });

        wmc.add(dataTable);
        wmc.add(form);
        init(PrijavePage.this);
        contentFragment.add(wmc);



    }
}
