package bigdata2019;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import eu.fbk.dh.tint.runner.TintPipeline;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.*;

public class OntologyManager {
	
	private OWLOntologyManager manager;
	private OWLOntology localOntology;
	private String report;
	
	public OntologyManager(String ontology) {
		super();
		try {
			this.manager = OWLManager.createOWLOntologyManager();
			this.localOntology = this.manager.loadOntologyFromOntologyDocument(new File(ontology));
		} catch (OWLOntologyCreationException e) {
			System.out.println("Errore durante il caricamento dell'ontologia <" + ontology + ">");
			System.out.println(e.getMessage());
		}
	}

	public TreeMap<String, String> loadClass(String cls) {

		OWLClass clsOWL = this.manager.getOWLDataFactory().getOWLClass(IRI.create("http://purl.org/ontology/fo/" + cls));
	
		OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
		OWLReasoner reasoner = reasonerFactory.createReasoner(this.localOntology);

		NodeSet<OWLNamedIndividual> individualsSet = reasoner.getInstances(clsOWL, true);
		Set<OWLNamedIndividual> individuals = individualsSet.getFlattened();
		TreeMap<String, String> individualsMap = new TreeMap<String, String>();
		
		for (OWLNamedIndividual a : individuals) {
			String s = a.toString().substring(a.toString().lastIndexOf('/') + 1, a.toString().length() - 1);
			String i = ((OWLAnnotationAssertionAxiom) a.getAnnotationAssertionAxioms(this.localOntology).toArray()[0]).getValue().toString();
			individualsMap.put(s, i.substring(1, i.length() - 4));
		}

		return individualsMap;
		
	}
	
	public String getHTMLTextFromURL(String url) throws MalformedURLException, IOException {
		
		String content = null;
        URLConnection connection;
 
        connection =  new URL(url).openConnection();
        Scanner scanner = new Scanner(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        scanner.useDelimiter("\\Z");
        content = scanner.next();
        scanner.close();
        
        return content;

	}
	
	public List<String> getElementsFromHTMLText(String htmlText, String cls) {
		
		List<String> itemsToReturn = new ArrayList<String>();
		Elements items = Jsoup.parse(htmlText).getElementsByClass(cls);
		
		for (Element item : items) {
			itemsToReturn.add(item.text());
		}		
		
		return itemsToReturn;
		
	}
	
	public Set<String> getAllClassesFromHTMLText(String htmlText) {
		
		Set<String> setToReturn = new HashSet<String>();
		Elements allItems = Jsoup.parse(htmlText).getAllElements();
		
		for (Element item : allItems) {
			setToReturn.addAll(item.classNames());
		}
		
		return setToReturn;
		
	}
	
	public String tokenize(String text) {
		
		return text.replaceAll("\\p{Punct}|\\p{Digit}|’", " ").toLowerCase()
				.replaceAll(this.stopWordsRegex("stopwords-it.txt"), "")
				.replaceAll("\\s+", " ");
		
	}
	
	public String lemmatize(String text) {
		
		String lemmatizedText = "";

		Annotation annotation = new TintPipeline().runRaw(text);
		for (CoreMap sentence : annotation.get(SentencesAnnotation.class)) {
			for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
				if (sentence == annotation.get(SentencesAnnotation.class).get(annotation.get(SentencesAnnotation.class).size() - 1)
					&& token == sentence.get(TokensAnnotation.class).get(sentence.get(TokensAnnotation.class).size() - 1)) {
					lemmatizedText += token.get(LemmaAnnotation.class);
				} else {
					lemmatizedText += token.get(LemmaAnnotation.class) + " ";
				}
			}
		}
		
		return lemmatizedText;
		
	}
	
