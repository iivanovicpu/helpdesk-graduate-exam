package hr.veleri.datavalidation;

import hr.veleri.data.dataobjects.Korisnik;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.ResourceModel;

/**
 * User: iivanovic
 * Date: 12.09.11.
 * Time: 17:46
 */
public class KorisnikValidator extends HelpdeskDataObjectValidator<Korisnik> {

    public KorisnikValidator(Korisnik object, FeedbackPanel feedbackPanel) {
        super(object, feedbackPanel);
    }

    @Override
    public boolean isValid(Korisnik korisnik) {
        getProperties().clear();
        if (null == korisnik.getIme() || korisnik.getIme().equals(""))
            getProperties().put("ime", new ResourceModel("korisnikEditPage.ime.fail").getObject());
        if (null == korisnik.getPrezime() || korisnik.getPrezime().equals(""))
            getProperties().put("prezime", new ResourceModel("korisnikEditPage.prezime.fail").getObject());
        if (null == korisnik.getEmail() || korisnik.getEmail().equals(""))
            getProperties().put("email", new ResourceModel("korisnikEditPage.email.fail").getObject());
        else {
            if (!korisnik.getEmail().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z]{2,}){1}$)"))
                getProperties().put("email", new ResourceModel("korisnikEditPage.emailformat.fail").getObject());
        }
        return isAllValid();
    }
}
