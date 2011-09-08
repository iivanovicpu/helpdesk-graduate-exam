package hr.veleri.data.dao.jpa;

import hr.veleri.data.dao.interfaces.KorisnikZaposlenikDao;
import hr.veleri.data.dataobjects.Aplikacija;
import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.KorisnikZaposlenik;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: iivanovic
 * Date: 02.05.11.
 * Time: 13:51
 */
public class KorisnikZaposlenikDaoImpl extends AbstractDaoJPAImpl<KorisnikZaposlenik> implements KorisnikZaposlenikDao {
    public KorisnikZaposlenikDaoImpl() {
        super(KorisnikZaposlenik.class);
    }

    @Transactional
    public List<KorisnikZaposlenik> findAll() {
        return getJpaTemplate().execute(new JpaCallback<List<KorisnikZaposlenik>>() {
            public List<KorisnikZaposlenik> doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<KorisnikZaposlenik> query = em.createQuery("select kz from KorisnikZaposlenik kz", KorisnikZaposlenik.class);
                return query.getResultList();
            }
        });
    }

    @Transactional
    public int countAll() {
        return getJpaTemplate().execute(new JpaCallback<Integer>() {
            public Integer doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Long> query = em.createQuery("select count(kz) from KorisnikZaposlenik kz", Long.class);
                return query.getSingleResult().intValue();
            }
        });
    }

    public KorisnikZaposlenik findByKorisnikAndAplikacija(final Korisnik korisnik, final Aplikacija aplikacija) {
        return getJpaTemplate().execute(new JpaCallback<KorisnikZaposlenik>() {
            public KorisnikZaposlenik doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<KorisnikZaposlenik> query = em.createQuery("select kz from KorisnikZaposlenik kz where kz.korisnik = ?1 and kz.aplikacija = ?2", KorisnikZaposlenik.class);
                query.setParameter(1, korisnik).setParameter(2,aplikacija);
                return query.getSingleResult();
            }
        });
    }
}
