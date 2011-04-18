package hr.veleri.data;

import hr.veleri.data.dao.interfaces.KlijentiDao;
import hr.veleri.data.dao.interfaces.KorisniciDao;
import hr.veleri.data.dataobjects.Klijent;
import hr.veleri.data.dataobjects.Korisnik;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * User: iivanovic
 * Date: 15.04.11.
 * Time: 12:06
 */
public class InitData {
    private boolean initialised;

    private KlijentiDao klijentiDao;

    private KorisniciDao korisniciDao;


    public void setKlijentiDao(KlijentiDao klijentiDao) {
        this.klijentiDao = klijentiDao;
    }

    public void setKorisniciDao(KorisniciDao korisniciDao) {
        this.korisniciDao = korisniciDao;
    }

    public void init() {
        if (!isInitialised()) {
            initKlijenti();
            initKorisnici();
            setInitialised(true);
        } else {
            System.out.println("--- db already initialised !!! ---");
        }

    }

    private void initKorisnici() {
        korisniciDao.save(new Korisnik("Igor","Ivanović","igor.ivanovic@veleri.hr",""));
        korisniciDao.save(new Korisnik("Patrik","Pauro","patrik.pauro@veleri.hr",""));
        korisniciDao.save(new Korisnik("Tomislav","Ćosić","tomislav.cosic@veleri.hr",""));
        korisniciDao.save(new Korisnik("Vanja","Juhas","vanja.juhas@veleri.hr",""));
    }

    private void initKlijenti() {
        klijentiDao.save(new Klijent("001", "Plava Laguna d.d."));
        klijentiDao.save(new Klijent("002", "Riviera Poreč d.d."));
        klijentiDao.save(new Klijent("003", "Imperial Rab d.d."));
        klijentiDao.save(new Klijent("004", "Rabac d.d."));
        klijentiDao.save(new Klijent("005", "Sunčana staza d.o.o."));
        klijentiDao.save(new Klijent("006", "Hoteli Makarska d.d."));
        klijentiDao.save(new Klijent("007", "Supetrus Hoteli d.d."));
    }

    public boolean isInitialised() {
        return initialised;
    }

    public void setInitialised(boolean initialised) {
        this.initialised = initialised;
    }
}
