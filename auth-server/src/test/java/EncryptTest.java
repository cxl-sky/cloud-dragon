import com.dragon.authserver.AuthServerApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerApplication.class)
public class EncryptTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void encrypt() {
        String encryptStr = stringEncryptor.encrypt("123456");
        System.out.println(encryptStr);
    }

    @Test
    public void decrypt() {
        String encryptStr = stringEncryptor.decrypt("TqrnYZn55aFVwnSo2TrbFA==");
        System.out.println(encryptStr);
    }

}