package bigdata2019;

import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

public class GUI extends javax.swing.JFrame {
	
	private static final long serialVersionUID = -3575539142917792586L;
	private final String ONTOPATH = "FoodOntology.owl";
	private final String ONTOCLASS = "Ingredient";
	private final String PYTHONPATH = "/Library/Frameworks/Python.framework/Versions/3.7/bin/python3";
	//private final String PYTHONPATH = "C:/Program Files/Python36/python";
	private String htmlText;
	private OntologyManager ontoManager;

    public GUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        urlButton.setEnabled(true);
        classCombo.setEnabled(false);
        startButton.setEnabled(false);
        
        this.ontoManager = new OntologyManager(this.ONTOPATH);
        ontoLabel.setText("The Ontology " + this.ONTOPATH + " has been loaded correctly!" );

    }
    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        urlLabel = new javax.swing.JLabel();
        urlText = new javax.swing.JTextField();
        urlButton = new javax.swing.JButton();
        classLabel = new javax.swing.JLabel();
        outLabel = new javax.swing.JLabel();
        outPath = new javax.swing.JLabel();
        outButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        eraseButton = new javax.swing.JButton();
        classCombo = new javax.swing.JComboBox<>();
        infoLabel = new javax.swing.JLabel();
        urlDeleteButton = new javax.swing.JButton();
        ontoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RDFood Project - Cavaliere Federica and Scaldaferri Antonello");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(251, 235, 219));

        jLabel2.setBackground(new java.awt.Color(250, 245, 220));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("logo.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(179, 179));

        urlLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        urlLabel.setText("1 - Enter the URL of the HTML file where you want to work on");

        urlButton.setText("Ok");
        urlButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlButtonActionPerformed(evt);
            }
        });

        classLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        classLabel.setText("2 - Set the HTML Class attribute you want add semantic content");

        outLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        outLabel.setText("3 - Load the folder where you want to save the output");

        outButton.setText("Search...");
        outButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outButtonActionPerformed(evt);
            }
        });

        startButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        startButton.setText("Start");
        startButton.setPreferredSize(new java.awt.Dimension(98, 29));
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        eraseButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        eraseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("trashBox.png"))); // NOI18N
        eraseButton.setText(" All");
        eraseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eraseButtonActionPerformed(evt);
            }
        });


        infoLabel.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        infoLabel.setText("The report, the resulting HTML file, the extracted RDF/NTriples text and graph will be saved in...");

        urlDeleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("trashBox.png"))); // NOI18N
        urlDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlDeleteButtonActionPerformed(evt);
            }
        });
        
        ontoLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14));
        ontoLabel.setText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(eraseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(urlText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(urlButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(urlDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(classCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(outButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(outPath, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ontoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(urlLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(outLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(infoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(classLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 105, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ontoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(urlLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(urlButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                    .addComponent(urlText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(classLabel))
                            .addComponent(urlDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(classCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(outLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outPath, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 230, Short.MAX_VALUE)
                        .addComponent(outButton)))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eraseButton))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    
    }
    
    private void eraseButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            

        urlText.setText("");
        outPath.setText("");
        urlButton.setEnabled(true);
        
        String[] arrayModel = new String[0];
        classCombo.setModel(new javax.swing.DefaultComboBoxModel<>(arrayModel));
        classCombo.setEnabled(false);
        startButton.setEnabled(false);
    }                                           

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
    	
    	String classHTML = (String) classCombo.getSelectedItem();
    	String output = outPath.getText() + "/";
    	
    	TreeMap<String, String> ingredients = this.ontoManager.loadClass(this.ONTOCLASS);
    	
    	List<String> ingredientsList = this.ontoManager.getElementsFromHTMLText(this.htmlText, classHTML);
    	
    	List<String> ingredientsTokens = new LinkedList<String>();
		for (String ingredient : ingredientsList) {
			ingredientsTokens.add(this.ontoManager.tokenize(ingredient));
		}
		
		List<String> tokens = new LinkedList<String>();
		for (String ingredientTokens : ingredientsTokens) {
			String[] tokensArray = ingredientTokens.split(" ");
			for (String token : tokensArray) {
				if (!token.equals("") && !tokens.contains(token)) {
					tokens.add(token);
				}
			}
		}
		
		List<String> ingredientsLemmas = new LinkedList<String>();
		for (String ingredientToken : tokens) {
			ingredientsLemmas.add(this.ontoManager.lemmatize(ingredientToken));
		}
		
		List<String> lemmas = new LinkedList<String>();
		for (String ingredientLemmas : ingredientsLemmas) { 
			lemmas.addAll(Arrays.asList(ingredientLemmas.split(" ")));
		}
		
		TreeMap<String, String> matchedIngredients = this.ontoManager.elementsMatch(ingredients, tokens, lemmas, output);
		this.ontoManager.addSemanticsToHTMLClass(ingredients, matchedIngredients, this.htmlText, classHTML, output);
		this.ontoManager.fromHTMLtoRDF(this.PYTHONPATH, output);
		
		JOptionPane.showMessageDialog(null, "The operation ended successfully!", "INFO", JOptionPane.INFORMATION_MESSAGE);
		
		String[] splittedReport = this.ontoManager.getReport().split("\nIngredients NOT found:\n\n");
		if (splittedReport.length > 1) {
			String missingItems = splittedReport[1];
			new AddIndividualFrame(this, this.ontoManager, missingItems).setVisible(true);
		}
		
		this.eraseButtonActionPerformed(evt);
		
    }

    private void outButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          

        IO io = new IO();

        try {
            String path = io.caricaCartella();
            outPath.setText(path);
            startButton.setEnabled(true);

        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Upload failed", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }                                         

    private void urlButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          

        String value = urlText.getText();

        if (value.equals("")) {
            JOptionPane.showMessageDialog(null, "Incomplete url", "WARNING", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
            	
            	this.htmlText = this.ontoManager.getHTMLTextFromURL(value);
                urlButton.setEnabled(false);
                
                Set<String> classSet = this.ontoManager.getAllClassesFromHTMLText(this.htmlText);
                
                String[] arrayModel = new String[classSet.size()];
                int i = 0;
                
                for(String e: classSet) {
                	
                	arrayModel[i] = e;
                	i++;
                }
                
                classCombo.setModel(new javax.swing.DefaultComboBoxModel<>(arrayModel));
                
                if(classSet.contains("recipe-ingredient-name")) {
                	classCombo.setSelectedItem("recipe-ingredient-name");
                }
                classCombo.setEnabled(true);
                
                if(!outPath.getText().isEmpty()) {
                	startButton.setEnabled(true);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Wrong url", "WARNING", JOptionPane.WARNING_MESSAGE);
            }

        }
        
    }
    
    private void urlDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        urlText.setText("");
        urlButton.setEnabled(true);
        startButton.setEnabled(false);
        
        String[] arrayModel = new String[0];
        classCombo.setModel(new javax.swing.DefaultComboBoxModel<>(arrayModel));
        classCombo.setEnabled(false);

    }
    
    public String getONTOCLASS() {
		return this.ONTOCLASS;
	}
    
    public static void main(String args[]) {
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    private javax.swing.JComboBox<String> classCombo;
    private javax.swing.JLabel classLabel;
    private javax.swing.JButton eraseButton;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel ontoLabel;
    private javax.swing.JButton outButton;
    private javax.swing.JLabel outLabel;
    private javax.swing.JLabel outPath;
    private javax.swing.JButton startButton;
    private javax.swing.JButton urlButton;
    private javax.swing.JButton urlDeleteButton;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JTextField urlText;
    
}
