package hr.veleri;

import hr.veleri.util.ConfigurationDecryptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.spi.PersistenceProvider;
import java.util.Map;

/**
 * User: iivanovic
 * Date: 11.10.2010.
 * Time: 12:27:18
 * <br>Opis: Specifičan EntityManagerFactoryBean koji postavke za pristup bazi dekriptira prije same konekcije na bazu.
 * Da bi mogli u applicationContext xml-u pohraniti kriptirane podatke za pristup bazi (iz sigurnosnih razloga)
 * entity managment ih prije korištenja treba dekodirati, stoga je naslijeđena klasa LocalManagerFactoryBean i
 * modificiran način čitanja podataka za pristup
 */
public class HelpdeskEntityManagerFactoryBean extends LocalEntityManagerFactoryBean {
    protected final Log logger = LogFactory.getLog(getClass());
    private ConfigurationDecryptor decryptor = new ConfigurationDecryptor();


    public HelpdeskEntityManagerFactoryBean(Map<String, Object> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }

    private Map<String, Object> propertiesMap;

    @Override
    protected EntityManagerFactory createNativeEntityManagerFactory() throws PersistenceException {
        if (logger.isInfoEnabled()) {
            logger.info("Building JPA EntityManagerFactory for persistence unit '" + getPersistenceUnitName() + "'");
        }
        PersistenceProvider provider = getPersistenceProvider();
        if (provider != null) {
            // Create EntityManagerFactory directly through PersistenceProvider.
            EntityManagerFactory emf = provider.createEntityManagerFactory(getPersistenceUnitName(), getPropertiesMap());
            if (emf == null) {
                throw new IllegalStateException(
                        "PersistenceProvider [" + provider + "] did not return an EntityManagerFactory for name '" +
                                getPersistenceUnitName() + "'");
            }

            // debug:
            return emf;
        } else {
            // Let JPA perform its standard PersistenceProvider autodetection.
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(getPersistenceUnitName(), getPropertiesMap());
            return factory;
        }

    }

    public Map<String, Object> getPropertiesMap() {
        return decryptor.decryptConfiguration(propertiesMap);
    }
}
