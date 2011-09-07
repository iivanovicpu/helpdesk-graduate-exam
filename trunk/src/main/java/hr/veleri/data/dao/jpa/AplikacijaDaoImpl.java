package hr.veleri.data.dao.jpa;

import hr.veleri.data.dao.interfaces.AplikacijaDao;
import hr.veleri.data.dataobjects.Aplikacija;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: iivanovic
 * Date: 02.05.11.
 * Time: 13:32
 */
public class AplikacijaDaoImpl extends AbstractDaoJPAImpl<Aplikacija> implements AplikacijaDao {
    public AplikacijaDaoImpl() {
        super(Aplikacija.class);
    }

    @Transactional
    public List<Aplikacija> findAll() {
        return getJpaTemplate().execute(new JpaCallback<List<Aplikacija>>() {
            public List<Aplikacija> doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Aplikacija> query = em.createQuery("select a from Aplikacija a", Aplikacija.class);
                return query.getResultList();
            }
        });
    }

    @Transactional
    public int countAll() {
        return getJpaTemplate().execute(new JpaCallback<Integer>() {
            public Integer doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Long> query = em.createQuery("select count (a) from Aplikacija a", Long.class);
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
                        Aplikacija entry1 = (Aplikacija) arg0;
                        Aplikacija entry2 = (Aplikacija) arg1;
                        int result = String.valueOf(entry1.getSifra()).compareTo(String.valueOf(entry2.getSifra()));
                        return sortParam.isAscending() ? result : -result;
                    }
                });
            }
            if (sortParam.getProperty().equals("naziv")) {
                Collections.sort(sortedEntries, new Comparator() {
                    public int compare(Object arg0, Object arg1) {
                        Aplikacija klijent1 = (Aplikacija) arg0;
                        Aplikacija klijent2 = (Aplikacija) arg1;
                        int result = klijent1.getNaziv().compareTo(klijent2.getNaziv());
                        return sortParam.isAscending() ? result : -result;
                    }
                });
            }
        }
        return sortedEntries.subList(first, first + count);
    }

}
