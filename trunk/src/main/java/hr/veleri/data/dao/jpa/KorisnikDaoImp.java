package hr.veleri.data.dao.jpa;

import hr.veleri.AuthenticationException;
import hr.veleri.data.dao.interfaces.KorisnikDao;
import hr.veleri.data.dataobjects.Korisnik;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class KorisnikDaoImp extends AbstractDaoJPAImpl<Korisnik> implements KorisnikDao {

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

    public Korisnik getKorisnik(final String username, final String password) {
        List<Korisnik> list = findAll();
        for (Korisnik korisnik : list) {
            if (korisnik.authenticate(username,password))
                return korisnik;
        }
        throw new AuthenticationException();
    }
}
