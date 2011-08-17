package hr.veleri.pages;

import hr.veleri.HelpdeskSession;
import hr.veleri.data.dataobjects.Korisnik;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        addNavigation();
    }

    private void initLoginInfo() {
        Korisnik korisnik = ((HelpdeskSession) getSession()).getLoggedInUser();
        loginInfoFragment.add(new Label("user", korisnik.getIme() + " " + korisnik.getPrezime() + "(" + korisnik.getTipKorisnika() + ")"));
        Link logoutLink = new LogoutLink("logout", Login.class);

        loginInfoFragment.add(logoutLink);

        /* forma za promjenu jezika */
        Form localeForm = new Form("setLocale");
        loginInfoFragment.add(localeForm);
        localeForm.add(new SubmitLink("switchToHr") {
            @Override
            public void onSubmit() {
                getSession().setLocale(new Locale("hr"));
            }
        });
        localeForm.add(new SubmitLink("switchToEn") {
            @Override
            public void onSubmit() {
                getSession().setLocale(Locale.ENGLISH);
            }

        });
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
