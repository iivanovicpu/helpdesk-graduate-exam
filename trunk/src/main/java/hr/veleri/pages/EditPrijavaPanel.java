package hr.veleri.pages;

import hr.veleri.data.dao.interfaces.PrijaveDao;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * User: iivanovic
 * Date: 06.09.11.
 * Time: 18:08
 */
public class EditPrijavaPanel extends Panel {
    @SpringBean
    private PrijaveDao prijaveDao;

    public EditPrijavaPanel(String componentId, final long entryId) {
        super(componentId);
        Form form = new Form("form") {
            protected void onSubmit() {
                PrijavaEditPage prijavaEditPage = new PrijavaEditPage(entryId);
                setResponsePage(prijavaEditPage);
                System.out.println("Edit: prijava (" + entryId + ")");
            }
        };
        add(form);
    }
}
