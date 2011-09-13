package hr.veleri.pages;

import hr.veleri.data.dao.interfaces.IntervencijeDao;
import hr.veleri.data.dataobjects.Intervencija;
import hr.veleri.data.dataobjects.Prijava;
import hr.veleri.datavalidation.IntervencijaValidator;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * User: iivanovic
 * Date: 08.09.11.
 * Time: 11:37
 */
public class IntervencijaEditPage extends AuthenticatedPage {

    @SpringBean
    private IntervencijeDao intervencijeDao;

    public IntervencijaEditPage(PageParameters pp) {
        try {
            long intid = Long.valueOf((String) pp.get("intid"));
            setResponsePage(new IntervencijaEditPage(intid));
        } catch (NumberFormatException e) {
            setResponsePage(UnouthorisedContentPage.class);
        } catch (ClassCastException e) {
            setResponsePage(UnouthorisedContentPage.class);
        }
    }

    public IntervencijaEditPage(long intid) {
        wmc = new WebMarkupContainer("intervencijaContainer");

        final FeedbackPanel errorMsg = new FeedbackPanel("errorMsg");
        wmc.add(errorMsg);


        final Intervencija intervencija = intervencijeDao.findById(intid);
        final Model<Intervencija> model = new Model<Intervencija>(intervencija);

        final IntervencijaValidator intervencijaValidator = new IntervencijaValidator(intervencija, errorMsg);

        Button odustaniBtn = new Button("odustani") {
            public void onSubmit() {
                setResponsePage(new PrijavaEditPage(intervencija.getPrijava().getPriid()));
            }
        };
        odustaniBtn.setDefaultFormProcessing(false);

        Label prirbr = new Label("prirbr", new PropertyModel<Prijava>(intervencija.getPrijava(), "prirbr"));
        Label datum = new Label("datum", new PropertyModel<Intervencija>(intervencija, "datumFormatted"));
        Label zaposlenik = new Label("zaposlenik", new PropertyModel<Intervencija>(intervencija, "zaposlenik"));
        TextArea<Intervencija> opis = new TextArea<Intervencija>("opis", new PropertyModel<Intervencija>(intervencija, "opis"));
        final TextField<Intervencija> trajanje = new TextField<Intervencija>("trajanje", new PropertyModel<Intervencija>(intervencija, "minutaTrajanja"));

        Form form = new Form<Intervencija>("intervencijaForm", model) {
            protected void onSubmit() {
                if (!intervencijaValidator.isValid(intervencija))
                    return;
                intervencijeDao.save(this.getModel().getObject());
                setResponsePage(new PrijavaEditPage(intervencija.getPrijava().getPriid()));
            }
        };

        form.add(prirbr, datum, zaposlenik, opis, odustaniBtn, trajanje);

        wmc.add(form);
        init(IntervencijaEditPage.this);
        contentFragment.add(wmc);
    }
}
