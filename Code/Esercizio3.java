import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author righi
 */


class MinHeap
{
    private int[] Heap;
    private int size;		//Mi tiene conto della posizione della prima posizione libera nell'array
    private int DIMENSIONE;
    public MinHeap(int DIMENSIONE)
    {
        this.DIMENSIONE = DIMENSIONE;
        this.size = 0;
        Heap = new int[this.DIMENSIONE + 1];		//creo un array di lunghezza DIMENSIONE+1
        Heap[0] = Integer.MIN_VALUE;				//inserisco il valore più piccolo in prima posizione nel vettore dello heap
    }
    /**
	* Mi restituisce il padre di uno specifico nodo
	*/
    private int padre(int posizione)
    {
        return posizione / 2;
    }
    
	/**
	* Scambia tra loro due nodi
	*/
    private void scambia(int posizioneF, int posizioneS)
    {
        int tmp;
        tmp = Heap[posizioneF];
        Heap[posizioneF] = Heap[posizioneS];
        Heap[posizioneS] = tmp;
    }
    
	/**
	* Inserisco un nuovo nodo all'interno dello heap.
	* 
	*/
    public void insert(int elemento)
    {
        Heap[++size] = elemento;		//Inserisco l'elemento nella prima posizione libera dell'array
        int corrente = size;			//corrente è la posizione del mio nuovo elemento appena inserito 
		
		//Finchè il mio nuovo elemento è minore di suo padre, lo scambio con il padre
        while (Heap[corrente] < Heap[padre(corrente)])	
        {
            scambia(corrente,padre(corrente));
            corrente = padre(corrente);		//la nuova posizione di corrente è la posizione dell'elemento che era suo padre
        }	
    }
    /**
     * Ritorno l'array che mantiene la proprietà heap dell'albero
     * @return 
     */
    public int[] stampaHeap(){
        int[] heapStampa = new int[Heap.length];
        for(int i=1;i<=size;i++){
            heapStampa[i] = Heap[i];
        }
        return heapStampa;
    }
    /**
     * Ritorno l'array riordinato tramite Insertion Sort
     * @return 
     */
    public int[] ordinaHeap(){ 
        int[] heapOrdinato = new int[Heap.length];		//creo un array
        for (int j = 1; j <= size; j++) {  
            int chiave = Heap[j];  		//prendo l'elemento nella posizione j
            int i = j-1; 			//prendo l'elemento nella posizione j-1
			
			//Finchè l'elemento nella posizione j-1 è all'interno dell'array ed è maggiore dell'elemento nella posizione j, lo scambio 
            while ( (i > -1) && ( Heap [i] > chiave ) ) {  
                Heap [i+1] = Heap [i];  
                i--;  
            }  
            Heap[i+1] = chiave;  
        }
        for(int i=1;i<=size;i++){
            heapOrdinato[i]=Heap[i];	
        }
        return heapOrdinato;	//ritorno l'array ordinato
    }
}
public class Esercizio3 {
    public static void main(String args[])
    {
        int[] ordinato = null;
        int[] heap = null;
        MinHeap minHeap = new MinHeap(50);
        Scanner lettura = new Scanner(System.in);
        String nomeFile = args[0];
        
        Scanner scanner = null;
        try{
            scanner = new Scanner(new File(nomeFile));
        }catch(FileNotFoundException e){
            System.out.println("Errore nella lettura del file.");
            System.exit(0);
        }
        while(scanner.hasNextInt()){
            int nodo = scanner.nextInt();
            minHeap.insert(nodo);
        }
        scanner.close();
        
        //Scrivo su file l'array heap
        Scanner lettura2 = new Scanner(System.in);
        String nomeFile2 = args[1];
        //String nomeFile2 = "Output1Es3.txt";
        PrintWriter scrittura1 = null;
        try{
            scrittura1 = new PrintWriter(nomeFile2);
        }catch(FileNotFoundException e){
            System.out.println("Errore nella scrittura del file.");
            System.exit(0);
        }
        heap = minHeap.stampaHeap();
        for(int i=1;i<heap.length;i++){
            scrittura1.println(heap[i]);
        }
        scrittura1.close();
        
        //Scrivo su file l'array ordinato
        String nomeFile3 = "Output2Es3.txt";
        PrintWriter scrittura2 = null;
        try{
            scrittura2 = new PrintWriter(nomeFile3);
        }catch(FileNotFoundException e){
            System.out.println("Errore nella scrittura del file.");
            System.exit(0);
        }
        ordinato = minHeap.ordinaHeap();
        for(int i=1;i<ordinato.length;i++){
            scrittura2.println(ordinato[i]);
        }
        scrittura2.close();
        
        System.out.println("L'inserimento di un elemento all'interno dell'albero comporta un costo computazionale O(log n).");
        System.out.println("Quando invece riordino l'array tramite Insertion Sort ho un costo computazionale di O(n^2).");
    }
    
}
