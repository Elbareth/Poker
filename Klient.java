/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author izab
 */

public class Klient {
    private Socket socket = null;
    private BufferedReader buffor = null;
    private PrintWriter printer = null;
    private String [] karty = new String[5];
    private Integer stanKonta; 
    private Integer suma;
    private Integer numerUzytwkonika;
    private boolean grasz = true;
    private Wyglad wyglad = null;
    private Wyglad look = null;
    private Integer pula = null;
    private String nazwaUzytkownika = "Uzytkownik1";
    private Logger logger = Logger.getLogger("Logs");
    private FileHandler fileHandler;
    class InformacjePodstawowe{       
        private String wiadomosc = null;
        private String [] karty = new String[5];   
        private int stanKonta;        
        public void run(){ 
            logger.info("Otzrymuje podstawowe infomracje");
            try{
                int licznik = 0;
                while((wiadomosc = buffor.readLine()) != null){
                    if(licznik >= 0 && licznik <= 4){ // teraz serwer przesyla string z nazwami kart
                        karty[licznik] = wiadomosc;  // kart jest 5  
                        logger.info(wiadomosc);
                    }                    
                    if(licznik == 5){ 
                        wyglad = new Wyglad();
                        wyglad.setCoToZaKarta(karty); // mamy wszystkie karty wiec mozemy ustwic je na stole                      
                        stanKonta = Integer.parseInt(wiadomosc);
                        wyglad.setStanKontaLiczba(stanKonta); // dostajemy od serwera domyslny stan konta = 10 000                      
                    }
                    if(licznik==6){
                        suma = Integer.parseInt(wiadomosc);
                        wyglad.setSuma(suma); // dostajemy od serwera domyslna sume podstawowa = 0
                    }
                    if(licznik == 7){
                        numerUzytwkonika = Integer.parseInt(wiadomosc);
                        wyglad.setNumerUzytkownika(numerUzytwkonika); // otrzymuje swoj identyfikator
                        printer.println("Recv"); // wysylam informacje ze otrzymalam wszstkie startowe dane
                        printer.flush();
                        wyglad.look(); // tworze wyglad aplikacji
                        break;
                    }
                    licznik++; 
                    
                }  
                logger.info("Mam informacje na temat stanu konta "+stanKonta+" sumy początkowej "+suma+" i dostałam jeszcze numer uzytkownika "+numerUzytwkonika);
            }           
            catch(IOException e){
                System.out.println(e.toString());
                logger.info(e.toString());
            }                     
        }
        public void runda(){
            try{
                printer.println(numerUzytwkonika); // wysylam numer identyfikatora
                printer.flush();// na jego podstawie serwer okresla czy teraz wchodze do gry                
                while((wiadomosc = buffor.readLine()) != null){                   
                    if(wiadomosc.equals("Stop")){ // serwer okreslil, ze na razie musze poczekać                       
                       wyglad.runda(false); // nie moge nic kliknąć
                       wyglad.getSuma(); // ale moge zobaczyc co sie dzieje z suma
                       logger.info("Dostałam stop a mam numer "+numerUzytwkonika);
                    }
                    if(wiadomosc.equals("Go")){ // tutaj moge grac  
                        logger.info("Dostałam informacje żeby działac a mam numer "+numerUzytwkonika);
                        wyglad.runda(true); // znow moge klikac                                           
                        boolean sprawdzenie = false;
                        while(sprawdzenie != true){ // czekam na wiadomosc z widkou, czy juz jest gotowa
                            sprawdzenie = wyglad.getWiadomosc();                            
                            try{
                                Thread.sleep(500);                                 
                            }
                            catch(InterruptedException e){
                                System.out.println("Przerwany sen");
                                logger.info(e.toString());
                            }                                                                                    
                        }                                              
                        String all = wyglad.wiadomosc();                        
                        wyslij(all); // stanKona, wymagania, suma
                        logger.info(all);
                        if(!wyglad.wiadomosc().equals("koniec")){                            
                            wyglad.setWiadomosc(false);  // ustawiam ze wiadomosc nie jest gotowa
                        }                                               
                        wyglad.getSuma();
                    }
                    if( !wiadomosc.equals("Go") && !wiadomosc.equals("Stop") && !wiadomosc.equals("0") && !wiadomosc.equals("odslon") && !wiadomosc.equals("liczymy") && !wiadomosc.equals("Daj numer")){ // nie wiem czemu serwer moze wyslac w tym momencie 0                       
                       String [] tab = wiadomosc.split(",");
                       wyglad.setIleMuszeWydac(Integer.parseInt(tab[0]));
                       logger.info(tab[0]);
                       pula = Integer.parseInt(tab[1]);
                       wyglad.setSuma2(pula);
                       logger.info(pula.toString());
                    }
                    if(wiadomosc.equals("Daj numer")){
                        logger.info("Daj numer uzytkownika "+numerUzytwkonika);
                       printer.println(numerUzytwkonika); // uzytkowik wysyla swoj numer by serwer mogl powiedziec co dalej                       
                       printer.flush();
                       wyglad.getSuma(); 
                    }                    
                    if(wiadomosc.equals("odslon")){ // od 3 rundy moze pojawic sie taka wiadomosc
                        logger.info("Infomracja by odsłonić karty");
                        wyglad.setOdslonKarty();                         
                    }                   
                    if(wiadomosc.equals("liczymy")){ 
                        logger.info("Podliczamy punkty");
                        wyglad.licz();
                        printer.println(wyglad.wiadomosc());
                        printer.flush();                                              
                        while((wiadomosc = buffor.readLine()) == null){}                        
                        if(wiadomosc.equals(wyglad.wiadomosc())){
                            logger.info("Wygrana");
                            JOptionPane.showMessageDialog(null, "Gratulacje! Wygrałeś w tej rundzie!!!");                           
                            wygrana(); 
                            socket.close();
                        }
                        if(!wiadomosc.equals(wyglad.wiadomosc())){
                            logger.info("Przegrana");
                            JOptionPane.showMessageDialog(null, "Niestety przegrałeś w tej rundzie. Powodzenia następnym razem");                           
                            socket.close();
                        }
                    }                                       
                }
            }
            catch(IOException e){
                System.out.println(e.toString());
            }
        }
    }    
    public void polacz(){        
        try{
            fileHandler = new FileHandler("logs.txt");            
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            SimpleFormatter simple = new SimpleFormatter();
            fileHandler.setFormatter(simple);
           socket = new Socket("127.0.0.1",7000); // łączę się z serverem lokalnym działającym na porcie 7000 
           logger.info("Stworzono socket");
           InputStreamReader input = new InputStreamReader(socket.getInputStream());
           buffor = new BufferedReader(input);
           printer = new PrintWriter(socket.getOutputStream());
        }
        catch(IOException e){
            System.out.println(e.toString());
        }       
        InformacjePodstawowe info = new InformacjePodstawowe();
        info.run(); // dostajemy podstawowe informacje od serwera 
        info.runda(); // tutaj mamy runde gry
    } 
    public void wyslij(String message){ // metoda do wysyłaia wiadomości 
        printer.println(message);
        printer.flush();
        logger.info(message);
    }
    public void wygrana(){
        // Tu tworzymy dokument PDF = dyplom
        logger.info("Tworzenie dyplomu");
        try{
            PdfWriter writer = new PdfWriter("Dyplom.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            Image image = new Image(ImageDataFactory.create("obrazy/wygrana.jpg")).setWidth(75);
            Text tytul = new Text("Gratulacje!!").setFontSize(100)/*.setFontColor(Color.BLUE)*/;
            Paragraph p1 = new Paragraph(tytul);
            document.add(p1);
            document.add(image);
            Text tresc = new Text("Drogi uzytkowniku "+nazwaUzytkownika+" wygrales "+pula + " zlotych ").setFontSize(30);
            Paragraph p2 = new Paragraph(tresc);
            document.add(p2);
            document.close();            
        }
        catch(IOException e){
            System.out.println(e.toString());
        }
    }
}
