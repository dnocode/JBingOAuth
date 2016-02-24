package com.dnocode.microsoft;

import com.dnocode.jhug.net.Http;
import com.dnocode.microsoft.domain.BingoScope;
import com.dnocode.microsoft.domain.BingoToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Bing {

  private static final String oauthEndPoint = "https://datamarket.accesscontrol.windows.net/v2/OAuth2-13";
  private static final String oauthGrantType = "client_credentials";
  private final Http http=new Http();
  private final Map<String,BingoToken> cache =new HashMap();
  private static Bing INSTANCE;
  private final ConcurrentHashMap<String,String> paramsMap=new ConcurrentHashMap<String,String>(){{put("grant_type", oauthGrantType);}};

  private Bing(String clientId, String clientSecret){
   try {
     paramsMap.put("client_id", URLEncoder.encode(clientId, StandardCharsets.UTF_8.displayName()));
     paramsMap.put("client_secret", URLEncoder.encode(clientSecret, StandardCharsets.UTF_8.displayName()));
   }catch (UnsupportedEncodingException exc){
     exc.printStackTrace();
   }
  }

  public synchronized static Bing auth(String ... clientIdAndSecretId){
   try {


     return INSTANCE = Optional.ofNullable(INSTANCE).orElseGet(()->new Bing(clientIdAndSecretId[0], clientIdAndSecretId[1]));
   }catch (IndexOutOfBoundsException e){
     System.out.print("remember clientid and secretId");
     return null;
   }
  }
  public  BingoToken token(BingoScope scope){ return token(scope.val());}
  /**
   * find or request for token
   * @param scope
   * @return
     */
  public synchronized BingoToken token(String scope){
    paramsMap.put("scope",scope);
    return findInCache(scope)
           .orElseGet(()->http
                    .postAsJson(oauthEndPoint, paramsMap, BingoToken.class)
                    .map(this::putTokenInCache).get());
  }


  /**
   * find token in cache
   * and remove it if expired
   * @param scope
   * @return
     */
  private Optional<BingoToken> findInCache(String scope) {
    return Optional
            .ofNullable(cache
                    .computeIfPresent(scope, (key, token) -> token.isExpired() ? (cache.remove(key).EXPIRED) : token));
  }


  private BingoToken putTokenInCache(BingoToken newToken){
    return cache.compute(newToken.scope, (k, oldtoken)->newToken);
  }

}