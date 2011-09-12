package hr.veleri.pages;

import hr.veleri.HelpdeskSession;
import hr.veleri.data.dao.interfaces.AplikacijaDao;
import hr.veleri.data.dao.interfaces.KorisnikKlijentDao;
import hr.veleri.data.dao.interfaces.PrijaveDao;
import hr.veleri.data.dataobjects.*;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.MinimumValidator;
import org.apache.wicket.validation.validator.StringValidator;

import java.util.Date;
import java.util.Locale;

/**
 * User: iivanovic
 * Date: 10.09.11.
 * Time: 07:21
 */
public class DodajPrijavuPage extends AuthenticatedPage {

    @SpringBean
    AplikacijaDao aplikacijaDao;

    @SpringBean
    PrijaveDao prijaveDao;

    DodajPrijavuPage() {
        final Prijava prijava = new Prijava();
        prijava.setPrijavio((((HelpdeskSession) getSession()).getLoggedInUser()));
        prijava.setPridatum(new Date());

        final Model<Prijava> prijavaModel = new Model<Prijava>(prijava);

        wmc = new WebMarkupContainer("prijavaContainer");


        final DateTextField datum = new DateTextField("datum", new Model<Date>(new Date()), new StyleDateConverter("M-", false)) {
            @Override
            public Locale getLocale() {
                return getSession().getLocale();
            }
        };
        datum.setEnabled(false);

        ChoiceRenderer<Aplikacija> choiceRendererAplikacija = new ChoiceRenderer<Aplikacija>("sifra");
        PropertyModel<Aplikacija> aplikacijaPropertyModel = new PropertyModel<Aplikacija>(prijavaModel.getObject(), "aplikacija");
        DropDownChoice<Aplikacija> aplikacije = new DropDownChoice<Aplikacija>("aplikacije", aplikacijaPropertyModel, aplikacijaDao.findAll(), choiceRendererAplikacija);

        final TextField<Prijava> prijavio = new TextField<Prijava>("prijavio", new PropertyModel<Prijava>(prijava,"prijavio"));
        final TextArea<Prijava> opis = new TextArea<Prijava>("opis", new PropertyModel<Prijava>(prijava, "opis"));
        IValidator opisValidator = new StringValidator.LengthBetweenValidator(7, 4000);
        opis.setRequired(true);
        opis.add(opisValidator);

        final TextArea<Prijava> napomena = new TextArea<Prijava>("napomena", new PropertyModel<Prijava>(prijava, "napomena"));

        Form<Prijava> form = new Form<Prijava>("prijavaForm",prijavaModel){
            @Override
            protected void onSubmit() {
                prijaveDao.save(prijava);
                setResponsePage(PrijavePage.class);
            }
        };

        Button odustaniBtn = new Button("odustani") {
            public void onSubmit() {
                setResponsePage(PrijavePage.class);
            }
        };
        odustaniBtn.setDefaultFormProcessing(false);
        form.add(aplikacije, datum, opis, napomena, prijavio, odustaniBtn);

        FeedbackPanel errorMsg = new FeedbackPanel("errorMsg");
        wmc.add(errorMsg);

        wmc.add(form);
        init(DodajPrijavuPage.this);
        contentFragment.add(wmc);
    }

}
