# JBingOAuth
Microsoft OAuth SDK

simple client to obtain token using client credentials flow through Bing oauth 2


    singleton creation require client and secret as parameters

     BingoToken token=Bing
                  .auth(urClientId,urSecretId)
                    .token(BingoScope.bingTranslator);

     BingoToken token2=Bing
                       .auth()
                         .token(BingoScope.bingTranslator);