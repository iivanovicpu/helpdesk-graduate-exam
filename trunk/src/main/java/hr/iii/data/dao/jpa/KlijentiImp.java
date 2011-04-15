package hr.iii.data.dao.jpa;

import hr.iii.data.dao.interfaces.KlijentiDao;
import hr.iii.data.dataobjects.Klijent;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KlijentiImp extends AbstractDaoJPAImpl<Klijent> implements KlijentiDao {

    public KlijentiImp() {
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
//            if (sortParam.getProperty().equals("mjesto.naziv")) {
//                Collections.sort(sortedEntries, new Comparator() {
//                    public int compare(Object arg0, Object arg1) {
//                        Klijent klijent1 = (Klijent) arg0;
//                        Klijent klijent2 = (Klijent) arg1;
//                        String mjesto1 = klijent1.getMjesto() != null ? klijent1.getMjesto().getNaziv() : "";
//                        String mjesto2 = klijent2.getMjesto() != null ? klijent2.getMjesto().getNaziv() : "";
//
//                        int result = mjesto1.compareTo(mjesto2);
//                        return sortParam.isAscending() ? result : -result;
//                    }
//                });
//            }
        }
        return sortedEntries.subList(first, first + count);
    }

}
