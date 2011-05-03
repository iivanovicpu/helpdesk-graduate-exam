package hr.veleri.pages;

import hr.veleri.data.dao.interfaces.KorisnikDao;
import hr.veleri.data.dataobjects.Korisnik;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

/**
 * User: iivanovic
 * Date: 04.10.2010.
 * Time: 13:58:51
 */
public class RadnikPage extends AuthenticatedPage {

    @SpringBean
    private KorisnikDao korisnikDao;


    public RadnikPage(final PageParameters pp) {
        init(RadnikPage.this);

        wmc = new WebMarkupContainer("listRadContainer");

        wmc.add(new ListView<Korisnik>("listrad", new PropertyModel<List<Korisnik>>(this, "korisnikDao.findAll")) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Korisnik> item) {
                Korisnik korisnik = item.getModelObject();
                item.add(new Label("radnikId", String.valueOf(korisnik.getId())));
                item.add(new Label("radnikIme", korisnik.getIme()));
            }
        });
        contentFragment.add(wmc);
    }

}
