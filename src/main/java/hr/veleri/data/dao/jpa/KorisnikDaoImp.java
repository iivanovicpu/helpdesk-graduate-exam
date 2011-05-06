package hr.veleri.data.dao.jpa;

import hr.veleri.AuthenticationException;
import hr.veleri.data.dao.interfaces.KorisnikDao;
import hr.veleri.data.dao.interfaces.KorisnikKlijentDao;
import hr.veleri.data.dao.interfaces.KorisnikZaposlenikDao;
import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.KorisnikKlijent;
import hr.veleri.data.dataobjects.KorisnikZaposlenik;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class KorisnikDaoImp extends AbstractDaoJPAImpl<Korisnik> implements KorisnikDao {

    @SpringBean
    private KorisnikKlijentDao korisnikKlijentDao;

    @SpringBean
    private KorisnikZaposlenikDao korisnikZaposlenikDao;

    public KorisnikDaoImp() {
        super(Korisnik.class);
    }

    @Transactional
    public List<Korisnik> findAll() {
        return getJpaTemplate().execute(new JpaCallback<List<Korisnik>>() {
            public List<Korisnik> doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Korisnik> query = em.createQuery("select r from Korisnik r", Korisnik.class);
                return query.getResultList();
            }
        });
    }

    @Transactional
    public int countAll() {
        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            public Integer doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Long> query = em.createQuery("select count (r) from Korisnik r", Long.class);
                return (query.getSingleResult()).intValue();
            }
        });

    }

    @Transactional
    public boolean isKorisnikKlijent(Korisnik korisnik) {
        for (KorisnikKlijent korisnikKlijent : korisnikKlijentDao.findAll()) {
            if (korisnikKlijent.getKorisnik().equals(korisnik))
                return true;
        }
        return false;
    }

    @Transactional
    public boolean isKorisnikZaposlenik(Korisnik korisnik) {
        for (KorisnikZaposlenik korisnikZaposlenik : korisnikZaposlenikDao.findAll()) {
            if (korisnikZaposlenik.getKorisnik().equals(korisnik))
                return true;
        }
        return false;
    }

    public Korisnik getKorisnik(final String username, final String password) {
        List<Korisnik> list = findAll();
        // administracija: admin - the MD5 encoder
        list.add(new Korisnik("Administrator", "sustava", "admin", "9031d52735e2a26a00a7d0d1c94d4743"));
        for (Korisnik korisnik : list) {
            if (korisnik.authenticate(username, password))
                return korisnik;
        }
        throw new AuthenticationException();
    }

    public void setKorisnikKlijentDao(KorisnikKlijentDao korisnikKlijentDao) {
        this.korisnikKlijentDao = korisnikKlijentDao;
    }

    public void setKorisnikZaposlenikDao(KorisnikZaposlenikDao korisnikZaposlenikDao) {
        this.korisnikZaposlenikDao = korisnikZaposlenikDao;
    }
}
