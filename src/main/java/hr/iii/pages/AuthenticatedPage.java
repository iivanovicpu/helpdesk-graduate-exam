package hr.iii.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Fragment;

/**
 * User: iivanovic
 * Date: 05.10.2010.
 * Time: 11:08:26
 */
public class AuthenticatedPage extends WebPage {
    private Navigation navigation;
    protected WebMarkupContainer wmc;

    protected Fragment subjectFragment;
    protected Fragment contentFragment;
    protected Fragment loginInfoFragment;


    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    /**
     * init method must be invoked in constructor of WebPage sub class<br>
     * this method define navigation for authenticated pages, fill its content, header and userinfo fragments
     * Fragments must have wicket ids: "content", "subject", "userinInfo"
     *
     * @param page AuthenticatedPage - object of AuthenticatedPage subcalss
     */
    protected void init(AuthenticatedPage page) {
        subjectFragment = new Fragment("header", "header", page);
        contentFragment = new Fragment("content", "content", page);
        loginInfoFragment = new Fragment("userinfo", "userinfo", page);
        addNavigation();
    }

    private void addNavigation() {
        setNavigation(new Navigation("navigation") {
            @Override
            public Component getContent(String id) {
                return contentFragment;
            }

            @Override
            public Component getSubject(String id) {
                return subjectFragment;
            }

            @Override
            public Component getUserInfo(String id) {
                return loginInfoFragment;
            }
        });
        add(navigation);
    }
}
