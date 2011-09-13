package hr.veleri.pages;

import hr.veleri.HelpdeskSession;
import hr.veleri.data.dao.interfaces.KorisnikDao;
import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.TipKorisnika;
import hr.veleri.datavalidation.KorisnikValidator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * User: iivanovic
 * Date: 12.09.11.
 * Time: 08:39
 */
public class KorisnikEditPage extends AuthenticatedPage {

    @SpringBean
    KorisnikDao korisnikDao;

    public KorisnikEditPage(long entryId) {

        /* redirekcija ako nije administrator logiran */
        if(!((HelpdeskSession) getSession()).getLoggedInUser().getTipKorisnika().equals(TipKorisnika.ADMINISTRATOR))
            setResponsePage(UnouthorisedContentPage.class);

        final Korisnik korisnik = korisnikDao.findById(entryId);
        final Model<Korisnik> model = new Model<Korisnik>(korisnik);

        wmc = new WebMarkupContainer("korisnikContainer");

        FeedbackPanel errorMsg = new FeedbackPanel("errorMsg");
        wmc.add(errorMsg);

        final KorisnikValidator korisnikValidator = new KorisnikValidator(korisnik, errorMsg);


        System.out.println(korisnik);

        Form<Korisnik> form = new Form<Korisnik>("korisnikForm", model) {
            @Override
            protected void onSubmit() {
                if (!korisnikValidator.isValid(korisnik))
                    return;
                korisnikDao.save(model.getObject());
                setResponsePage(KorisniciPage.class);
            }
        };

        TextField<Korisnik> prezime = new TextField<Korisnik>("prezime", new PropertyModel<Korisnik>(korisnik, "prezime"));
        TextField<Korisnik> ime = new TextField<Korisnik>("ime", new PropertyModel<Korisnik>(korisnik, "ime"));
        TextField<Korisnik> email = new TextField<Korisnik>("email", new PropertyModel<Korisnik>(korisnik, "email"));
//        IValidator emailRequiredValidator = EmailAddressValidator.getInstance();
//        email.add(emailRequiredValidator);

        form.add(prezime, ime, email);
        wmc.add(form);

        init(KorisnikEditPage.this);
        contentFragment.add(wmc);

    }
}
