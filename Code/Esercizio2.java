
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author righi
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


class NodoLista{
    private String chiave;
    private NodoLista collegamento;
    public NodoLista(){
        collegamento = null;
        chiave = null;
    }
    public NodoLista(String chiave,NodoLista collegamento){
        this.chiave = chiave;
        this.collegamento = collegamento;
    }
    public String getChiave(){
        return chiave;
    }
    public void setCollegamento(NodoLista nuovoCollegamento){
        collegamento = nuovoCollegamento;
    }
    public NodoLista getCollegamento(){
        return collegamento;
    }
}

/**
 * Questa classe implementa una lista concatenata
 */
class ListaConcatenata{
    private NodoLista testa;
    public ListaConcatenata(){
        this.testa = null;
    }
    /**
     * Mostra i dati della lista
     */
    public void mostraLista(){
        NodoLista posizione = testa;
        while(posizione != null){
            System.out.println(posizione.getChiave());
            posizione = posizione.getCollegamento();
        }
    }
    /**
     * Restitusce il numero di nodi/lunghezza della lista
     */
    public int lunghezzaLista(){
        int conta = 0;
        NodoLista posizione = testa;
        while(posizione != null){
            conta++;
            posizione = posizione.getCollegamento();
        }
        return conta;
    }
    /**
     * Aggiunge all'inizio della lista un nodo contenente la nuova chiave
     */
    public void aggiungiNodo(String nuovaChiave){
        testa = new NodoLista(nuovaChiave,testa);
    }
    }

/**
 * Questa classe implementa una tabella hash
 */
class TabellaHash{
    private ListaConcatenata[] tabella;
    private int DIMENSIONE;
    public TabellaHash(){
        this.DIMENSIONE = 10;
        tabella = new ListaConcatenata[DIMENSIONE];
        for(int i=0;i<DIMENSIONE;i++){
            tabella[i] = new ListaConcatenata(); 
        }
    }
    public TabellaHash(int DIMENSIONE){
        this.DIMENSIONE = DIMENSIONE;
        tabella = new ListaConcatenata[DIMENSIONE];
        for(int i=0;i<DIMENSIONE;i++){
            tabella[i] = new ListaConcatenata(); 
        }
    }
    /**
     * Calcola il codice hash della chiave
     * @param chiave
     * @return 
     */
    public int calcolaHashCode(String chiave){
        double C = 0.15;
        int risultato = 0;
        int hash=0;
        for(int i=chiave.length()-1;i>=0;i--){
            hash =(int)(hash + (int)(chiave.charAt(chiave.length()-(i+1)))*Math.pow(256,i));
        }
        double hashC = hash*C;
        double resto = hashC - (int)hashC;
        risultato= (int)(DIMENSIONE*resto);
        return risultato;
    }
    /**
     * Aggiunge la chiave alla lista di trabocco
     * @param chiave 
     */
    public void aggiungiATabella(String chiave){
        int hash = calcolaHashCode(chiave);
        ListaConcatenata lista = tabella[hash];
        lista.aggiungiNodo(chiave);
    }
    public ListaConcatenata getListaDiTrabocco(int i){
        return tabella[i];
    }
}

public class Esercizio2 {
    public static void main(String[] args){
        int numeroRighe=0;
		int lunghezzaLista = 0;
        String nomeFile = args[0];
		//System.out.println(nomeFile);
		if(nomeFile.equals("Input1Es2.txt") || nomeFile.equals("Input2Es2.txt")){
			lunghezzaLista = 16;
			//System.out.println("16");
		}
		else{
			lunghezzaLista = 32;
			//System.out.println("32");
		}
        Scanner scanner = null;
		TabellaHash h1 = new TabellaHash(lunghezzaLista);
        try{
            scanner = new Scanner(new File(nomeFile));
        }catch(FileNotFoundException e){
            System.out.println("Errore nella lettura del file.");
            System.exit(0);
        }
        while(scanner.hasNextLine()){
            String riga = scanner.nextLine();
            numeroRighe ++;
            h1.aggiungiATabella(riga);
        }
        scanner.close();
        
		
		String nomeFile2 = args[1];
                PrintWriter scrittura = null;
                try{
                    scrittura = new PrintWriter(nomeFile2);
                }catch(FileNotFoundException e){
                    System.out.println("Errore nell'apertura del file.");
                    System.exit(0);
                }
               for (int i=0;i<lunghezzaLista;i++){
				   ListaConcatenata appoggio2 = h1.getListaDiTrabocco(i);
					scrittura.println("Lista "+(i+1)+": "+appoggio2.lunghezzaLista());
				}
        scrittura.close();
        
        
       
    }
   
}
        
