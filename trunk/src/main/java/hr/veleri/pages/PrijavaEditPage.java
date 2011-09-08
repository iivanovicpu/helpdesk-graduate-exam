package hr.veleri.pages;

import hr.veleri.data.dao.interfaces.AplikacijaDao;
import hr.veleri.data.dao.interfaces.IntervencijeDao;
import hr.veleri.data.dao.interfaces.PrijaveDao;
import hr.veleri.data.dataobjects.Aplikacija;
import hr.veleri.data.dataobjects.Intervencija;
import hr.veleri.data.dataobjects.Prijava;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Iterator;
import java.util.List;

/**
 * User: iivanovic
 * Date: 07.09.11.
 * Time: 10:13
 */
public class PrijavaEditPage extends AuthenticatedPage {
    @SpringBean
    private PrijaveDao prijaveDao;

    @SpringBean
    private IntervencijeDao intervencijeDao;

    @SpringBean
    private AplikacijaDao aplikacijaDao;

    public PrijavaEditPage(long prijavaId) {
        final Prijava prijava = prijaveDao.findById(prijavaId);
        wmc = new WebMarkupContainer("prijavaContainer");

        Model<Prijava> prijavaModel = new Model<Prijava>(prijava);
        Form<Prijava> form = new Form<Prijava>("prijavaForm", prijavaModel){
            protected void onSubmit() {
                setResponsePage(new PrijavePage());
            }
        };
        Label prirbr = new Label("prirbr", new PropertyModel<Prijava>(prijava, "prirbr"));
        Label pridatum = new Label("pridatum", new PropertyModel<Prijava>(prijava, "pridatumFormatted"));
        Label pridatumzap = new Label("pridatumzap", new PropertyModel<Prijava>(prijava, "pridatumzapFormatted"));
        Label prijavio = new Label("prijavio", new PropertyModel<Prijava>(prijava, "prijavio"));
        Label aplikacija = new Label("aplikacija", new PropertyModel<Prijava>(prijava,"aplikacija"));
        Label opis = new Label("opis", new PropertyModel<Prijava>(prijava,"opis"));
        Label napomena = new Label("napomena", new PropertyModel<Prijava>(prijava,"napomena"));

        /* drop lista se ne ažurira s podatkom iz baze, dropliste ćemo koristiti samo za insert forme */
//        ChoiceRenderer<Aplikacija> choiceRendererAplikacija = new ChoiceRenderer<Aplikacija>("sifra");
//        PropertyModel<Aplikacija> aplikacijaPropertyModel = new PropertyModel<Aplikacija>(prijavaModel.getObject(), "aplikacija");
//        DropDownChoice<Aplikacija> aplikacije = new DropDownChoice<Aplikacija>("aplikacije", aplikacijaPropertyModel, aplikacijaDao.findAll(), choiceRendererAplikacija);

        Button dodajIntervenciju = new Button("dodajint") {
            public void onSubmit() {
                System.out.println("nova intervencija");
                setResponsePage(new DodajIntervencijuPage());
            }
        };
        dodajIntervenciju.setDefaultFormProcessing(false);

        form.add(dodajIntervenciju);


        form.add(pridatum, prirbr, aplikacija, prijavio, pridatumzap, opis, napomena);

        /* tablica intervencija */
        IColumn[] columns = {
                new PropertyColumn<Intervencija>(new Model<String>("Osoba"), "zaposlenik", "zaposlenik"),
                new PropertyColumn<Intervencija>(new Model<String>("Datum"), "datum", "datumFormatted"),
                new PropertyColumn<Intervencija>(new Model<String>("Opis"), "opis", "opis"),
                new PropertyColumn<Intervencija>(new Model<String>("Trajanje (minute"), "trajanje", "minutaTrajanja"),
                new AbstractColumn<Intervencija>(new Model<String>("edit")) {
                    public void populateItem(Item cellItem, String componentId, IModel rowModel) {
                        long entryId = ((Intervencija) rowModel.getObject()).getId();
                        cellItem.add(new EditRowPanel(componentId, entryId, new IntervencijaEditPage(entryId)));
                    }
                }
        };
        SortableDataProvider<Intervencija> provider = new SortableDataProvider<Intervencija>() {
            public int size() {
                return intervencijeDao.countAllByPrijava(prijava);
            }

            public IModel<Intervencija> model(Intervencija intervencija) {
                return new Model<Intervencija>(intervencija);
            }

            public Iterator<Intervencija> iterator(int first, int count) {
                SortParam sortParam = getSort();
                List<Intervencija> list = intervencijeDao.selectEntries(first, count, sortParam, prijava);
                return list != null ? list.iterator() : null;
            }
        };
//        DefaultDataTable dataTable = new DefaultDataTable("entries", columns, provider.get(), 3);
        /* za custom tablicu ne može se koristiti DefaultDataTable klasa */
        DataTable<Intervencija> dataTable = new DataTable<Intervencija>("entries", columns, provider, 3) {
            protected Item<Intervencija> newRowItem(String id, int index, IModel<Intervencija> model) {
                return new OddEvenItem<Intervencija>(id, index, model);
            }
        };
        dataTable.addTopToolbar(new HeadersToolbar(dataTable, provider));
        dataTable.addBottomToolbar(new NavigationToolbar(dataTable){
        });

        /* ajax paginacija  -  */
//        dataTable.setOutputMarkupId(true);
//        AjaxNavigationToolbar navigationToolbar = new AjaxNavigationToolbar(dataTable);
//        dataTable.addBottomToolbar(navigationToolbar);

        wmc.add(dataTable);

        wmc.add(form);
        init(PrijavaEditPage.this);
        contentFragment.add(wmc);
    }
}
