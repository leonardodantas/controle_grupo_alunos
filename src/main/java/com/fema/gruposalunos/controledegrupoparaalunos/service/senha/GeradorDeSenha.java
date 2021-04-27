package com.fema.gruposalunos.controledegrupoparaalunos.service.senha;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GeradorDeSenha {

    public static int TAMANH0 = 10;

    public static String getRandomPass(){
        char[] chart ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        char[] senha= new char[TAMANH0];

        int chartLenght = chart.length;
        Random rdm = new Random();

        for (int x=0; x<TAMANH0; x++)
            senha[x] = chart[rdm.nextInt(chartLenght)];

        return new String(senha);
    }
}
