package hr.veleri.pages;

import hr.veleri.HelpdeskSession;
import org.apache.wicket.markup.html.link.Link;

/**
 * User: iivanovic
 * Date: 09.05.11.
 * Time: 13:20
 */
public class LogoutLink extends Link {

    public LogoutLink(String id) {
        super(id);
    }

    @Override
    public void onClick() {
        HelpdeskSession session = (HelpdeskSession) getSession();
        session.setLoggedInUser(null);
    }
}
