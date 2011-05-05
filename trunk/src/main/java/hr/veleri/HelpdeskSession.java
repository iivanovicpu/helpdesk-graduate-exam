package hr.veleri;

import hr.veleri.data.dataobjects.Korisnik;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

/**
 * User: iivanovic
 * Date: 05.10.2010.
 * Time: 11:21:22
 */
public class HelpdeskSession extends WebSession {

    private Korisnik loggedInUser;

    public HelpdeskSession(Request request) {
        super(request);
    }

    public Korisnik getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Korisnik loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}
