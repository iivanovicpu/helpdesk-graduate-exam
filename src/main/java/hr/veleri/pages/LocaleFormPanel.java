package hr.veleri.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.Locale;

/**
 * User: iivanovic
 * Date: 17.08.11.
 * Time: 09:42
 */
public class LocaleFormPanel extends Panel{
    public LocaleFormPanel(String id) {
        super(id);
        Form localeForm = new Form("setLocale");
        add(localeForm);
        localeForm.add(new SubmitLink("switchToHr") {
            @Override
            public void onSubmit() {
                getSession().setLocale(new Locale("hr"));
            }
        });
        localeForm.add(new SubmitLink("switchToEn") {
            @Override
            public void onSubmit() {
                getSession().setLocale(Locale.ENGLISH);
            }

        });

    }
}
