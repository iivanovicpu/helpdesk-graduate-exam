package hr.veleri.pages;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * User: iivanovic
 * Date: 05.10.2010.
 * Time: 08:42:07
 */
public abstract class KlijentNavigation extends Navigation {
    public KlijentNavigation(String id) {
        super(id);
        add(getUserInfo("userinfo"));
        add(getSubject("header"));
        add(getContent("content"));
        addLocalePanel();
    }

    public void addLocalePanel() {
        add(new LocaleFormPanel("localePanel"));
    }
}
