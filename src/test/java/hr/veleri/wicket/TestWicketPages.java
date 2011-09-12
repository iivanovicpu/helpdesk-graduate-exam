package hr.veleri.wicket;

import hr.veleri.HelpdeskApplication;
import hr.veleri.pages.HomePage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class TestWicketPages {

    protected WicketTester tester;

    @Before
    public void setup() {
        final ApplicationContextMock acm = new ApplicationContextMock();

//        EventDao eventDao = Mockito.mock(EventDao.class);

//        acm.putBean("eventDao", eventDao);

        tester = new WicketTester(new HelpdeskApplication() {
            /* (non-Javadoc)
                * @see hr.veleri.WicketApplication#getGuiceInjector()
                */
            @Override
            protected SpringComponentInjector getSpringInjector() {
                return new SpringComponentInjector(this, acm, true);
            }
        });
    }

    @Test
    public void testStartPage() {
        tester.startPage(HomePage.class);
    }

    @Test
    public void testEventPage() {
//        tester.startPage(EventPage.class);
    }

}
