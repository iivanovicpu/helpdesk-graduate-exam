package hr.veleri.pages;

import hr.veleri.HelpdeskSession;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

/**
 * User: iivanovic
 * Date: 09.05.11.
 * Time: 13:20
 */
public class LogoutLink extends BookmarkablePageLink {


    public <C extends org.apache.wicket.Page> LogoutLink(String id, Class<C> pageClass) {
        super(id, pageClass);
    }

}
