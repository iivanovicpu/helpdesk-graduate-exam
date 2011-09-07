package hr.veleri.data.dao.jpa;

import hr.veleri.data.dao.interfaces.IntervencijeDao;
import hr.veleri.data.dataobjects.Intervencija;
import hr.veleri.data.dataobjects.Klijent;
import hr.veleri.data.dataobjects.Prijava;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IntervencijeDaoImp extends AbstractDaoJPAImpl<Intervencija> implements IntervencijeDao {

    public IntervencijeDaoImp() {
        super(Intervencija.class);
    }

    @Transactional
    public List<Intervencija> findAll() {
        return getJpaTemplate().execute(new JpaCallback<List<Intervencija>>() {
            public List<Intervencija> doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Intervencija> query = em.createQuery("select k from Intervencija k", Intervencija.class);
                return query.getResultList();
            }
        });
    }

    @Transactional
    public List<Intervencija> findAllByPrijava(final Prijava prijava) {
        return getJpaTemplate().execute(new JpaCallback<List<Intervencija>>() {
            public List<Intervencija> doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Intervencija> query = em.createQuery("select k from Intervencija k where k.prijava = ?1", Intervencija.class);
                query.setParameter(1, prijava);
                return query.getResultList();
            }
        });
    }

    public int countAllByPrijava(final Prijava prijava) {
        return getJpaTemplate().execute(new JpaCallback<Integer>() {
            public Integer doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Long> query = em.createQuery("select count (k) from Intervencija k where k.prijava = ?1", Long.class);
                query.setParameter(1, prijava);
                return (query.getSingleResult()).intValue();
            }
        });
    }

    @Transactional
    public int countAll() {
        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            public Integer doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Long> query = em.createQuery("select count (k) from Intervencija k", Long.class);
                return (query.getSingleResult()).intValue();
            }
        });


    }

    public List selectEntries(int first, int count, final SortParam sortParam, Prijava prijava) {
        List sortedEntries = findAllByPrijava(prijava);
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

}
