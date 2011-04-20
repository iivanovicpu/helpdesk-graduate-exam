package hr.veleri.pages;

import hr.veleri.WicketApplication;
import hr.veleri.data.dataobjects.Korisnik;
import org.apache.wicket.PageParameters;

/**
 * User: iivanovic
 * Date: 14.10.2010.
 * Time: 11:38:08
 */
public class UserInfo extends WebServicePage {

    public UserInfo(PageParameters pg) {
        Integer id = pg.getAsInteger("id");
        if (id == null) {
            id = 1;
        }

//        Korisnik korisnik = ((WicketApplication) getApplication()).getSessionStore().ge(id);
//        setDefaultModel(new Model(korisnik));
    }
}
