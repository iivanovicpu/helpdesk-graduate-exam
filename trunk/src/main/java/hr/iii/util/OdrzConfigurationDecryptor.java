package hr.iii.util;

import org.jasypt.util.TextEncryptor;

import java.util.Map;

/**
 * User: iivanovic
 * Date: 07.10.2010.
 * Time: 14:30:59
 */
public class OdrzConfigurationDecryptor {
    private static final String ENC_PASSWORD = "supertajnikljuc";

    private static TextEncryptor encryptor = new TextEncryptor();

    public OdrzConfigurationDecryptor() {
        encryptor.setPassword(ENC_PASSWORD);
    }

    public Map<String, Object> decryptConfiguration(Map<String, Object> map){
        for (String key : map.keySet()) {
            map.put(key,decrypt((String) map.get(key)));
        }
        return map;
    }

    public String decrypt(String encryptedValue){
        return encryptor.decrypt(encryptedValue);
    }

    public static void main(String[] args) {
        /* posgres:
        *   connection: jdbc:postgresql://localhost:5432/radninalog
        *   username:   radninalog
        *   password:   radninalog
        * */

//        OdrzConfigurationDecryptor decryptor = new OdrzConfigurationDecryptor();
        encryptor.setPassword(ENC_PASSWORD);
        System.out.println("radninalog: " + encryptor.encrypt("radninalog"));
        System.out.println("jdbc:postgresql://localhost:5432/helpdesk: " + encryptor.encrypt("jdbc:postgresql://localhost:5432/helpdesk"));
    }


}
