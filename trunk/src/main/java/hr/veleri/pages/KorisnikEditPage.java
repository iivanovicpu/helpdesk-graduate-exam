package hr.veleri.pages;

import hr.veleri.data.dao.interfaces.KorisnikDao;
import hr.veleri.data.dataobjects.Korisnik;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.EmailAddressValidator;

/**
 * User: iivanovic
 * Date: 12.09.11.
 * Time: 08:39
 */
public class KorisnikEditPage extends AuthenticatedPage {

    @SpringBean
    KorisnikDao korisnikDao;

    public KorisnikEditPage(long entryId) {
        wmc = new WebMarkupContainer("korisnikContainer");

        FeedbackPanel errorMsg = new FeedbackPanel("errorMsg");
        wmc.add(errorMsg);


        Korisnik korisnik = korisnikDao.findById(entryId);
        final Model<Korisnik> model = new Model<Korisnik>(korisnik);
        System.out.println(korisnik);

        Form<Korisnik> form = new Form<Korisnik>("korisnikForm", model) {
            @Override
            protected void onSubmit() {
                korisnikDao.save(model.getObject());
                setResponsePage(KorisniciPage.class);
            }
        };

        RequiredTextField<Korisnik> prezime = new RequiredTextField<Korisnik>("prezime", new PropertyModel<Korisnik>(korisnik, "prezime"));
        RequiredTextField<Korisnik> ime = new RequiredTextField<Korisnik>("ime", new PropertyModel<Korisnik>(korisnik, "ime"));
        RequiredTextField<Korisnik> email = new RequiredTextField<Korisnik>("email", new PropertyModel<Korisnik>(korisnik, "email"));
        IValidator emailRequiredValidator = EmailAddressValidator.getInstance();
        email.add(emailRequiredValidator);

        form.add(prezime, ime, email);
        wmc.add(form);

        init(KorisnikEditPage.this);
        contentFragment.add(wmc);

    }
}
