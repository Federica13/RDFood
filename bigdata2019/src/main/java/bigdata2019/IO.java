package bigdata2019;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

public class IO extends javax.swing.JFrame {
	
	private static final long serialVersionUID = -4267814494066641139L;

	public IO(){
        
        initComponents();
        this.setLocationRelativeTo(null);
    }
	
	private void initComponents() {

        this.fileChooser = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(this.fileChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    
    }
	
	public String caricaCartella() throws IOException, ClassNotFoundException {
		
		String path = "";
                
        this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.fileChooser.setMultiSelectionEnabled(false);

        int valore = this.fileChooser.showOpenDialog(this);
        
        if(valore == JFileChooser.APPROVE_OPTION){
            
            File  file = this.fileChooser.getSelectedFile();  
            path = file.getAbsolutePath();     
        
        }
        else{
            throw new IOException();
        }
        
        dispose();
        
        return path;
    }

	private javax.swing.JFileChooser fileChooser;
	
}
