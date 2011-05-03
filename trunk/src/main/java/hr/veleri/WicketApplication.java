package hr.veleri;

import hr.veleri.pages.*;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 *
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 * @see wicket.myproject.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

    /**
     * Constructor
     */
    public WicketApplication() {
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
        mountBookmarkablePage("event", KlijentiPage.class);
        mountBookmarkablePage("users", RadnikPage.class);
        getMarkupSettings().setDefaultMarkupEncoding("UTF8");
        addComponentInstantiationListener(getSpringInjector());

//        initData();


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
