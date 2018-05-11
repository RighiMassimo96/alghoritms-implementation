/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author righi
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.*;

/*
In un ABR la complessità delle singole operazioni di inserimento,ricerca e cancellazione è sempre O(h),dove h è l'altezza dell'albero.
Purtroppo,h è O(n) nel caso pessimo, perchè l'albero può allungarsi inserendo e cancellando nodi.
 */

/**
 *
 * @author righi
 */

/**
 * La classe @Luogo viene utilizzata per implementare la lista di laboratori in 
 * cui è presente l'attrezzatura.
 */
class Luogo{
    private String luogo;
    private int quantita;
    public Luogo(String luogo){
        this.luogo = luogo;
        this.quantita = 1;
    }
    public String getLuogo(){
        return luogo;
    }
    public int getQuantita(){
        return quantita;
    }
    public void aumentaQuantita(){
        this.quantita = quantita+1;
    }
    public void diminuisciQuantita(){
        this.quantita = quantita-1;
    }
}
/**
 * Ogni @Attrezzatura viene gestita come un nodo dell'albero.
 */
class Attrezzatura{
    private ArrayList<Luogo> lista2 = new ArrayList();   // lista di laboratori in cui è presente l'attrezzatura
	private String codice;      // codice alfanumerico che identifica l'attrezzatura
	private Attrezzatura left;      
	private Attrezzatura right;
        public static int confronti=0;
	public Attrezzatura(String codice){
		this.codice = codice;
		this.left = null;
		this.right = null;
	}
        public String getCodice(){
            return codice;
        }
        public Attrezzatura getLeft(){
            return left;
        }
        public Attrezzatura getRight(){
            return right;
        }
        public void setLeft(Attrezzatura nuovoCollegamento){
            this.left = nuovoCollegamento;
        }
        public void setRight(Attrezzatura nuovoCollegamento){
            this.right = nuovoCollegamento;
        }
        /**
         * Aggiunge una nuova attrezzatura in uno specifico laboratorio.
         * @param luogo 
         */
        public void addToLista(String luogo){
            if(!lista2.isEmpty()){
                boolean aggiunto = false;
                for(int i=0;i<lista2.size();i++){
                    confronti++;
                    if(lista2.get(i).getLuogo().equalsIgnoreCase(luogo)){
                        lista2.get(i).aumentaQuantita();
                        aggiunto = true;
                        break;
                    } 
                }
                if(aggiunto == false){
                    //confronti++;
                    Luogo luogoDaAggiungere = new Luogo(luogo);
                    lista2.add(luogoDaAggiungere);
                }
            }
            else{
                //confronti++;
                Luogo luogoDaAggiungere = new Luogo(luogo);
                lista2.add(luogoDaAggiungere);
            }
        }
        /**
         * Elimina un attrezzatura da uno specifico laboratorio.
         * Se l'attrezzatura non è più presente nel laboratorio, il laboratorio
         * viene eliminato dalla lista.
         * Restituisce true se l'attrezzatura è stata rimossa con successo,
         * altrimenti false.
         * @param luogo
         * @return 
         */
        public boolean deleteToLista(String luogo){
            boolean eliminato = false;
            if(!lista2.isEmpty()){
                for(int i=0;i<lista2.size();i++){
                    confronti++;
                    if(lista2.get(i).getLuogo().equalsIgnoreCase(luogo)){
                        lista2.get(i).diminuisciQuantita();
                        confronti++;
                        if(lista2.get(i).getQuantita()==0){
                            Luogo rimosso=lista2.remove(i);
                        }
                        eliminato = true;
                        return eliminato;
                    } 
                }
            }
            return eliminato;
        }
        /**
         * Cerca se l'attrezzatura è presente in uno specifico laboratorio.
         * Se l'attrezzatura è presente, ritorna il numero di esemplari.
         * Restituisce true se l'attrezzatura è stata trovata, altrimenti false.
         * @param luogo
         * @return 
         */
        public int findInLista(String luogo){
            boolean trovato = false;
            if(!lista2.isEmpty()){
                for(int i=0;i<lista2.size();i++){
                    confronti++;
                    if(lista2.get(i).getLuogo().equalsIgnoreCase(luogo)){
                        trovato=true;
                        return lista2.get(i).getQuantita();
                    }
                }
            }
            return -1;
        }
        public ArrayList getLista(){
            return lista2;
        }
}

