package hr.veleri.data.dao.jpa;

import hr.veleri.data.dao.interfaces.KlijentDao;
import hr.veleri.data.dataobjects.Klijent;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KlijentDaoImp extends AbstractDaoJPAImpl<Klijent> implements KlijentDao {

    public KlijentDaoImp() {
        super(Klijent.class);
    }

    @Transactional
    public List<Klijent> findAll() {
        return getJpaTemplate().execute(new JpaCallback<List<Klijent>>() {
            public List<Klijent> doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Klijent> query = em.createQuery("select k from Klijent k", Klijent.class);
                return query.getResultList();
            }
        });
    }

    @Transactional
    public int countAll() {
        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            public Integer doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Long> query = em.createQuery("select count (k) from Klijent k", Long.class);
                return (query.getSingleResult()).intValue();
            }
        });

    }

    public List selectEntries(int first, int count, final SortParam sortParam) {
        List sortedEntries = findAll();
        if (sortParam != null) {
            if (sortParam.getProperty().equals("sifra")) {
                Collections.sort(sortedEntries, new Comparator() {
                    public int compare(Object arg0, Object arg1) {
                        Klijent entry1 = (Klijent) arg0;
                        Klijent entry2 = (Klijent) arg1;
                        int result = String.valueOf(entry1.getSifra()).compareTo(String.valueOf(entry2.getSifra()));
                        return sortParam.isAscending() ? result : -result;
                    }
                });
            }
            if (sortParam.getProperty().equals("naziv")) {
                Collections.sort(sortedEntries, new Comparator() {
                    public int compare(Object arg0, Object arg1) {
                        Klijent klijent1 = (Klijent) arg0;
                        Klijent klijent2 = (Klijent) arg1;
                        int result = klijent1.getNaziv().compareTo(klijent2.getNaziv());
                        return sortParam.isAscending() ? result : -result;
                    }
                });
            }
        }
        return sortedEntries.subList(first, first + count);
    }

    public Klijent findBySifra(final String sifra) {
        try {
            return getJpaTemplate().execute(new JpaCallback<Klijent>() {
                public Klijent doInJpa(EntityManager em) throws PersistenceException {
                    TypedQuery<Klijent> query = em.createQuery("select k from Klijent k where k.sifra = ?1", Klijent.class);
                    query.setParameter(1, sifra);
                    return query.getSingleResult();
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
