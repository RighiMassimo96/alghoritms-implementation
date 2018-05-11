# alghoritms-implementation
Algorithms implementation through Java

I developed this project in the second year of university for the course "Algoritmi e Strutture Dati".
The project is divided into 3 parts, each of which implements a different algorithm.

##1 - BINARY SEARCH TREE (BST)

A BST has been implemented that manages a collection of equipment each characterized by an alphanumeric code.
To each equipment is associated a list of places (warehouses, research laboratories, etc ...) in which the equipment is present. For each place the quantity of equipment of that type that is present must be specified. In the same place there may be more than one piece of equipment and the same type of equipment can be present in several places.

It is possible at any time to add or remove equipment. When adding equipment, specify the place where it will be placed, while when removing equipment, it is necessary to specify the place from which it is removed. If no such equipment remains in place, the place is removed from the list. If the equipment is no longer present anywhere, it is removed from the data structure.

The search operation has also been implemented, which takes the equipment and a place will return the number of specimens of the equipment present in the specified site.

For each operation of research, insertion or deletion, the number of elementary instructions performed to complete the operation was identified experimentally and then compared with the theoretical computational complexity calculated for that type of operation.

The algorithm takes a file as input (ex: Input1Es1.txt) and returns the result (ex: Output1Es1.txt).
The data read from the Input1Es1.txt file will be in the following format:
I Id_attrezzatura luogo 
C Id_attrezzatura luogo 
R Id_attrezzatura luogo 
where I stands for insertion, C for deletion and R search.

After each insertion, search or deletion operation, the number of elementary instructions executed, the type of operation and the result according to this format will be printed on the Output1Es1.txt file:
istr  Tipo di operazione  Esito
es.
293 Inserimento Riuscito
45 Ricerca Non Trovato


##2 - HASH TABLE

The behavior of the Hash function based on the multiplication method has been experimentally verified.
A hash table has been implemented in which the collisions are managed by overflow lists that use a Hash function based on the multiplication method with parameters C = 0.15.

Subsequently the keys were inserted in the Hash table; at the end of the insertion the length of the overflow list was measured.
As for part 1, the reading of the inputs and the writing of the outputs was done via file I/O.

##3 - MIN HEAP

An array-based tree structure has been implemented that implements a Min Heap.
Once the insertion has been completed (again via file), the transformation of the tree thus constructed into an array (which obviously has to retain the properties of the heap) is implemented and subsequently printed in the Output1Es3.txt file (one element per line).
At this point, the array previously obtained is reordered and printed in the Output2Es3.txt file (one element per line).