class BinarySearchTree {
	private  Attrezzatura radice;
    public static int confronti2=0;
	public BinarySearchTree(){
		this.radice = null;
	}
        
    public int treeHeight(Attrezzatura root){
		if(root==null)return 0;
		return (1+ Math.max(treeHeight(root.getLeft()),treeHeight(root.getRight())));
	}
        
        
	/**
    * Cerca un attrezzatura all'interno dell'albero.
    * Se l'attrezzatura è presente ritorna il numero di esemplari presenti nello specifico laboratorio.
	* Se invece non viene trovata l'attrezzatura, si ritorna come quantità 0.
	* Si parte dalla radice e si controllano i nodi figli: 
	* se il codice dell'attrezzatura che sto cercando è maggiore del codice della radice, allora mi soposto nel figlio destro;
	* se invece il codice dell'attrezzatura che sto cercando è minore del codice della radice, allora mi soposto nel figlio sinistro.
	* Ripeto questa procedura fino a che non non trovo l'attrezzatura.
    * @param codiceDaTrovare
    * @param luogoDaTrovare
    * @return 
    */
	public int find(String codiceDaTrovare, String luogoDaTrovare){
		Attrezzatura corrente = radice;
        int quantita=0;
		while(corrente!=null){
            confronti2++;
			if(corrente.getCodice().compareTo(codiceDaTrovare)==0){
                quantita = corrente.findInLista(luogoDaTrovare);
                return quantita;
			}else if(corrente.getCodice().compareTo(codiceDaTrovare)>0){
				corrente = corrente.getLeft();
			}else{
				corrente = corrente.getRight();
			}
		}
        return quantita;
	}
    /**
	* Elimino un'attrezzatura da uno specifico laboratorio; se dopo di ciò non è presente più alcuna attrezzatura all'interno del
	* laboratorio allora elimino il laboratorio dalla lista.
	* Se un attrezzatura non è più presente in alcun laboratorio allora elimino la elimino dall'albero.
	* Ritorno true se l'eliminazione avviene con successo, false in caso contrario.
    */	
	public boolean delete(String codiceDaEliminare,String luogoDaEliminare){
		Attrezzatura padre = radice;
		Attrezzatura corrente = radice;		//parto dalla radice
		boolean isLeftChild = false;
        boolean eliminato = false;
		while(corrente.getCodice().compareTo(codiceDaEliminare)!=0){
            confronti2++;
			padre = corrente;
			if(corrente.getCodice().compareTo(codiceDaEliminare)>0){
                //confronti2++;
				isLeftChild = true;
				corrente = corrente.getLeft();	 //scendo nel figlio sinistro
			}else{
				isLeftChild = false;
				corrente = corrente.getRight();	 //scendo nel figlio destro
			}
			if(corrente == null){
                eliminato=false;
                return eliminato;		//in questo caso vuol dire che non ho trovato l'attrezzatura o il laboratorio.
			}
		}
		//Se arrivo a questo punto vuol dire che ho trovato il nodo
		//Caso 1: il nodo da eliminare non ha figli.
		//In questo caso basta arrivare al nodo da eliminare e tenere traccia del padre.
		//Se il nodo è il figlio destro allora settare parent.right = null, se invece
		//il nodo da eliminare è il figlio sinistro settare parent.left = null.
                
		if(corrente.getLeft()==null && corrente.getRight()==null){
            confronti2++;
			if(corrente==radice){
                //confronti2++;
                eliminato = corrente.deleteToLista(luogoDaEliminare);	//se l'attrezzatura viene eliminata con successo dal laboratorio ritorno true, senò false
                if(radice.getLista().isEmpty()){
                    radice = null;   				//caso in cui l'attrezzatura non è presente in nessun laboratorio
                }	
			}
			if(isLeftChild ==true){
                eliminato = padre.getLeft().deleteToLista(luogoDaEliminare);
                if(padre.getLeft().getLista().isEmpty()){
                padre.setLeft(null);   
                }	
			}else{
                eliminato = padre.getRight().deleteToLista(luogoDaEliminare);
                if(padre.getRight().getLista().isEmpty()){
                    padre.setRight(null);   
                }	
			}
                return eliminato;		
		}
		//Case 2 : il nodo da eliminare ha 1 figlio
		//Tengo traccia del padre e in che suo lato esiste il figlio.
		//Dopo che elimino il figlio, prendo il suo sottoalbero e lo collego al padre dalla stessa parte dove esisteva il figlio.
		else if(corrente.getRight()==null){     //Non ha il figlio destro
            confronti2++;
			if(corrente==radice){
                //confronti2++;
                eliminato = corrente.deleteToLista(luogoDaEliminare);
                if(corrente.getLista().isEmpty()){
                    radice = corrente.getLeft();
                }	
			}else if(isLeftChild){
                eliminato = corrente.deleteToLista(luogoDaEliminare);
                if(corrente.getLista().isEmpty()){
                    padre.setLeft(corrente.getLeft());
				}	
			}else{
                eliminato = corrente.deleteToLista(luogoDaEliminare);
				if(corrente.getLista().isEmpty()){
                    padre.setRight(corrente.getLeft());
				}
			}
            return eliminato;
		}
		else if(corrente.getLeft()==null){      //Non ha il figlio sinistro
            confronti2++;
			if(corrente==radice){
                //confronti2++;
                eliminato = corrente.deleteToLista(luogoDaEliminare);
                if(corrente.getLista().isEmpty()){
                    radice = corrente.getRight();
                }
			}else if(isLeftChild){
                eliminato = corrente.deleteToLista(luogoDaEliminare);
                if(corrente.getLista().isEmpty()){
                    padre.setLeft(corrente.getRight());
                }	
			}else{
                eliminato = corrente.deleteToLista(luogoDaEliminare);
                if(corrente.getLista().isEmpty()){
                padre.setRight(corrente.getRight());
                }
			}
            return eliminato;
        //Caso 3 : il nodo da eliminare ha 2 figli
		//In questo caso ci serve trovare il successore del nodo da eliminare,
		//ovvero il più piccolo nodo del sottoalbero destro del nodo da eliminare.
		//Trovato il successore, ci basterà sostituirlo al posto del nodo da eliminare.
		}else if(corrente.getLeft()!=null && corrente.getRight()!=null){
            confronti2++;
			Attrezzatura successor = getSuccessore(corrente);     //utilizziamo il successore
			if(corrente==radice){
                //confronti2++;
                eliminato = corrente.deleteToLista(luogoDaEliminare);
                if(radice.getLista().isEmpty()){
                    radice = successor;
                }	
			}else if(isLeftChild){
                //confronti2++;
                eliminato = padre.getLeft().deleteToLista(luogoDaEliminare);
                if(padre.getLeft().getLista().isEmpty()){
                padre.setLeft(successor);
                }	
			}else{
                eliminato = padre.getRight().deleteToLista(luogoDaEliminare);
                if(padre.getLeft().getLista().isEmpty()){
					padre.setRight(successor);
                }  
			}			
			successor.setLeft(corrente.getLeft());
		}		
		return eliminato;		
	}
	/**
         * Quando elimino un nodo con due figli, per ristrutturare l'albero, ho
         * bisogno di trovare il successore del nodo da eliminare.
         * Il successore di un nodo u è il nodo più piccolo nel sottoalbero destro di u.
         * @param nodoDaEliminare
         * @return 
         */
	public Attrezzatura getSuccessore(Attrezzatura nodoDaEliminare){
		Attrezzatura successore =null;
		Attrezzatura padreDelSuccessore =null;
		Attrezzatura corrente = nodoDaEliminare.getRight();
		while(corrente!=null){
            //confronti2++;
			padreDelSuccessore = successore;
			successore = corrente;
			corrente = corrente.getLeft();
		}
		//check if successore has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
		//padreDelSuccessore
        confronti2++;
		if(successore!=nodoDaEliminare.getRight()){
			padreDelSuccessore.setLeft(successore.getRight());
			successore.setRight(nodoDaEliminare.getRight());
		}
		return successore;
	}
    /**
    * Inserisce un attrezzatura in un laboratorio.
    * @param codiceDaInserire
    * @param luogoDaInserire 
    */
	public void insert(String codiceDaInserire, String luogoDaInserire){
		Attrezzatura nodoDaInserire = new Attrezzatura(codiceDaInserire);
        confronti2++;
		if(radice==null){
			radice = nodoDaInserire;
            nodoDaInserire.addToLista(luogoDaInserire);        //guarda meglio
			return;
		}
		Attrezzatura corrente = radice;
		Attrezzatura padre = null;
		while(true){
			padre = corrente;
            confronti2++;
			if(corrente.getCodice().compareTo(codiceDaInserire)>0){
				corrente = corrente.getLeft();
                confronti2++;
				if(corrente==null){
					padre.setLeft(nodoDaInserire);
                    nodoDaInserire.addToLista(luogoDaInserire);
					return;
				}
                //confronti2++;
			}else if(corrente.getCodice().compareTo(codiceDaInserire)<0){
				corrente = corrente.getRight();
                confronti2++;
				if(corrente==null){
					padre.setRight(nodoDaInserire);
                    nodoDaInserire.addToLista(luogoDaInserire);
					return;
				}
                //confronti2++;
                }else if(corrente.getCodice().compareTo(codiceDaInserire)==0){
                    corrente.addToLista(luogoDaInserire);
                    return;
                }
		}
    }
	
