/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author izab
 */

public class Karty {
    private String [][] karty = new String [4][13];
    private boolean [][] czyWybrana = new boolean [4][13];
    public Karty(){
       for(int i=0;i<4;i++){
           for(int k=0;k<13;k++){               
               czyWybrana[i][k] = false;
           }
       } 
       for(int i=0;i<4;i++){
           for(int k=0;k<13;k++){
               if(k==0) karty[i][k]="2";
               if(k==1) karty[i][k]="3";
               if(k==2) karty[i][k]="4";
               if(k==3) karty[i][k]="5";
               if(k==4) karty[i][k]="6";
               if(k==5) karty[i][k]="7";
               if(k==6) karty[i][k]="8";
               if(k==7) karty[i][k]="9";
               if(k==8) karty[i][k]="10";
               if(k==9) karty[i][k]="jopek";
               if(k==10) karty[i][k]="krolowa";
               if(k==11) karty[i][k]="krol";
               if(k==12) karty[i][k]="as";
               if(i==0) karty[i][k]=karty[i][k]+"pik.jpg";
               if(i==1) karty[i][k]=karty[i][k]+"kier.jpg";
               if(i==2) karty[i][k]=karty[i][k]+"karo.jpg";
               if(i==3) karty[i][k]=karty[i][k]+"trefl.jpg";
           }
       }       
    }    
    public String wartosc(int i, int k){
        return karty[i][k];
    }
    public int [][] jakaToKarta(String [] karta){
        int [][] tmp = new int [2][5];
        for(int l=0;l<5;l++){
            for(int i=0;i<4;i++){
                for(int k=0;k<13;k++){
                    if(karty[i][k].equals(karta[l])){
                        tmp[0][l] = i; // kolor
                        tmp[1][l] = k; // wartosc
                    }
                }
            }
        }
        return tmp;
    }
    public int licz(String [] karta){
        int tmp = 0;
        for(int l=0;l<5;l++){
            for(int i=0;i<4;i++){
                for(int k=0;k<13;k++){
                    if(karty[i][k].equals(karta[l])){
                        tmp = tmp + k;
                    }
                }
            }
        }
        return tmp;
    }    
    public boolean pokerKrolewski(int [][] tmp){
        boolean prawda = true;       
        for(int k=1;k<5;k++){
            if(tmp[0][k] != tmp[0][k-1]){ // ten sam kolor
                prawda = false;
            }
            if(tmp[1][k] != 12 && tmp[1][k] != 11 && tmp[1][k] != 10 && tmp [1][k] != 9 && tmp[1][k] != 8){
                prawda = false;
            }            
        } 
        return prawda;
    }
    public boolean poker(int [][] tmp){
        boolean prawda = true;
        List a = new ArrayList();        
        for(int k=1;k<5;k++){
            if(tmp[0][k] != tmp[0][k-1]){ // ten sam kolor
                prawda = false;
            }                         
        } 
        for(int i=0;i<5;i++){
            a.add(tmp[1][i]);  // dodajemy wartosci do listy
        }
        Collections.sort(a);
        for(int i=1;i<5;i++){
            if((Integer.parseInt(a.get(i).toString())-Integer.parseInt(a.get(i).toString()))!=1){
                prawda = false;
            }
        }
        return prawda;
    }
    public boolean kareta(int [][] tmp){
        // nie zwracam uwagi na kolor
        boolean prawda = false;
        int licznik = 1;
        for(int i=1;i<5;i++){
            if(tmp[1][i] == tmp[1][i-1]){
                licznik++;
            }
        }
        if(licznik == 4){
            prawda = true;
        }
        return prawda;
    }
    public boolean full(int [][] tmp){
        //nie zwracam uwagi na kolor
        // 3 karty mają tę samą wartość a dwie pozostałe są parą
        int licznikTrojka = 1;
        int licznikPara = 1;
        boolean prawda = false;
        List a = new ArrayList();
        for(int i=0;i<5;i++){
            a.add(tmp[1][i]);
        }
        Collections.sort(a);
        for(int i=1;i<4;i++){
            if(a.get(i) == a.get(i-1)){
                licznikTrojka++;
            }
            if(a.get(i) != a.get(i-1)){
                if(a.get(i) == a.get(i+1)){
                    licznikPara++;
                }
            }
        }
        if(licznikPara == 3 || licznikTrojka == 3){           
            if(licznikPara == 2 || licznikTrojka == 2){
                prawda = true;
            }
        }        
        return prawda;
    }
    public boolean kolor(int [][] tmp){
        boolean prawda = true;
        for(int i=1;i<5;i++){
            if(tmp[0][i] != tmp[0][i-1]){
                prawda = false;
            }
        }
        return prawda;
    }
    public boolean strit(int [][] tmp){
        boolean prawda = true;
        List a = new ArrayList();
        for(int i=0;i<5;i++){
            a.add(tmp[1][i]);
        }
        Collections.sort(a);
        for(int i=1;i<5;i++){
            if(Integer.parseInt(a.get(i).toString())-Integer.parseInt(a.get(i-1).toString())!=1){
                prawda = false;
            }
        }
        return prawda;
    }
    public boolean trojka(int [][] tmp){
        boolean prawda = false;
        int licznik = 1;
        List a = new ArrayList();
        for(int i=0;i<5;i++){
            a.add(tmp[1][i]);            
        }
        Collections.sort(a);
        for(int i=1;i<5;i++){
            if(a.get(i) == a.get(i-1)){
                licznik++;
            }
        }
        if(licznik == 3){
            prawda = true;
        }
        return prawda;
    }
    public boolean dwiePary(int [][] tmp){
        boolean prawda = false;
        int licznik1 = 1;
        int licznik2 = 1;
        List a = new ArrayList();
        for(int i=0;i<5;i++){
            a.add(tmp[1][i]);
        }
        Collections.sort(a);
        for(int i=1;i<4;i++){
            if(a.get(i) == a.get(i-1)){
                licznik1++;
            }
            if(a.get(i) != a.get(i-1)){
                if(a.get(i) == a.get(i+1)){
                    licznik1++;
                }
            }
        }
        if(licznik1 == 4){
            prawda = true;
        }
        return prawda;
    }
    public boolean para(int [][] tmp){
        boolean prawda = false;
        int licznik = 1;
        List a = new ArrayList();
        for(int i=0;i<5;i++){
           a.add(tmp[1][i]); 
        }
        Collections.sort(a);
        for(int i=1;i<5;i++){
            if(a.get(i) == a.get(i-1)){
                licznik++;
            }
        }
        if(licznik == 2){
            prawda = true;
        }
        return prawda;
    }
    public Integer przelicz(String [] karty){
        //najwyzsza karta to poprostu dajemy maxa z kart
        if(pokerKrolewski(jakaToKarta(karty))){            
            return 10000; // nie maja znaczenia wartosci
        }
        if(poker(jakaToKarta(karty))){          
            return 9 * licz(karty);
        }
        if(kareta(jakaToKarta(karty))){            
            return 8*licz(karty);
        }
        if(full(jakaToKarta(karty))){            
            return 7*licz(karty);
        }
        if(kolor(jakaToKarta(karty))){       
            return 6*licz(karty);
        }
        if(strit(jakaToKarta(karty))){            
            return 5*licz(karty);
        }
        if(trojka(jakaToKarta(karty))){            
            return 4*licz(karty);
        }
        if(dwiePary(jakaToKarta(karty))){            
            return 3*licz(karty);
        }
        if(para(jakaToKarta(karty))){            
            return 2*licz(karty);
        }              
        int max = 0;
        for(int l=0;l<5;l++){
            for(int i=0;i<4;i++){
                for(int k=0;k<13;k++){
                    if(this.karty[i][k].equals(karty[l])){                        
                        if(max<k){
                            max = k;
                        }
                    }
                }
            }
        }
        return max;
    }
}
