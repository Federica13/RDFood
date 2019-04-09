# RDFood
This project is an ibrid Java and Python application that adds RDFa semantic content to food webpages. 
RDFood was implemented by two computer engeneers Federica Cavaliere and Antonello Scaldaferri.

La repository "RDFood" contiene il progetto Java "bigdata2019", nel quale è presente lo script Python "fromHTMLToRDF.py". 
A sostegno del progetto Java è stato utilizzato lo strumento di build automation "Apache Maven".

Per poter eseguire l'applicazione:

1) Istallare, sulla propria macchina, i package Python necessari all'esecuzione dello script sovra citato. 
    Nello specifico: "rdflib", "networkx" e "matplotlib".
    
2) Istallare automaticamente le librerie utilizzate (le dipendenze) dal progetto Java attraverso Maven.
    Nello specifico: aprire in un opportuno IDE il progetto (si consiglia Eclipse), con il tasto destro 
    selezionare la voce "Maven", successivamente la voce "Update Project...", ed infine premere "OK".
    
3) All'interno della classe "GUI.java" del progetto, settare l'attributo "PYTHONPATH" con l'absolute path
    dell'esecutivo Python sulla propria macchina. Infine, eseguire da questa classe il programma.
    
