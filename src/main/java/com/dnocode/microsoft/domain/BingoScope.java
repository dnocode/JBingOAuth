package com.dnocode.microsoft.domain;

/**
 * Created by dino on 24/02/16.
 */
public enum BingoScope {
    bingTranslator("http://api.microsofttranslator.com");
    private String val;

    BingoScope(String val) {
        this.val = val;
    }



    public String val(){return this.val;}



}
