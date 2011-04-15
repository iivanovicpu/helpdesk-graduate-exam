package hr.iii.pages;

import hr.iii.AuthenticationException;
import hr.iii.HelpdeskSession;
import hr.iii.data.AppConfiguration;
import hr.iii.data.InitData;
import hr.iii.data.dataobjects.Korisnik;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

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
    private InitData initData;

    public Login() {
        initApplication();
        FeedbackPanel errorMsg = new FeedbackPanel("errorMsg");
        add(errorMsg);
        Form form = new Form("loginForm", new CompoundPropertyModel(this)) {
            @Override
            protected void onSubmit() {
                try {
                    // todo: dohvat korisnika iz servisa
                    Korisnik korisnik = new Korisnik();//radnikDao.getRadnik(username, password);
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

//        todo: ako je development mode ukljucen ponovno napuni bazu
//        System.out.println("-- application: " + appConfiguration.getAppName());
//        System.out.println("-- version: " + appConfiguration.getVersion());
//        System.out.println("-- devMode: " + appConfiguration.isDevelopmentMode());
    }

    private void initApplication() {

    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
