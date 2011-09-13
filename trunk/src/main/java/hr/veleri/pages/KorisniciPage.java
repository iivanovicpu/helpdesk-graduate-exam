package hr.veleri.pages;

import hr.veleri.HelpdeskSession;
import hr.veleri.data.dao.interfaces.KorisnikDao;
import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.TipKorisnika;
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
 * Date: 04.10.2010.
 * Time: 13:58:51
 */
public class KorisniciPage extends AuthenticatedPage {

    @SpringBean
    private KorisnikDao korisnikDao;


    public KorisniciPage(final PageParameters pp) {
        init(KorisniciPage.this);

        /* redirekcija ako nije administrator logiran */
        if(!((HelpdeskSession) getSession()).getLoggedInUser().getTipKorisnika().equals(TipKorisnika.ADMINISTRATOR))
            setResponsePage(UnouthorisedContentPage.class);

        wmc = new WebMarkupContainer("korisniciContainer");


            IColumn[] columns = {
                new PropertyColumn(new Model("Prezime"), "prezime", "prezime"),
                new PropertyColumn(new Model("Ime"), "ime", "ime"),
                new PropertyColumn(new Model("E-mail"), "email", "email"),
                new PropertyColumn(new Model("Tip"), "tip", "tipKorisnika"),
                new AbstractColumn<Korisnik>(new Model<String>("")) {
                    public void populateItem(Item cellItem, String componentId, IModel rowModel) {
                        long entryId = ((Korisnik) rowModel.getObject()).getId();
                        cellItem.add(new EditRowPanel(componentId, entryId, new KorisnikEditPage(entryId)));
                    }
                }

        };
        SortableDataProvider provider = new SortableDataProvider() {
            public int size() {
                return korisnikDao.countAll();
            }

            public IModel model(Object object) {
                Korisnik entry = (Korisnik) object;
                return new Model<Korisnik>(entry);
            }

            public Iterator iterator(int first, int count) {
                SortParam sortParam = getSort();
                return korisnikDao.selectEntries(first, count, sortParam).iterator();
            }
        };
        DataTable dataTable = new DataTable("entries", columns, provider, 5) {
            protected Item newRowItem(String id, int index, IModel model) {
                return new OddEvenItem(id, index, model);
            }
        };

        dataTable.addTopToolbar(new HeadersToolbar(dataTable, provider));
        dataTable.addBottomToolbar(new NavigationToolbar(dataTable) {
        });

        wmc.add(dataTable);

        contentFragment.add(wmc);
    }

}
