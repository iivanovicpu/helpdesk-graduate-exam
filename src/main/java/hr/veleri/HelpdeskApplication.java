package hr.veleri;

import hr.veleri.pages.*;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.MixedParamUrlCodingStrategy;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class HelpdeskApplication extends WebApplication {

    /**
     * Constructor
     */
    public HelpdeskApplication() {
    }

    protected SpringComponentInjector getSpringInjector() {
        return new SpringComponentInjector(this);
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new HelpdeskSession(request);
    }


    @Override
    protected void init() {
        super.init();
        mountBookmarkablePage("klijenti", KlijentiPage.class);
        mountBookmarkablePage("korisnici", KorisniciPage.class);
        mountBookmarkablePage("login", Login.class);
        mountBookmarkablePage("prijave", PrijavePage.class);
        mount(new MixedParamUrlCodingStrategy("prijava",PrijavaEditPage.class,new String[]{"priid"}));
        mount(new MixedParamUrlCodingStrategy("intervencija",IntervencijaEditPage.class,new String[]{"intid"}));
        mountBookmarkablePage("home", HomePage.class);
        getMarkupSettings().setDefaultMarkupEncoding("UTF8");
        addComponentInstantiationListener(getSpringInjector());
        getMarkupSettings().setStripWicketTags(true);

        getSecuritySettings().setAuthorizationStrategy(
                /* AuthenticatedPage klase su zaštićene login-om */
                new SimplePageAuthorizationStrategy(AuthenticatedPage.class, Login.class) {

                    @Override
                    protected boolean isAuthorized() {
                        return ((HelpdeskSession) Session.get()).getLoggedInUser() != null;
                    }
                });

    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
}
