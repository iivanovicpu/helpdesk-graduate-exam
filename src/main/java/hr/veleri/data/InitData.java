package hr.veleri.data;

import hr.veleri.data.dao.interfaces.KlijentDao;
import hr.veleri.data.dao.interfaces.KorisnikDao;
import hr.veleri.data.dataobjects.Klijent;
import hr.veleri.data.dataobjects.Korisnik;

/**
 * User: iivanovic
 * Date: 15.04.11.
 * Time: 12:06
 */
public class InitData {
    private boolean initialised;

    private KlijentDao klijentDao;

    private KorisnikDao korisnikDao;


    public void setKlijentDao(KlijentDao klijentDao) {
        this.klijentDao = klijentDao;
    }

    public void setKorisnikDao(KorisnikDao korisnikDao) {
        this.korisnikDao = korisnikDao;
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
        korisnikDao.save(new Korisnik("Igor","Ivanović","igor.ivanovic@veleri.hr",""));
        korisnikDao.save(new Korisnik("Patrik","Pauro","patrik.pauro@veleri.hr",""));
        korisnikDao.save(new Korisnik("Tomislav","Ćosić","tomislav.cosic@veleri.hr",""));
        korisnikDao.save(new Korisnik("Vanja","Juhas","vanja.juhas@veleri.hr",""));
    }

    private void initKlijenti() {
        klijentDao.save(new Klijent("001", "Plava Laguna d.d."));
        klijentDao.save(new Klijent("002", "Riviera Poreč d.d."));
        klijentDao.save(new Klijent("003", "Imperial Rab d.d."));
        klijentDao.save(new Klijent("004", "Rabac d.d."));
        klijentDao.save(new Klijent("005", "Sunčana staza d.o.o."));
        klijentDao.save(new Klijent("006", "Hoteli Makarska d.d."));
        klijentDao.save(new Klijent("007", "Supetrus Hoteli d.d."));
    }

    public boolean isInitialised() {
        return initialised;
    }

    public void setInitialised(boolean initialised) {
        this.initialised = initialised;
    }
}
