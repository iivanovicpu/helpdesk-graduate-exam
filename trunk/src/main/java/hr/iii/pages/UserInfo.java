package hr.iii.pages;

import hr.iii.WicketApplication;
import hr.iii.data.User;
import org.apache.wicket.PageParameters;
import org.apache.wicket.model.Model;

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

//        User user = ((WicketApplication) getApplication()).getUser(id);
//        setDefaultModel(new Model(user));
    }
}
