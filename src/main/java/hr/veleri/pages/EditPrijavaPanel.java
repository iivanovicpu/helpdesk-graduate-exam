package hr.veleri.pages;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * User: iivanovic
 * Date: 06.09.11.
 * Time: 18:08
 */
public class EditPrijavaPanel extends Panel {
    public EditPrijavaPanel(String componentId, final long entryId) {
        super(componentId);
        Form form = new Form("form") {
            protected void onSubmit() {
                // todo: pozvati formu za editiranje prijave
                System.out.println("Edit: prijava ("+ entryId +")");;
            };
        };
        add(form);
    }
}
