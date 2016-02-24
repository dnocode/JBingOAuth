package com.dnocode.microsoft;

import com.dnocode.microsoft.domain.BingoScope;
import com.dnocode.microsoft.domain.BingoToken;

/**
 * Created by dino on 24/02/16.
 */
public class prova {

    public static void main (String [] args){


        BingoToken token=Bing
                .auth("ar-dev","0fKIk+qYwfQPbuIk0rv0nm7aUS+MUI7t2gGQlo1Zko0=")
                .gimmiToken(BingoScope.bingTranslator);


    }
}
