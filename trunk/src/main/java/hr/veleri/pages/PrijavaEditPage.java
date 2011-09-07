package hr.veleri.pages;

import hr.veleri.data.dao.interfaces.AplikacijaDao;
import hr.veleri.data.dao.interfaces.PrijaveDao;
import hr.veleri.data.dataobjects.Aplikacija;
import hr.veleri.data.dataobjects.Prijava;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * User: iivanovic
 * Date: 07.09.11.
 * Time: 10:13
 */
public class PrijavaEditPage extends AuthenticatedPage {
    @SpringBean
    private PrijaveDao prijaveDao;

    @SpringBean
    private AplikacijaDao aplikacijaDao;

    public PrijavaEditPage(long prijavaId) {
        Prijava prijava = prijaveDao.findById(prijavaId);
        wmc = new WebMarkupContainer("prijavaContainer");

        Model<Prijava> prijavaModel = new Model<Prijava>(prijava);
        Form form = new Form<Prijava>("prijavaForm", prijavaModel);
        Label prirbr = new Label("prirbr", new PropertyModel<Prijava>(prijava, "prirbr"));
        Label pridatum = new Label("pridatum", new PropertyModel<Prijava>(prijava, "pridatum"));

        ChoiceRenderer<Aplikacija> choiceRendererAplikacija = new ChoiceRenderer<Aplikacija>("sifra");

        PropertyModel<Aplikacija> aplikacijaPropertyModel = new PropertyModel<Aplikacija>(prijavaModel.getObject(), "aplikacija");

        DropDownChoice<Aplikacija> aplikacije = new DropDownChoice<Aplikacija>("aplikacije", aplikacijaPropertyModel, aplikacijaDao.findAll(), choiceRendererAplikacija);

        form.add(pridatum, prirbr, aplikacije);
        wmc.add(form);
        init(PrijavaEditPage.this);
        contentFragment.add(wmc);
    }
}
