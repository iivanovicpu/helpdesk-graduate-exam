package hr.iii.util;

import junit.framework.TestCase;

/**
 * User: iivanovic
 * Date: 07.10.2010.
 * Time: 14:37:24
 */
public class OdrzPasswordEncryptorTest extends TestCase {
    public void testDecrypt() throws Exception {
        OdrzConfigurationDecryptor encryptor = new OdrzConfigurationDecryptor();
        assertEquals(encryptor.decrypt("Dq7LbPmo2aen8RDRB9AxlPBVo3NAHzgq"),"iivanovic");
    }
}
