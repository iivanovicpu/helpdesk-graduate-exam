package hr.veleri.pages;

import hr.veleri.HelpdeskSession;
import hr.veleri.data.dao.interfaces.IntervencijeDao;
import hr.veleri.data.dao.interfaces.KorisnikZaposlenikDao;
import hr.veleri.data.dataobjects.Aplikacija;
import hr.veleri.data.dataobjects.Intervencija;
import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.Prijava;
import hr.veleri.datavalidation.IntervencijaValidator;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.MinimumValidator;
import org.apache.wicket.validation.validator.StringValidator;

import javax.xml.validation.Validator;
import java.util.Date;
import java.util.Locale;

/**
 * User: iivanovic
 * Date: 08.09.11.
 * Time: 14:36
 */
public class DodajIntervencijuPage extends AuthenticatedPage {

    @SpringBean
    IntervencijeDao intervencijeDao;

    @SpringBean
    KorisnikZaposlenikDao korisnikZaposlenikDao;

    public DodajIntervencijuPage(Prijava prijava) {

        wmc = new WebMarkupContainer("intervencijaContainer");
        FeedbackPanel errorMsg = new FeedbackPanel("errorMsg");
        wmc.add(errorMsg);


        final Intervencija intervencija = new Intervencija();
        intervencija.setPrijava(prijava);
        Korisnik korisnik = ((HelpdeskSession) getSession()).getLoggedInUser();
        Aplikacija aplikacija = prijava.getAplikacija();

        intervencija.setZaposlenik(korisnikZaposlenikDao.findByKorisnikAndAplikacija(korisnik, aplikacija));
        final Model<Intervencija> model = new Model<Intervencija>(intervencija);

        final IntervencijaValidator intervencijaValidator = new IntervencijaValidator(intervencija, errorMsg);

        Button odustaniBtn = new Button("odustani") {
            public void onSubmit() {
                setResponsePage(new PrijavaEditPage(intervencija.getPrijava().getPriid()));
            }
        };
        odustaniBtn.setDefaultFormProcessing(false);

        Label prirbr = new Label("prirbr", new PropertyModel<Prijava>(intervencija.getPrijava(), "prirbr"));
        final DateTextField datum = new DateTextField("datum", new Model<Date>(new Date()), new StyleDateConverter("M-", false)) {
            @Override
            public Locale getLocale() {
                return getSession().getLocale();
            }
        };
        datum.setEnabled(false);

        TextField<Intervencija> zaposlenik = new TextField<Intervencija>("zaposlenik", new PropertyModel<Intervencija>(intervencija, "zaposlenik"));
        zaposlenik.setEnabled(false);
        final TextArea<Intervencija> opis = new TextArea<Intervencija>("opis", new PropertyModel<Intervencija>(intervencija, "opis"));
        final TextField<Intervencija> trajanje = new TextField<Intervencija>("trajanje", new PropertyModel<Intervencija>(intervencija, "minutaTrajanja"));

        Form form = new Form<Intervencija>("intervencijaForm", model) {
            protected void onSubmit() {
                if(!intervencijaValidator.isValid(model.getObject()))
                    return;
                intervencija.setDatum(datum.getModel().getObject());
                intervencijeDao.save(this.getModel().getObject());
                setResponsePage(new PrijavaEditPage(intervencija.getPrijava().getPriid()));
            }
        };

        form.add(prirbr, datum, zaposlenik, opis, trajanje, odustaniBtn);

        wmc.add(form);
        init(DodajIntervencijuPage.this);
        contentFragment.add(wmc);
    }

}
