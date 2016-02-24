package com.dnocode.microsoft;

import com.dnocode.microsoft.domain.BingoScope;
import com.dnocode.microsoft.domain.BingoToken;
import com.dnocode.microsoft.net.Http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class B {
  private static final int oauthTimeout = 10000;
  private static final String oauthEndpoint = "/v2/OAuth2-13/";
  private static final String oauthServerName = "datamarket.accesscontrol.windows.net";
  private static final String oauthGrantType = "client_credentials";



  private   final String clientId ;
  private   final String clientSecret;
  private final Map<String,List<BingoToken>> scopeTokenMap=new HashMap<>();
  private final Http http=new Http();

  private final HashMap<String,String> paramsMap=new HashMap<String,String>(){{
    put("client_id", clientId);
    put("client_secret", clientSecret);
    put("grant_type", oauthGrantType);}};



  private static B INSTANCE;
  private B(String clientId,String clientSecret){
    this.clientId=clientId;
    this.clientSecret=clientSecret;
  }

  public static B auth(String clientId,String clientSecret){
    return  Optional.of(INSTANCE).orElse(new B(clientId,clientSecret));
  }


  public  BingoToken gimmiToken(BingoScope scope){ return gimmiToken(scope.val());  }
  public  BingoToken gimmiToken(String scope){
    //cerca in cache
    //http request
    //put in cache;
    return null;

  }

  private Optional<BingoToken> findInCache(String scope){  return Optional.empty();}

}