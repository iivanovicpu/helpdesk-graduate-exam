package hr.iii.data.dao.jpa;

import hr.iii.AuthenticationException;
import hr.iii.data.dao.interfaces.KorisnikDao;
import hr.iii.data.dataobjects.Korisnik;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class KorisnikDaoJPAImp extends AbstractDaoJPAImpl<Korisnik> implements KorisnikDao {

    public KorisnikDaoJPAImp() {
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

    public Korisnik getRadnik(final String username, final String password) {
        List<Korisnik> list = findAll();
        for (Korisnik korisnik : list) {
            if (korisnik.authenticate(username,password))
                return korisnik;
        }
        throw new AuthenticationException();
    }
}