	private String stopWordsRegex(String stopWordsFileName) {
		
		char[] stopWordsChars = new char[(int) new File(stopWordsFileName).length()];
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(stopWordsFileName), "UTF-8"));
			br.read(stopWordsChars);
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		String[] stopWords = new String(stopWordsChars).split("\n");
		
		String regex = "";
		
		for (String s : stopWords) {
			if (s == stopWords[stopWords.length - 1]) {
				regex = regex + "\\b" + s + "\\b";
			}
			else {
				regex = regex + "\\b" + s + "\\b" + "|";
			}	
		}
		
		return regex;
		
	}
	
	public String getReport() {
		return this.report;
	}

	public TreeMap<String, String> elementsMatch(TreeMap<String, String> ingredients, List<String> tokens, List<String> lemmas, String path) {
		
		tokens.sort(null);
		lemmas.sort(null);
		List<String> ingredientsList = new LinkedList<String>(ingredients.keySet());
		List<String> ingredientsWithoutLast = new LinkedList<String>();
		
		for (String ingredient : ingredientsList) {
			ingredientsWithoutLast.add(ingredient.substring(0, ingredient.length() - 1));
		}
		
		TreeMap<String, String> matchedIngredients = new TreeMap<String, String>();
		List<Integer> matchedIndex = new LinkedList<Integer>();
		
		String tokenWithoutLast = new String();
		String lemmaWithoutLast = new String();
				
		this.report = "Word Matching\n\n\nIngredients found:\n\n";
		
		for (int i = 0; i < tokens.size(); i++) {
			tokenWithoutLast = tokens.get(i).substring(0, tokens.get(i).length() - 1);
			lemmaWithoutLast = lemmas.get(i).substring(0, lemmas.get(i).length() - 1);
			
			if (ingredientsWithoutLast.contains(lemmaWithoutLast) && !matchedIngredients.keySet().contains(tokens.get(i))) {
				matchedIngredients.put(tokens.get(i), ingredientsList.get(ingredientsWithoutLast.indexOf(lemmaWithoutLast)));
				this.report = this.report + "Token: " + tokens.get(i) + "\n" + "Lemma: " + lemmas.get(i) + "\n" + "Match: YES! -> " + " Ingredient: " + ingredientsList.get(ingredientsWithoutLast.indexOf(lemmaWithoutLast)) + "\n\n";
				matchedIndex.add(i);
			} else {
				if (ingredientsWithoutLast.contains(tokenWithoutLast) && !matchedIngredients.keySet().contains(tokens.get(i))) {
					matchedIngredients.put(tokens.get(i), ingredientsList.get(ingredientsWithoutLast.indexOf(tokenWithoutLast)));
					this.report = this.report + "Token: " + tokens.get(i) + "\n" + "Lemma: " + lemmas.get(i) + "\n" + "Match: YES! -> " + " Ingredient: " + ingredientsList.get(ingredientsWithoutLast.indexOf(tokenWithoutLast)) + "\n\n";
					matchedIndex.add(i);
				}
			}
		}
		
		this.report = this.report + "\nIngredients NOT found:\n\n";
		
		for (int i = 0; i < tokens.size(); i++) {
			if (!matchedIndex.contains(i)) {
				this.report = this.report + "Token: " + tokens.get(i) + "\n" + "Lemma: " + lemmas.get(i) + "\n" + "Match: NO! :(\n\n";
			}
		}

		try {

			FileOutputStream file = new FileOutputStream(new File(path + "report.txt"));
			file.write(this.report.getBytes());
			file.close();
		
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Impossible to save the report file!", "WARNING", JOptionPane.WARNING_MESSAGE);
		}
		
		return matchedIngredients;
		
	}
	
	private static Node toTextElement(String str, TreeMap<String, String> i, TreeMap<String, String> m) {
		
		Element e = new Element("span");
		e.attr("about", "");
		e.attr("vocab", "http://purl.org/ontology/fo/");
		e.attr("typeof", "Recipe");
		
		for (String s: str.split(" ")) {
			
			if (m.containsKey(s)) {
				
				Element a = e.appendElement("span");
				a.attr("about", "");
				a.attr("rel", "hasIngredient");
				a.attr("href", "http://purl.org/ontology/fo/" + m.get(s));
				
				Element b = e.appendElement("span");
				b.attr("about", "http://purl.org/ontology/fo/" + m.get(s));
				b.attr("vocab", "http://xmlns.com/foaf/0.1/");
				b.attr("property", "name");
				b.attr("content", i.get(m.get(s)));
				b.attr("lang", "en");
				b.appendText(s);
				
			} else {
				e.appendText(s);
			}
			
			e.appendText(" ");
		
		}
		
	    return e;
	}

	private static boolean checkMatchTextNode(String str, TreeMap<String, String> m) {
				
		for (String s: str.split(" ")) {
			
			if (m.containsKey(s)) {
				return true;
			}
		}
		
		return false;
	
	}
	
	private static void replaceTextNodes(Node root, TreeMap<String, String> i, TreeMap<String, String> m) {
	    
		if (root instanceof TextNode) {
			if(checkMatchTextNode(((TextNode) root).text(), m)) {
				root.replaceWith(toTextElement(((TextNode) root).text(), i, m));
			}
		} else {
			for (Node child : root.childNodes()) {
				replaceTextNodes(child, i, m);
			}
		}
	}
	
	public Document addSemanticsToHTMLClass(TreeMap<String, String> i, TreeMap<String, String>  m, String htmlText, String htmlCls, String path) {
		
		Document doc = Jsoup.parse(htmlText);
		Elements items = doc.getElementsByClass(htmlCls);

		for (Element item: items) {
			replaceTextNodes(item, i, m);
		}
		
		FileOutputStream file; 
		
		try {
			
			file = new FileOutputStream(new File("newItems.txt"));
			file.write(items.toString().getBytes());
			file.close();
			
			file = new FileOutputStream(new File(path + "finalHTML.html"));
			file.write(doc.toString().getBytes());
			file.close();
		
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Impossible to save the processed HTML file!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		return doc;
		
	}

	public void fromHTMLtoRDF(String s, String path) {
				
		Process p;
		StringBuffer output = new StringBuffer();
		
	    try {
	      p = Runtime.getRuntime().exec(s + " fromHTMLtoRDF.py");
	      p.waitFor();
	      
	      BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	      String line = "";
	      
	      while ((line = reader.readLine()) != null) {
	        output.append(line + "\n");
	      }
	      
	      p.destroy();
	      reader.close();
	      
	      File source = new File("outputRDF.txt");
	      File dest = new File(path + "outputRDF.txt");
	      FileUtils.copyFile(source, dest);
	      
	      source = new File("outputTriples.txt");
	      dest = new File(path + "outputTriples.txt");
	      FileUtils.copyFile(source, dest);
	      
	      source = new File("Graph.eps");
	      dest = new File(path + "Graph.eps");
	      FileUtils.copyFile(source, dest);
	     
	    } catch (IOException | InterruptedException e) {
	    	JOptionPane.showMessageDialog(null, "Impossible to extract RDF/XML content, N-Triples format and graph!", "WARNING", JOptionPane.WARNING_MESSAGE);
	    }
	   
	    System.out.println(output.toString());

	}

	public void addIndividualToClassWithAnnotation(String nameIndividual, String cls, String englishNameIndividual) throws OWLOntologyStorageException {
		
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		
		OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(
				factory.getOWLClass(IRI.create("http://purl.org/ontology/fo/" + cls)),
				factory.getOWLNamedIndividual(IRI.create("http://purl.org/ontology/fo/" + nameIndividual))
				);
		
		this.manager.addAxiom(this.localOntology, classAssertion);
		
		OWLAnnotationAssertionAxiom annotationAssertion = factory.getOWLAnnotationAssertionAxiom(
				factory.getOWLAnnotationProperty(IRI.create("http://xmlns.com/foaf/0.1/name")),
				factory.getOWLNamedIndividual(IRI.create("http://purl.org/ontology/fo/" + nameIndividual)).getIRI(),
				factory.getOWLLiteral(englishNameIndividual + "@en", OWL2Datatype.RDF_PLAIN_LITERAL));
		
		this.manager.addAxiom(this.localOntology, annotationAssertion);
		
		this.manager.saveOntology(this.localOntology);
		
	}

}
