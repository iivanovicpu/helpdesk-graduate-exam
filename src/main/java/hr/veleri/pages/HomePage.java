package hr.veleri.pages;

import hr.veleri.data.AppConfiguration;
import hr.veleri.data.InitData;
import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 */
public class HomePage extends AuthenticatedPage {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor that is invoked when page is invoked without a em.
     *
     * @param parameters Page parameters
     */

    @SpringBean
    private AppConfiguration appConfiguration;

    @SpringBean
    private InitData initData;


    public HomePage(final PageParameters parameters) {
        init(HomePage.this);

        initData();

        System.out.println("---- init homePage ----");
    }

    private void initData() {
        if (appConfiguration.isDevelopmentMode()) {
            initData.init();
        }
    }

}
