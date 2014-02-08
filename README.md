# Progetto del corso Information Retrieval, 2013/2014 #

Il codice dell'implementazione presentata si trova in src/Probability.java,
mentre src/WeightedInJaccard.java contiene un algoritmo che, seppur meno
raffinato, ottiene un migliore score F1.

Date due pagine, l'algoritmo di Probability.java prova a stimare il
numero di pagine linkate da entrambe. Ciò viene ottenuto modellando il
problema come l'aggiunta di due nuovi nodi al grafo di Wikipedia secondo
il modello di accrescimento di Barabási–Albert. In questo modello una
nuova pagina linka con probabilità maggiore una pagina con numero di link
entranti maggiore. Il numero di pagine linkato da entrambe viene stimato
semplificando il problema aggiungendo un'ipotesi d'indipendenza. A questo
punto la differenza fra il valore atteso e il valore stimato viene
riscalata fra 0 e 1 con un'approssimazione trovata empiricamente della
funzione di ripartizione. Maggiori dettagli si trovano nel file slide.pdf
in questa stessa cartella.

L'algoritmo di WeightedInJaccard.java calcola invece l'indice di Jaccard sui
link in ingresso delle due pagine. Per far pesare di meno le pagine da cui
escono molti link viene introdotto un fattore correttivo ispirato all'idf.
Interpretiamo infatti ogni pagina come un documento di collezione D, i cui
termini sono i link entranti. Il riscalamento così introdotto migliora
leggermente lo score F1, a prezzo però di un incremento massiccio nel tempo
di calcolo, effetto mitigato da una cache mantenuta in memoria principale degli
score idf.

Si è scelto infine di concentrare gli sforzi sul dataset AIDA/CoNLL perché si è
trovato sperimentalmente che, per via del ridotto numero di documenti, è troppo
facile fare overfit sul dataset WikipediaSimilarity411.

## Manuale d'uso ##

La rule `make` del Makefile compila il programma con le impostazioni contenute
in src/Main.java.

La rule `make run` esegue il run richiesto.

La rule `make score`, se eseguita configurando il programma nella modalità di
testing, produce il risultato stimato del run sul dataset
WikipediaSimilarity41.

## Licenza ##

Il codice è rilasciato con licenza MIT e sarà disponibile, allo scadere della
data di consegna, su [Github](https://www.github.com/jacquerie/IR).
