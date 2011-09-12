package hr.veleri.data.dao.jpa;

import hr.veleri.data.dao.interfaces.KorisnikKlijentDao;
import hr.veleri.data.dataobjects.Korisnik;
import hr.veleri.data.dataobjects.KorisnikKlijent;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: iivanovic
 * Date: 18.04.11.
 * Time: 13:49
 */
public class KorisnikKlijentDaoImpl extends AbstractDaoJPAImpl<KorisnikKlijent> implements KorisnikKlijentDao {

    public KorisnikKlijentDaoImpl() {
        super(KorisnikKlijent.class);
    }

    public Korisnik getKorisnik(String username, String password) {
        return null;  //todo: implementirati
    }

    public KorisnikKlijent findByKorisnik(final Korisnik korisnik) {
        return getJpaTemplate().execute(new JpaCallback<KorisnikKlijent>() {
            public KorisnikKlijent doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<KorisnikKlijent> query = em.createQuery("select k from KorisnikKlijent k where k.korisnik = ?1", KorisnikKlijent.class);
                query.setParameter(1, korisnik);
                return query.getSingleResult();
            }
        });

    }

    @Transactional
    public List<KorisnikKlijent> findAll() {
        return getJpaTemplate().execute(new JpaCallback<List<KorisnikKlijent>>() {
            public List<KorisnikKlijent> doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<KorisnikKlijent> query = em.createQuery("select k from KorisnikKlijent k", KorisnikKlijent.class);
                return query.getResultList();
            }
        });
    }

    @Transactional
    public int countAll() {
        return getJpaTemplate().execute(new JpaCallback<Integer>() {
            public Integer doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Long> query = em.createQuery("select count(k) from KorisnikKlijent k", Long.class);
                return query.getSingleResult().intValue();
            }
        });
    }
}
