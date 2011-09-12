package hr.veleri.datavalidation;

import hr.veleri.data.dataobjects.Intervencija;
import hr.veleri.datavalidation.HelpdeskDataObjectValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.ResourceModel;

/**
 * User: iivanovic
 * Date: 12.09.11.
 * Time: 11:47
 */
public class IntervencijaValidator extends HelpdeskDataObjectValidator<Intervencija> {

    public IntervencijaValidator(Intervencija object, FeedbackPanel feedbackPanel) {
        super(object, feedbackPanel);
    }

    @Override
    public boolean isValid(Intervencija intervencija) {
        getProperties().clear();
        if (intervencija.getMinutaTrajanja() <= 0)
            getProperties().put("trajanje", new ResourceModel("intervencijaEditPage.trajanje.fail").getObject());
        if (null == intervencija.getOpis() || intervencija.getOpis().equals(""))
            getProperties().put("opis", new ResourceModel("intervencijaEditPage.opis.fail").getObject());
        return isAllValid();
    }
}
