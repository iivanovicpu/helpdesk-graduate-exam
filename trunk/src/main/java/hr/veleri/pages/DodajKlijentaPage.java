package hr.veleri.pages;

import hr.veleri.data.dao.interfaces.KlijentDao;
import hr.veleri.data.dataobjects.Klijent;
import hr.veleri.datavalidation.KlijentValidator;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * User: iivanovic
 * Date: 15.09.11.
 * Time: 09:08
 */
public class DodajKlijentaPage extends AuthenticatedPage {

    @SpringBean
    KlijentDao klijentDao;

    @SpringBean
    KlijentValidator klijentValidator;

    public DodajKlijentaPage() {
        wmc = new WebMarkupContainer("klijentContainer");
        FeedbackPanel errorMsg = new FeedbackPanel("errorMsg");
        wmc.add(errorMsg);


        final Klijent klijent = new Klijent();

        final Model<Klijent> model = new Model<Klijent>(klijent);

//        klijentValidator = new KlijentValidator(klijent, errorMsg);
        klijentValidator.setDataObject(klijent);
        klijentValidator.setFeedbackPanel(errorMsg);

        Button odustaniBtn = new Button("odustani") {
            public void onSubmit() {
                setResponsePage(KlijentiPage.class);
            }
        };
        odustaniBtn.setDefaultFormProcessing(false);

        TextField<Klijent> sifra = new TextField<Klijent>("sifra", new PropertyModel<Klijent>(klijent, "sifra"));
        final TextField<Klijent> naziv = new TextField<Klijent>("naziv", new PropertyModel<Klijent>(klijent, "naziv"));

        Form form = new Form<Klijent>("klijentForm", model) {
            protected void onSubmit() {
                if(!klijentValidator.isValid(model.getObject()))
                    return;
                klijentDao.save(this.getModel().getObject());
                setResponsePage(KlijentiPage.class);
            }
        };

        form.add(sifra, naziv, odustaniBtn);

        wmc.add(form);
        init(DodajKlijentaPage.this);
        contentFragment.add(wmc);
    }
}
