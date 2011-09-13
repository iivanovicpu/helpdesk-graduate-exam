package hr.veleri.pages;

import hr.veleri.data.dao.interfaces.PrijaveDao;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * User: iivanovic
 * Date: 06.09.11.
 * Time: 18:08
 */
public class EditRowPanel extends Panel {

    public EditRowPanel(String componentId, final long entryId, final AuthenticatedPage authenticatedPage) {
        super(componentId);
        Form form = new Form("form") {
            protected void onSubmit() {
                setResponsePage(authenticatedPage);
            }
        };
        SubmitLink link = new SubmitLink("edit",form);
        form.add(link);
        add(form);
    }
}
