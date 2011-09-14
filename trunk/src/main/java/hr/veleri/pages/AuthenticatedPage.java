package hr.veleri.pages;

import hr.veleri.HelpdeskSession;
import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.TipKorisnika;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
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

    protected void init(AuthenticatedPage page) {
        subjectFragment = new Fragment("header", "header", page);
        contentFragment = new Fragment("content", "content", page);
        loginInfoFragment = new Fragment("userinfo", "userinfo", page);

        initLoginInfo();

        if (((HelpdeskSession) getSession()).getLoggedInUser().getTipKorisnika().equals(TipKorisnika.ADMINISTRATOR))
            addNavigation();
        if (((HelpdeskSession) getSession()).getLoggedInUser().getTipKorisnika().equals(TipKorisnika.PODRSKA))
            addZaposlenikNavigation();
        if (((HelpdeskSession) getSession()).getLoggedInUser().getTipKorisnika().equals(TipKorisnika.KLIJENT))
            addKlijentNavigation();
    }

    private void addKlijentNavigation() {
        setNavigation(new KlijentNavigation("navigation") {
            public Component getContent(String id) {
                return contentFragment;
            }

            public Component getSubject(String id) {
                return subjectFragment;
            }

            public Component getUserInfo(String id) {
                return loginInfoFragment;
            }
        });
        add(navigation);

    }

    private void addZaposlenikNavigation() {
        setNavigation(new ZaposlenikNavigation("navigation") {
            public Component getContent(String id) {
                return contentFragment;
            }

            public Component getSubject(String id) {
                return subjectFragment;
            }

            public Component getUserInfo(String id) {
                return loginInfoFragment;
            }
        });
        add(navigation);
    }

    private void initLoginInfo() {
        Korisnik korisnik = ((HelpdeskSession) getSession()).getLoggedInUser();
        loginInfoFragment.add(new Label("user", korisnik.getIme() + " " + korisnik.getPrezime() + " (" + korisnik.getTipKorisnika() + ")"));
        Link logoutLink = new LogoutLink("logout", Login.class);

        loginInfoFragment.add(logoutLink);
    }

    private void addNavigation() {
        setNavigation(new AdminNavigation("navigation") {
            public Component getContent(String id) {
                return contentFragment;
            }

            public Component getSubject(String id) {
                return subjectFragment;
            }

            public Component getUserInfo(String id) {
                return loginInfoFragment;
            }
        });
        add(navigation);
    }
}