    public Attrezzatura getRadice(){
        return radice;
    }
    /**
    * Visualizza le attrezzature in ordine.
    * @param radice 
    */
	public void display(Attrezzatura radice){
		if(radice!=null){
                        confronti2++;
			display(radice.getLeft());
			System.out.print(" " + radice.getCodice());
			display(radice.getRight());
		}
	}
        
}
    public class Esercizio1{
	public static void main(String args[]){
                ArrayList<String> lista = new ArrayList();
				BinarySearchTree b = new BinarySearchTree();
                //Scanner lettura = null;
                
				
				String nomeFile = args[0];
				Scanner lettura = null;
                
				
                //String nomeFile = lettura1.nextLine();
                try{
					lettura = new Scanner(new File(nomeFile));
					//lettura = new Scanner(new File(nomeFile));
                }catch(FileNotFoundException e){
                    System.out.println("Errore nella lettura del file "+nomeFile);
                    System.exit(0);
                }
                int quantita=0;
                boolean risultato = false;
                String visualizza=null;
                String operazione;
                String nomeAttrezzatura;
                String luogo;
                while(lettura.hasNextLine()){
                    operazione = lettura.next();
                    nomeAttrezzatura = lettura.next();
                    luogo = lettura.next();
                    Attrezzatura attrezzo = new Attrezzatura(nomeAttrezzatura);
                    if(operazione.equalsIgnoreCase("I")){
                        b.insert(nomeAttrezzatura, luogo);
                        visualizza = (Attrezzatura.confronti+BinarySearchTree.confronti2)+"  Inserimento  Riuscito";
                        System.out.println(visualizza);
                    }
                    else if(operazione.equalsIgnoreCase("C")){
                        risultato=b.delete(nomeAttrezzatura, luogo);
                        if(risultato==true)
                        {
                            visualizza = (Attrezzatura.confronti+BinarySearchTree.confronti2)+"  Cancellazione  Riuscita";
                            System.out.println(visualizza);
                        }
                        else{
                            visualizza = (Attrezzatura.confronti+BinarySearchTree.confronti2)+"  Cancellazione  Non Riuscita";
                            System.out.println(visualizza);
                        }
                    }
                    else if(operazione.equalsIgnoreCase("R")){
                        quantita=b.find(nomeAttrezzatura, luogo);
                        if(quantita!=-1 && quantita!=0){
                            visualizza = (Attrezzatura.confronti+BinarySearchTree.confronti2)+"  Ricerca  Trovato "+quantita;
                            System.out.println(visualizza);
                        }
                        else{
                            visualizza = (Attrezzatura.confronti+BinarySearchTree.confronti2)+"  Ricerca  Non Trovato";
                            System.out.println(visualizza);
                        }
                    }
                    lista.add(visualizza);
                    Attrezzatura.confronti=0;
                    BinarySearchTree.confronti2=0;
                }
                lettura.close();
                
                
                String nomeFile2 = args[1];
                PrintWriter scrittura = null;
                try{
                    scrittura = new PrintWriter(nomeFile2);
                }catch(FileNotFoundException e){
                    System.out.println("Errore nell'apertura del file.");
                    System.exit(0);
                }
                for(int i=0;i<lista.size();i++){
			scrittura.println(lista.get(i));			
		}
                scrittura.close();
                

	}
 
}


