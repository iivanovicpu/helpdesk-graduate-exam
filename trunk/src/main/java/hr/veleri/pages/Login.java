package hr.veleri.pages;

import hr.veleri.AuthenticationException;
import hr.veleri.HelpdeskSession;
import hr.veleri.data.AppConfiguration;
import hr.veleri.data.InitData;
import hr.veleri.data.dao.interfaces.KorisnikDao;
import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.TipKorisnika;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * User: iivanovic
 * Date: 05.10.2010.
 * Time: 11:16:53
 */
public class Login extends WebPage {
    private String username;
    private String password;

    @SpringBean
    private AppConfiguration appConfiguration;

    @SpringBean
    private KorisnikDao korisnikDao;

    @SpringBean
    private InitData initData;



    public Login() {
        /* logout - ako je netko vec logiran */
        HelpdeskSession session = (HelpdeskSession) getSession();
        session.setLoggedInUser(null);

        initApplication();
        FeedbackPanel errorMsg = new FeedbackPanel("errorMsg");
        add(errorMsg);

        /* forma za promjenu jezika */
        Form localeForm = new Form("setLocale");
        add(localeForm);
        List supportedLocales = new ArrayList();
        supportedLocales.add(Locale.ENGLISH);
        supportedLocales.add(new Locale("hr"));
        DropDownChoice selectedLocale = new DropDownChoice("selectedLocale", new PropertyModel(getSession(), "locale"), supportedLocales);
        localeForm.add(selectedLocale);


        Form form = new Form("loginForm", new CompoundPropertyModel(this)) {
//        Form form = new Form("loginForm", new CompoundPropertyModel(this)) {


            @Override
            protected void onSubmit() {
                try {
                    Korisnik korisnik = korisnikDao.getKorisnik(username, password);
                    if (korisnikDao.isKorisnikZaposlenik(korisnik))
                        korisnik.setTipKorisnika(TipKorisnika.ZAPOSLENIK);
                    if (korisnikDao.isKorisnikKlijent(korisnik))
                        korisnik.setTipKorisnika(TipKorisnika.KLIJENT);
                    if (korisnik.getTipKorisnika() == null)
                        korisnik.setTipKorisnika(TipKorisnika.ADMINISTRATOR);
                    ((HelpdeskSession) getSession()).setLoggedInUser(korisnik);
                    if (!continueToOriginalDestination())
                        setResponsePage(HomePage.class);
                } catch (AuthenticationException e) {
                    error("Login failed! Try again");
                }
            }
        };
        add(form);
        form.add(new TextField("username"));
        form.add(new PasswordTextField("password"));
        add(new Label("t", "properties: " + "-"));
    }

    private void initApplication() {
        initData();
    }

    /* inicijalizira bazu podataka ako je u postavkama uključen developmentMode*/
    private void initData() {
        HelpdeskSession session = (HelpdeskSession) getSession();
        session.setLoggedInUser(null);

        if (appConfiguration.isDevelopmentMode()) {
            initData.init();
        }
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
