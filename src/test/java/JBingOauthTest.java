import com.dnocode.microsoft.Bing;
import com.dnocode.microsoft.domain.BingoScope;
import com.dnocode.microsoft.domain.BingoToken;
import junit.framework.TestCase;

/**
 * Created by dino on 24/02/16.
 */
public class JBingOauthTest extends TestCase {


    private final String urClientId="X";
    private final String urSecretId="Y";

    // test method to add two values
    public void testClient(){

        BingoToken token=Bing
                .auth(urClientId,urSecretId)
                  .gimmiToken(BingoScope.bingTranslator);

        assertTrue(token.isExpired()==false );
    }
}
