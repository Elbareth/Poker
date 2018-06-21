/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Na srodku bedzie cala pola wybranych
// z boku ile ostatnio bylo postawione
// nazwiska innych graczy, pogrubienie gdy czyjas kolej
package poker;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author izab
 */
public class Wyglad extends JFrame{
    private String [] coToZaKarta = new String[5];    
    private Integer stanKontaLiczba;
    private JButton pass = null;
    private JButton wyrownaj = null;
    private JButton przebij = null;
    private JButton odslonKarty = null;
    private JTextField ilePrzebij = null;
    private JButton karta1 = null;
    private JButton karta2 = null;
    private JButton karta3 = null;
    private JButton karta4 = null;
    private JButton karta5 = null;
    private JLabel napis = null;
    private JTextField stanKonta = null;
    private JButton zeton5 = null;
    private JButton zeton10 = null;
    private JButton zeton50 = null;
    private JButton zeton100 = null;
    private JButton zeton500 = null;
    private JButton zeton1000 = null;
    private JTextField sumka = null;         
    private Integer ileMuszeWydac = 5;
    private Integer nadwyzka = 0;
    private Integer suma;
    private Integer numerUzytkownika;
    private String wiadomosc;
    private boolean wiadomoscWyslana = false;    
    public void look(){ // funkcja tworzaca wyglad
        setTitle("Poker"); // nadaje tytul
        setSize(800,600); // wymiary
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // co sie stanie gdy zamkne okienko
        setResizable(false);// uniemozliwia zmiane rozmiaru okna.
        try{
            setIconImage(ImageIO.read(new File("obrazy/icon.jpg"))); // ustawiam ikone
        }
        catch(IOException e){
            System.out.println(e.toString());
        }
        JLabel background=new JLabel(new ImageIcon("obrazy/stol.jpg")); // ustawiam tlo
        add(background); 
        //Od tego fragmentu dodaje w okreslonym miejscu przyciski i akcje do nich
        pass = new JButton("Spasuj");
        pass.setBounds(60, 300, 120, 30);
        pass.addActionListener(new GameOver());
        background.add(pass);
        wyrownaj = new JButton("Wyrównaj");
        wyrownaj.setBounds(60, 333, 120, 30);
        wyrownaj.addActionListener(new Dobij());
        background.add(wyrownaj);
        przebij = new JButton("Przebij o:");
        przebij.setBounds(60,366,120,30);
        przebij.addActionListener(new Przebij());
        background.add(przebij);
        ilePrzebij = new JTextField("");
        ilePrzebij.setBounds(180,366,30,30);
        ilePrzebij.setBackground(new Color(255,255,255));        
        background.add(ilePrzebij);
        odslonKarty = new JButton("Odsłoń karty");
        odslonKarty.setBounds(60,450,150,30);
        odslonKarty.setEnabled(false);
        odslonKarty.addActionListener(new Koniec());
        background.add(odslonKarty); 
        karta1 = new JButton();
        karta2 = new JButton();
        karta3 = new JButton();
        karta4 = new JButton();
        karta5 = new JButton();
        zeton5 = new JButton();
        zeton10 = new JButton();
        zeton50 = new JButton();
        zeton100 = new JButton();
        zeton500 = new JButton();
        zeton1000 = new JButton();
        Image kar1 = null;
        Image kar2 = null;
        Image kar3 = null;
        Image kar4 = null;
        Image kar5 = null;
        Image zet5 = null;
        Image zet10 = null;
        Image zet50 = null;
        Image zet100 = null;
        Image zet500 = null;
        Image zet1000 = null;
        try{            
            kar1 = ImageIO.read(new File("obrazy/"+coToZaKarta[0]));
            kar2 = ImageIO.read(new File("obrazy/"+coToZaKarta[1]));
            kar3 = ImageIO.read(new File("obrazy/"+coToZaKarta[2]));
            kar4 = ImageIO.read(new File("obrazy/"+coToZaKarta[3]));
            kar5 = ImageIO.read(new File("obrazy/"+coToZaKarta[4]));
            zet5 = ImageIO.read(new File("obrazy/5zeton.png"));
            zet10 = ImageIO.read(new File("obrazy/10zeton.png"));
            zet50 = ImageIO.read(new File("obrazy/50zeton.png"));
            zet100 = ImageIO.read(new File("obrazy/100zeton.png"));
            zet500 = ImageIO.read(new File("obrazy/500zeton.png"));
            zet1000 = ImageIO.read(new File("obrazy/1000zeton.png"));
        }
        catch(IOException e){
            System.out.println(e.toString());
        }
        // Tutaj dodaje przyciski, ktore maja jakies ikony na sobie
        karta1.setIcon(new ImageIcon(kar1));
        karta1.setBounds(250,300,75,100);
        background.add(karta1);
        karta2.setIcon(new ImageIcon(kar2));
        karta2.setBounds(330,300,75,100);
        background.add(karta2);
        karta3.setIcon(new ImageIcon(kar3));
        karta3.setBounds(410,300,75,100);
        background.add(karta3);
        karta4.setIcon(new ImageIcon(kar4));
        karta4.setBounds(490,300,75,100);
        background.add(karta4);
        karta5.setIcon(new ImageIcon(kar5));
        karta5.setBounds(570,300,75,100);
        background.add(karta5);
        napis = new JLabel("<html>Twój aktualny <br/> stan  konta <br/> to: </html>");
        napis.setForeground(Color.WHITE);
        napis.setBounds(650,300,150,50);
        background.add(napis);
        stanKonta = new JTextField(stanKontaLiczba.toString());
        stanKonta.setEnabled(false);
        stanKonta.setBounds(650, 350, 50, 30);
        stanKonta.setDisabledTextColor(Color.black);
        background.add(stanKonta);
        zeton5.setIcon(new ImageIcon(zet5));
        zeton5.setBounds(250, 430, 50, 50);       
        zeton5.setContentAreaFilled(false);// ta funkcja jest po to by ikony mogly byc okragle  
        zeton5.addActionListener(new Minus5());
        background.add(zeton5); 
        zeton10.setIcon(new ImageIcon(zet10));
        zeton10.setBounds(310,430,50,50);
        zeton10.setContentAreaFilled(false);
        zeton10.addActionListener(new Minus10());
        background.add(zeton10);
        zeton50.setIcon(new ImageIcon(zet50));
        zeton50.setBounds(370,430,50,50);
        zeton50.setContentAreaFilled(false);
        zeton50.addActionListener(new Minus50());
        background.add(zeton50);
        zeton100.setIcon(new ImageIcon(zet100));
        zeton100.setBounds(430,430,50,50);
        zeton100.setContentAreaFilled(false);
        zeton100.addActionListener(new Minus100());
        background.add(zeton100);
        zeton500.setIcon(new ImageIcon(zet500));
        zeton500.setBounds(490,430,50,50);
        zeton500.setContentAreaFilled(false);
        zeton500.addActionListener(new Minus500());
        background.add(zeton500);
        zeton1000.setIcon(new ImageIcon(zet1000));
        zeton1000.setBounds(550, 430, 50, 50);
        zeton1000.setContentAreaFilled(false);
        zeton1000.addActionListener(new Minus1000());
        background.add(zeton1000);
        sumka = new JTextField(suma.toString());
        sumka.setBounds(380,150,40,40);
        sumka.setEnabled(false);
        sumka.setDisabledTextColor(Color.black);
        background.add(sumka);        
        setVisible(true); // widok staje sie widoczny
    }
    public Wyglad(){            
    }    
    public void pomoc(int x){ //funkcja pomocnicza sluzy do obliczania stanu konta, sumy, nadwyzki itp. I wysla to do serwera przez klienta
        Integer sumaTmp = 0;             
        if(stanKontaLiczba >= x){ // jesli mam wystarczajaca ilosc pieiedzy na koncie to pobieram z niego           
            stanKontaLiczba = stanKontaLiczba - x;
            stanKonta.setText(stanKontaLiczba.toString());// ustawiam obecny stan konta            
            if(ileMuszeWydac == 0){ // nie musze nic dodatkowo wydawac a jednak chce                
                nadwyzka = nadwyzka + x; // tworzy sie nadwyzka ktora kolejni uzytkownicy beda musieli pokryc
                sumaTmp = sumaTmp + nadwyzka; // nadwyzka jest pobierana z mojego konta wiec jest dodawana do sumy ogolnej                
            }
            if(ileMuszeWydac > 0){   // musze cos jednak wydac by grac dalej               
                sumaTmp = sumaTmp + ileMuszeWydac; // ta kwotna pobierana jest z mojego konta i laduje na sumie ogolnej
                ileMuszeWydac = ileMuszeWydac - x;
                if(ileMuszeWydac < 0 ){
                    nadwyzka = ileMuszeWydac * (-1); // jesli wydalam za duzo to to bedzie nadwyzka
                    sumaTmp = sumaTmp + nadwyzka; // zostala pobrana z mojego konta i laduje na sumie ogolnej                    
                }
                if(ileMuszeWydac > 0){
                    JOptionPane.showMessageDialog(null, "Musisz wydać jeszcze "+ileMuszeWydac); // informuje ile musze jeszcze wylozyc na stol
                }
            }
                        
        }       
        if(ileMuszeWydac <= 0){ // juz wydalam tyle ile bylo trzeba
            ileMuszeWydac = 0;
            wiadomosc = stanKontaLiczba.toString()+","+nadwyzka.toString()+","+sumaTmp.toString(); // tworze wiadomosc            
            wiadomoscWyslana = true; // informuje ze wiadomosc zostala stworzona            
        }
    }
    // serja takich samych funkcji. Odpowiadaja za sterowanie przyciskami. Różnia się tylko
    // kwotą przelewaną z konta
    class Minus5 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pomoc(5);                      
        }       
    }
    class Minus10 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pomoc(10);            
        }       
    }
    class Minus50 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pomoc(50);            
        }       
    }
    class Minus100 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pomoc(100);   
        }
    }
    class Minus500 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pomoc(500);          
        }       
    }
    class Minus1000 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pomoc(1000);           
        }       
    } 
    // Ta funkcja odpowiada za poddanie się 
    class GameOver implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {            
            JOptionPane.showMessageDialog(null,"Poddałeś się w tej rundzie");            
            wiadomosc = "pass"; // serwer otrzymuje odpowiednią wiadomosc
            wiadomoscWyslana = true;
        }      
    }
    // Przelewamy ze swojego konta tyle ile uzytkownicy przed nami
    class Dobij implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           pomoc(ileMuszeWydac);            
        }        
    }
    // Jesli uważamy, ze nasze karty są mocne to mozemy zwiększyć stawianą stawkę
    // Jeśli damy więcej - zmusimy tym samym innych uzytkowników by dali więcej
    // Więc jeśli faktycznie mamy lepsze karty to dostaniemy duzo - chyba ,że 
    // po dordze wszyscy sie poddali to wtedy mniej ;)
    class Przebij implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {           
            String tmp = ilePrzebij.getText(); // dodatkowa suma ktora chce postawic            
            int liczba = 0;           
            try{
                liczba = Integer.parseInt(tmp);               
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Wprowadź poprawną liczbę ");
                ilePrzebij.setText("");
            }           
            if(liczba != 0){
                int sumaAll = ileMuszeWydac + liczba; // obowiazkowa kwota
                ilePrzebij.setText("");
                pomoc(sumaAll); // pobieram ze swojego konta kwote obowiazkowa i jeszcze 
                // to co sama dobrowalone dałam
            }
        }        
    }
    // Ta funkcja odpowiada za koniec gry. Odsłaniam karty. Teraz wszyscy muszą je
    // odslonic i przeliczyc wartosc kart by powiedziec o tym serwerowi
    // na tej podstawie serwer wyloni zwycięsce 
    class Koniec implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){            
            wiadomosc = "koniec"; 
            wiadomoscWyslana = true;           
        }
    }
    public Integer licz(){
        Karty k = new Karty();
        Integer wartosc = k.przelicz(coToZaKarta);
        wiadomosc = wartosc.toString();     
        return wartosc;
    }
    // W zaleznosci blokuje lub odblokowuje ruchy
    public void runda(boolean coRobic){        
        if(coRobic == false){
            zeton5.setEnabled(false);
            zeton10.setEnabled(false);
            zeton50.setEnabled(false);
            zeton100.setEnabled(false);
            zeton500.setEnabled(false);
            zeton1000.setEnabled(false);
            pass.setEnabled(false);
            wyrownaj.setEnabled(false);
            przebij.setEnabled(false);
        }
        else{
           zeton5.setEnabled(true);
           zeton10.setEnabled(true);
           zeton50.setEnabled(true);
           zeton100.setEnabled(true);
           zeton500.setEnabled(true);
           zeton1000.setEnabled(true);
           pass.setEnabled(true);
           wyrownaj.setEnabled(true);
           przebij.setEnabled(true); 
        }
    }
    // seria getterów i setterów żeby moc komunikowac sie z kilentem -> serwerem
    public void setIleMuszeWydac(int ileMuszeWydac){
        this.ileMuszeWydac = ileMuszeWydac;
    }
    public void setOdslonKarty(){
        odslonKarty.setEnabled(true);
    }
    public void setCoToZaKarta(String [] coToZaKarta){
        this.coToZaKarta = coToZaKarta;
    }
    public void setStanKontaLiczba(int stanKontaLiczba){
        this.stanKontaLiczba = stanKontaLiczba;
    }
    public int getSuma(){
        sumka.setText(suma.toString());
        return suma;
    }
    public void setSuma(Integer suma){
        this.suma=suma;        
    }
    public void setSuma2(Integer suma){
        this.suma=suma;        
        sumka.setText(this.suma.toString());
    }
    public void setNumerUzytkownika(int numerUzytkownika){
        this.numerUzytkownika=numerUzytkownika;
    }
    public String wiadomosc(){
        return wiadomosc;
    }
    public boolean getWiadomosc(){
        return wiadomoscWyslana;
    }
    public void setWiadomosc(boolean wiadomoscWyslana){
        this.wiadomoscWyslana = wiadomoscWyslana;
    }    
}
