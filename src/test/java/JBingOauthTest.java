import com.dnocode.microsoft.Bing;
import com.dnocode.microsoft.domain.BingoScope;
import com.dnocode.microsoft.domain.BingoToken;
import junit.framework.TestCase;

/**
 * Created by dino on 24/02/16.
 */
public class JBingOauthTest extends TestCase {


    // test method to add two values
    public void testAdd(){


        BingoToken token=Bing
                .auth("ar-dev","0fKIk+qYwfQPbuIk0rv0nm7aUS+MUI7t2gGQlo1Zko0=")
                .gimmiToken(BingoScope.bingTranslator);


        assertTrue(true );
    }
}
