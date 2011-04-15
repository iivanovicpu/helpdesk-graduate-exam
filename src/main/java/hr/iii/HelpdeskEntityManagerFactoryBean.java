package hr.iii;

import hr.iii.util.OdrzConfigurationDecryptor;
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
 */
public class HelpdeskEntityManagerFactoryBean extends LocalEntityManagerFactoryBean {
    protected final Log logger = LogFactory.getLog(getClass());
    private OdrzConfigurationDecryptor decryptor = new OdrzConfigurationDecryptor();


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
