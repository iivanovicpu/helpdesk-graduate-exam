package hr.veleri.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * User: iivanovic
 * Date: 06.09.11.
 * Time: 12:34
 */
public abstract class Navigation extends Panel{
    public Navigation(String id) {
        super(id);
    }

    abstract void addLocalePanel();

    abstract Component getSubject(String id);

    abstract Component getContent(String id);

    abstract Component getUserInfo(String id);
}
