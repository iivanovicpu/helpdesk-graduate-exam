package hr.veleri.data;

import hr.veleri.data.dao.interfaces.*;
import hr.veleri.data.dataobjects.*;

/**
 * User: iivanovic
 * Date: 15.04.11.
 * Time: 12:06
 */
public class InitData {
    private boolean initialised;

    private KlijentDao klijentDao;

    private KorisnikDao korisnikDao;

    private KorisnikKlijentDao korisnikKlijentDao;

    private AplikacijaDao aplikacijaDao;

    private KorisnikZaposlenikDao korisnikZaposlenikDao;


    public void setKlijentDao(KlijentDao klijentDao) {
        this.klijentDao = klijentDao;
    }

    public void setKorisnikDao(KorisnikDao korisnikDao) {
        this.korisnikDao = korisnikDao;
    }

    public void setKorisnikKlijentDao(KorisnikKlijentDao korisnikKlijentDao) {
        this.korisnikKlijentDao = korisnikKlijentDao;
    }

    public void setAplikacijaDao(AplikacijaDao aplikacijaDao) {
        this.aplikacijaDao = aplikacijaDao;
    }

    public void setKorisnikZaposlenikDao(KorisnikZaposlenikDao korisnikZaposlenikDao) {
        this.korisnikZaposlenikDao = korisnikZaposlenikDao;
    }

    public void init() {
        if (!isInitialised()) {
            initExampleData();
            setInitialised(true);
        } else {
            System.out.println("--- db already initialised !!! ---");
        }

    }

    private void initExampleData() {
        // klijenti
        Klijent pl = klijentDao.save(new Klijent("001", "Plava Laguna d.d."));
        Klijent riviera = klijentDao.save(new Klijent("002", "Riviera Poreč d.d."));
        Klijent imperial = klijentDao.save(new Klijent("003", "Imperial Rab d.d."));
        Klijent rabac = klijentDao.save(new Klijent("004", "Rabac d.d."));
        Klijent ss = klijentDao.save(new Klijent("005", "Sunčana staza d.o.o."));
        Klijent makarska = klijentDao.save(new Klijent("006", "Hoteli Makarska d.d."));
        Klijent supetrus = klijentDao.save(new Klijent("007", "Supetrus Hoteli d.d."));

        // korisnici
        Korisnik iivanovic = korisnikDao.save(new Korisnik("Igor", "Ivanović", "igor.ivanovic@veleri.hr", "3b6d69c25e32c90b85c11c03dfde97e6"));    // pass: iivanovic
        Korisnik ppauro = korisnikDao.save(new Korisnik("Patrik", "Pauro", "patrik.pauro@veleri.hr", "6c722ef06c1db589caef37c1b5fb8850"));      // pass: ppauro
        Korisnik tcosic = korisnikDao.save(new Korisnik("Tomislav", "Ćosić", "tomislav.cosic@veleri.hr", "cb3918a280403e87479d2ec7141b32dc"));  // pass: tcosic
        Korisnik vjuhas = korisnikDao.save(new Korisnik("Vanja", "Juhas", "vanja.juhas@veleri.hr", "9829bcb9e22402246d5f865c74117a16"));        // pass: vjuhas
        Korisnik dostojic = korisnikDao.save(new Korisnik("Damir", "Ostojić", "damir.ostojic@veleri.hr", "9bddd73fd4e48c8cc0adcd9c75df88be"));    // pass: dostojic

        // aplikacije
        Aplikacija ptw = aplikacijaDao.save(new Aplikacija("Prijava Turista - Web", "PTW"));
        Aplikacija ptz2 = aplikacijaDao.save(new Aplikacija("Prijava Turista u TZ", "PTZ2"));
        Aplikacija pos = aplikacijaDao.save(new Aplikacija("Blagajnička kasa", "POS"));
        Aplikacija rec = aplikacijaDao.save(new Aplikacija("Recepcijsko poslovanje", "REC2"));
        Aplikacija pro = aplikacijaDao.save(new Aplikacija("Prodaja smještajnih kapaciteta", "PRO"));
        Aplikacija gas = aplikacijaDao.save(new Aplikacija("Gastronomija", "GAS"));
        Aplikacija mje = aplikacijaDao.save(new Aplikacija("Mjenjačnica", "MJE3"));

        // korisnici zaposlenici: ppauro, iivanovic - zaduzeni za vise aplikacija
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(ptw, iivanovic));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(ptz2, iivanovic));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(pos, iivanovic));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(pro, iivanovic));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(mje, ppauro));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(rec, ppauro));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(gas, ppauro));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(pro, ppauro));

        // korisnici klijenti: tcosic, vjuhas - korisnici aplikacija
        korisnikKlijentDao.save(new KorisnikKlijent(pl, tcosic));
        korisnikKlijentDao.save(new KorisnikKlijent(ss, vjuhas));
        korisnikKlijentDao.save(new KorisnikKlijent(imperial, dostojic));

    }

    public boolean isInitialised() {
        return initialised;
    }

    public void setInitialised(boolean initialised) {
        this.initialised = initialised;
    }
}
