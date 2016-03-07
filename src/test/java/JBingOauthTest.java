import com.dnocode.microsoft.Bing;
import com.dnocode.microsoft.domain.BingoScope;
import com.dnocode.microsoft.domain.BingoToken;
import junit.framework.TestCase;
import rx.Observable;

import java.util.Optional;

/**
 * Created by dino on 24/02/16.
 */
public class JBingOauthTest extends TestCase {


    private final String urClientId="x";
    private final String urSecretId="y";

    // test method to add two values
    public void testClient(){

       BingoToken token = Bing
                .auth(urClientId, urSecretId)
                .token(BingoScope.bingTranslator)
                .toBlocking().first();

       BingoToken token2= Bing
                .auth(urClientId, urSecretId)
                .token(BingoScope.bingTranslator)
                .toBlocking().first();



        System.out.print("token:"+token.accessToken);







    }
}
