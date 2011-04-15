package hr.veleri.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * User: iivanovic
 * Date: 05.10.2010.
 * Time: 08:42:07
 */
public abstract class Navigation extends Panel {
    public Navigation(String id) {
        super(id);
        add(getUserInfo("userinfo"));
        add(getSubject("header"));
        add(getContent("content"));
    }

    public abstract Component getSubject(String id);

    public abstract Component getContent(String id);

    public abstract Component getUserInfo(String id);
}
