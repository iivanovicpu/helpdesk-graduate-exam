package hr.veleri.datavalidation;

import hr.veleri.data.dao.interfaces.KlijentDao;
import hr.veleri.data.dataobjects.Klijent;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * User: iivanovic
 * Date: 15.09.11.
 * Time: 09:19
 */
public class KlijentValidator extends HelpdeskDataObjectValidator<Klijent> {

    @SpringBean
    KlijentDao klijentDao;

    public KlijentValidator() {
    }

    public KlijentValidator(Klijent object, FeedbackPanel feedbackPanel) {
        super(object, feedbackPanel);
    }

    @Override
    public boolean isValid(Klijent klijent) {
        getProperties().clear();
        if (null == klijent.getSifra() || klijent.getSifra().equals(""))
            getProperties().put("sifra", new ResourceModel("klijentEditPage.sifra.fail").getObject());
        else if (!klijent.getSifra().matches("[0-9]*")) {
            getProperties().put("sifra", new ResourceModel("klijentEditPage.sifraformat.fail").getObject());
        } else if (null != klijent.getSifra() || klijent.getSifra().equals("")) {
            if (null != klijentDao.findBySifra(klijent.getSifra()))
                getProperties().put("sifra", new ResourceModel("klijentEditPage.sifraunique.fail").getObject());
        }
        if (null == klijent.getNaziv() || klijent.getNaziv().equals(""))
            getProperties().put("opis", new ResourceModel("klijentEditPage.naziv.fail").getObject());
        return isAllValid();
    }

    public void setKlijentDao(KlijentDao klijentDao) {
        this.klijentDao = klijentDao;
    }
}
