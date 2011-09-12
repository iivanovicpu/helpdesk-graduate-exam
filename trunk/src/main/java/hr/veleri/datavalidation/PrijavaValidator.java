package hr.veleri.datavalidation;

import hr.veleri.data.dataobjects.Prijava;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.ResourceModel;

/**
 * User: iivanovic
 * Date: 12.09.11.
 * Time: 13:14
 */
public class PrijavaValidator extends HelpdeskDataObjectValidator<Prijava> {

    public PrijavaValidator(Prijava object, FeedbackPanel feedbackPanel) {
        super(object, feedbackPanel);
    }

    @Override
    public boolean isValid(Prijava prijava) {
        getProperties().clear();
        if (null == prijava.getOpis() || prijava.getOpis().equals(""))
            getProperties().put("opis", new ResourceModel("prijavaEditPage.opis.fail").getObject());
        return isAllValid();
    }
}
