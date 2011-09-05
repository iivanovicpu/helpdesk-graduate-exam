package hr.veleri.data.dao.jpa;

import hr.veleri.data.dao.interfaces.PrijaveDao;
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

public class PrijaveImp extends AbstractDaoJPAImpl<Prijava> implements PrijaveDao {

    public PrijaveImp() {
        super(Prijava.class);
    }

    @Transactional
    public List<Prijava> findAll() {
        return getJpaTemplate().execute(new JpaCallback<List<Prijava>>() {
            public List<Prijava> doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Prijava> query = em.createQuery("select p from Prijava p", Prijava.class);
                return query.getResultList();
            }
        });
    }

    @Transactional
    public int countAll() {
        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            public Integer doInJpa(EntityManager em) throws PersistenceException {
                TypedQuery<Long> query = em.createQuery("select count (p) from Prijava p", Long.class);
                return (query.getSingleResult()).intValue();
            }
        });

    }

    public List selectEntries(int first, int count, final SortParam sortParam) {
        List sortedEntries = findAll();
        if (sortParam != null) {
            if (sortParam.getProperty().equals("prirbr")) {
                Collections.sort(sortedEntries, new Comparator() {
                    public int compare(Object arg0, Object arg1) {
                        Prijava entry1 = (Prijava) arg0;
                        Prijava entry2 = (Prijava) arg1;
                        int result = String.valueOf(entry1.getPrirbr()).compareTo(String.valueOf(entry2.getPrirbr()));
                        return sortParam.isAscending() ? result : -result;
                    }
                });
            }
            if (sortParam.getProperty().equals("pridatum")) {
                Collections.sort(sortedEntries, new Comparator() {
                    public int compare(Object arg0, Object arg1) {
                        Prijava entry1 = (Prijava) arg0;
                        Prijava entry2 = (Prijava) arg1;
                        int result = String.valueOf(entry1.getPridatum()).compareTo(String.valueOf(entry2.getPridatum()));
                        return sortParam.isAscending() ? result : -result;
                    }
                });
            }
            if (sortParam.getProperty().equals("prijavio")) {
                Collections.sort(sortedEntries, new Comparator() {
                    public int compare(Object arg0, Object arg1) {
                        Prijava entry1 = (Prijava) arg0;
                        Prijava entry2 = (Prijava) arg1;
                        int result = String.valueOf(entry1.getPrijavio().getKorisnik()).compareTo(String.valueOf(entry2.getPrijavio().getKorisnik()));
                        return sortParam.isAscending() ? result : -result;
                    }
                });
            }
            if (sortParam.getProperty().equals("aplikacija")) {
                Collections.sort(sortedEntries, new Comparator() {
                    public int compare(Object arg0, Object arg1) {
                        Prijava entry1 = (Prijava) arg0;
                        Prijava entry2 = (Prijava) arg1;
                        int result = String.valueOf(entry1.getAplikacija()).compareTo(String.valueOf(entry2.getAplikacija()));
                        return sortParam.isAscending() ? result : -result;
                    }
                });
            }
            if (sortParam.getProperty().equals("opis")) {
                Collections.sort(sortedEntries, new Comparator() {
                    public int compare(Object arg0, Object arg1) {
                        Prijava entry1 = (Prijava) arg0;
                        Prijava entry2 = (Prijava) arg1;
                        int result = String.valueOf(entry1.getOpis()).compareTo(String.valueOf(entry2.getOpis()));
                        return sortParam.isAscending() ? result : -result;
                    }
                });
            }
        }
        return sortedEntries.subList(first, first + count);
    }

}
