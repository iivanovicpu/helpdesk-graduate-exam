package hr.iii.pages;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebPage;

import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * User: iivanovic
 * Date: 14.10.2010.
 * Time: 11:32:24
 */
public class WebServicePage extends WebPage {

    protected final Log logger = LogFactory.getLog(getClass());


    public WebServicePage() {
        setStatelessHint(true);
    }

    protected final void onRender(MarkupStream markupStream) {

        PrintWriter pw = new PrintWriter(getResponse().getOutputStream());
//        System.out.println(getRequest());

        if(logger.isInfoEnabled())
            logger.info(getDefaultModel().toString() + "----------------on render----");
        pw.write(getXML().toString());
        pw.close();
    }

    protected XStream createXStream() {
        XStream xstream = new XStream();
        xstream.setMode(XStream.ID_REFERENCES);
        return xstream;
    }

    private CharSequence getXML() {
        XStream xstream = createXStream();
        return xstream.toXML(getDefaultModelObject());
    }

    @Override
    public final String getMarkupType() {
        return "xml";
    }

    @Override
    public final boolean hasAssociatedMarkup() {
        return false;
    }

    @Override
    public final Component add(IBehavior... behaviors) {
        throw new UnsupportedOperationException(
                "WebServicePage does not support IBehaviours");
    }
}
