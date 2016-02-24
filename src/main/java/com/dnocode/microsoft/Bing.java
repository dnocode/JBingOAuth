package com.dnocode.microsoft;

import com.dnocode.microsoft.domain.BingoScope;
import com.dnocode.microsoft.domain.BingoToken;
import com.dnocode.microsoft.net.Http;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Bing {
  private static final int oauthTimeout = 10000;
  private static final String oauthEndpoint = "/v2/OAuth2-13/";
  private static final String oauthServerName = "datamarket.accesscontrol.windows.net";
  private static final String oauthGrantType = "client_credentials";
  private final Http http=new Http();
  private final Map<String,BingoToken> scopeTokenMap=new HashMap();


  private static Bing INSTANCE;


  private final HashMap<String,String> paramsMap=new HashMap<String,String>(){{put("grant_type", oauthGrantType);}};

  private Bing(String clientId, String clientSecret){
    paramsMap.put("client_id", clientId);
    paramsMap.put("client_secret", clientSecret);
  }

  public static Bing auth(String clientId, String clientSecret){
    return  INSTANCE=Optional.ofNullable(INSTANCE).orElse(new Bing(clientId,clientSecret));
  }

  public  BingoToken gimmiToken(BingoScope scope){ return gimmiToken(scope.val());  }

  /**
   * find or request for token
   * @param scope
   * @return
     */
  public  BingoToken gimmiToken(String scope){

    return findInCache(scope)
            .orElse(http
                    .postAsJson(oauthServerName.concat(oauthEndpoint),paramsMap,BingoToken.class)
                    .map(token->scopeTokenMap.put(token.scope,token))
                    .get());
  }


  /**
   * find token in cache
   * and remove it if expired
   * @param scope
   * @return
     */
  private Optional<BingoToken> findInCache(String scope) {
    return Optional
            .ofNullable(scopeTokenMap
                    .computeIfPresent(scope, (key, token) -> token.isExpired() ? scopeTokenMap.remove(key).EXPIRED : token));
  }


}