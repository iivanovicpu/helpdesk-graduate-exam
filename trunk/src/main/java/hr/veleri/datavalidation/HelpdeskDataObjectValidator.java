package hr.veleri.datavalidation;

import hr.veleri.data.dataobjects.DomainObject;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * User: iivanovic
 * Date: 12.09.11.
 * Time: 11:40
 */
public abstract class HelpdeskDataObjectValidator<T extends DomainObject> implements Serializable {
    private DomainObject dataObject;
    private Map<String, String> properties = new HashMap<String, String>();
    private boolean isValid = true;
    private FeedbackPanel feedbackPanel;

    protected HelpdeskDataObjectValidator() {
    }

    public HelpdeskDataObjectValidator(T object, FeedbackPanel feedbackPanel) {
        this.dataObject = object;
        this.feedbackPanel = feedbackPanel;
    }

    public abstract boolean isValid(T object);

    public FeedbackPanel getFeedbackPanel() {
        return feedbackPanel;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isValid() {
        return isValid;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public boolean isAllValid() {
        for (String key : properties.keySet()) {
            getFeedbackPanel().info(properties.get(key));
        }
        return properties.isEmpty();
    }

    public void setDataObject(DomainObject dataObject) {
        this.dataObject = dataObject;
    }

    public void setFeedbackPanel(FeedbackPanel feedbackPanel) {
        this.feedbackPanel = feedbackPanel;
    }
}
