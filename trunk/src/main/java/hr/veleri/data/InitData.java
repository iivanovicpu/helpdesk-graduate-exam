package hr.veleri.data;

import hr.veleri.data.dao.interfaces.*;
import hr.veleri.data.dataobjects.Aplikacija;
import hr.veleri.data.dataobjects.Klijent;
import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.KorisnikZaposlenik;

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
            initKlijenti();
            initKorisniciKlijentiZaposlenici();
            setInitialised(true);
        } else {
            System.out.println("--- db already initialised !!! ---");
        }

    }

    private void initKorisniciKlijentiZaposlenici() {
        // klijenti
        Klijent pl = new Klijent("001", "Plava Laguna d.d.");
        Klijent riviera = new Klijent("002", "Riviera Poreč d.d.");
        Klijent imperial = new Klijent("003", "Imperial Rab d.d.");
        Klijent rabac = new Klijent("004", "Rabac d.d.");
        Klijent ss = new Klijent("005", "Sunčana staza d.o.o.");
        Klijent makarska = new Klijent("006", "Hoteli Makarska d.d.");
        Klijent supetrus = new Klijent("007", "Supetrus Hoteli d.d.");
        klijentDao.save(pl);
        klijentDao.save(riviera);
        klijentDao.save(imperial);
        klijentDao.save(rabac);
        klijentDao.save(ss);
        klijentDao.save(makarska);
        klijentDao.save(supetrus);

        // korisnici
        Korisnik iivanovic = new Korisnik("Igor", "Ivanović", "igor.ivanovic@veleri.hr", "3b6d69c25e32c90b85c11c03dfde97e6");
        Korisnik ppauro = new Korisnik("Patrik", "Pauro", "patrik.pauro@veleri.hr", "6c722ef06c1db589caef37c1b5fb8850");
        Korisnik tcosic = new Korisnik("Tomislav", "Ćosić", "tomislav.cosic@veleri.hr", "cb3918a280403e87479d2ec7141b32dc");
        Korisnik vjuhas = new Korisnik("Vanja", "Juhas", "vanja.juhas@veleri.hr", "9829bcb9e22402246d5f865c74117a16");
        korisnikDao.save(iivanovic);    // pass: iivanovic
        korisnikDao.save(ppauro);       // pass: ppauro
        korisnikDao.save(tcosic);       // pass: tcosic
        korisnikDao.save(vjuhas);       // pass: vjuhas

        // aplikacije
        Aplikacija ptw = new Aplikacija("Prijava Turista - Web", "PTW");
        Aplikacija ptz2 = new Aplikacija("Prijava Turista u TZ", "PTZ2");
        Aplikacija pos = new Aplikacija("Blagajnička kasa", "POS");
        Aplikacija rec = new Aplikacija("Recepcijsko poslovanje", "REC2");
        Aplikacija pro = new Aplikacija("Prodaja smještajnih kapaciteta", "PRO2");
        Aplikacija gas = new Aplikacija("Gastronomija", "GAS");
        Aplikacija mje = new Aplikacija("Mjenjačnica", "MJE3");
        aplikacijaDao.save(ptw);
        aplikacijaDao.save(ptz2);
        aplikacijaDao.save(rec);
        aplikacijaDao.save(pos);
        aplikacijaDao.save(pro);
        aplikacijaDao.save(gas);
        aplikacijaDao.save(mje);

        // korisnici zaposlenici: ppauro, iivanovic - zaduzeni za vise aplikacija
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(ptw, iivanovic));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(ptz2, iivanovic));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(pos, iivanovic));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(pro, iivanovic));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(mje, ppauro));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(rec, ppauro));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(gas, ppauro));
        korisnikZaposlenikDao.save(new KorisnikZaposlenik(pro, ppauro));

    }

    private void initKlijenti() {
    }

    public boolean isInitialised() {
        return initialised;
    }

    public void setInitialised(boolean initialised) {
        this.initialised = initialised;
    }
}
