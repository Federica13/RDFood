package bigdata2019;

import javax.swing.JOptionPane;

import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class AddIndividualFrame extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 5686585192365270554L;
	private GUI parentFrame;
	private OntologyManager ontoManager;
	
    public AddIndividualFrame(GUI parentFrame, OntologyManager ontoManager, String missingItems) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.parentFrame = parentFrame;
        this.ontoManager = ontoManager;
        this.textArea.setText(missingItems);
    }
    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        textField1 = new javax.swing.JTextField();
        label2 = new javax.swing.JLabel();
        button = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        textField2 = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        label1.setText("Some elements were not found within the ontology!");
        label1.setFont(new java.awt.Font("Lucida Grande", 1, 14));

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        textField1.setText("");

        label2.setText("Do you want to add someone?");
        label2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); 

        button.setText("Add");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        label3.setText("ATTENTION: Insert the ingredients into the ontology in singular!");
        label3.setFont(new java.awt.Font("Lucida Grande", 1, 14)); 

        textField2.setText("");

        label5.setText("en:");
        label5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); 

        label4.setText("it:");
        label4.setFont(new java.awt.Font("Lucida Grande", 1, 14)); 

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(label4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(label5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addComponent(label3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(button)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1)
                .addGap(13, 13, 13)
                .addComponent(label2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label5)
                    .addComponent(label4))
                .addGap(18, 18, 18)
                .addComponent(button)
                .addContainerGap(8, Short.MAX_VALUE))
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
    
    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	if (!this.textField1.getText().equals("") && !this.textField2.getText().equals("")) {
    		try {
				this.ontoManager.addIndividualToClassWithAnnotation(this.textField1.getText(), this.parentFrame.getONTOCLASS(), this.textField2.getText());
				JOptionPane.showMessageDialog(null, "The ingredient: " + this.textField1.getText() +
						" (\"" + this.textField2.getText() + "\"@en) has been added to the ontology!",
						"INFO", JOptionPane.INFORMATION_MESSAGE);
			} catch (OWLOntologyStorageException e) {
				JOptionPane.showMessageDialog(null, "Impossible to save the ontology!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
    	} else {
    		JOptionPane.showMessageDialog(null, "Enter the required values!", "WARNING", JOptionPane.WARNING_MESSAGE);
    	}
    	
    	this.textField1.setText("");
    	this.textField2.setText("");
    	
    }
    
    private javax.swing.JButton button;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField textField1;
    private javax.swing.JTextField textField2;
    
}
