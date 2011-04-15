package hr.veleri.data;

import hr.veleri.data.dao.interfaces.KlijentiDao;
import hr.veleri.data.dataobjects.Klijent;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * User: iivanovic
 * Date: 15.04.11.
 * Time: 12:06
 */
public class InitData {
    private boolean initialised;

    @SpringBean
    private KlijentiDao klijentiDao;


    public void setKlijentiDao(KlijentiDao klijentiDao) {
        this.klijentiDao = klijentiDao;
    }

    public void init() {
        if (!isInitialised()) {
            klijentiDao.save(new Klijent("001", "Plava Laguna d.d."));
            klijentiDao.save(new Klijent("002", "Riviera Poreč d.d."));
            klijentiDao.save(new Klijent("003", "Imperial Rab d.d."));
            klijentiDao.save(new Klijent("004", "Rabac d.d."));
            klijentiDao.save(new Klijent("005", "Sunčana staza d.o.o."));
            klijentiDao.save(new Klijent("006", "Hoteli Makarska d.d."));
            klijentiDao.save(new Klijent("007", "Supetrus Hoteli d.d."));

            setInitialised(true);
        } else {
            System.out.println("--- db already initialised !!! ---");
        }

    }

    public boolean isInitialised() {
        return initialised;
    }

    public void setInitialised(boolean initialised) {
        this.initialised = initialised;
    }
}
