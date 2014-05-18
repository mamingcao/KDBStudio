/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studio.core;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.util.regex.*;

/**
 *
 * @author mamingcao
 */
public class FilterComboBox extends JComboBox {
    private List<String> array;
    private int currentCaretPosition=0;


    public FilterComboBox(List<String> array) {
        super(array.toArray());
        
        this.array = array;
        this.setEditable(true);
        final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
        textfield.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                if(key.getKeyCode() == KeyEvent.VK_DOWN){
                    
                }else if(key.getKeyCode() == KeyEvent.VK_UP){
                  
                }else if(key.getKeyCode() == KeyEvent.VK_LEFT){
                    
                }else if(key.getKeyCode() == KeyEvent.VK_RIGHT){
                    
                }
                else{
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {

                            currentCaretPosition=textfield.getCaretPosition();
                            if(textfield.getSelectedText()==null)
                            {
                                textfield.setCaretPosition(0);
                                comboFilter(textfield.getText());
                                textfield.setCaretPosition(currentCaretPosition);
                            }
                         }
                    });
                }
            }

        });

    }

    public void comboFilter(String enteredText) {
        List<String> filterArray= new ArrayList<String>();
        Pattern MY_PATTERN = null;
        try{
            String pattern = enteredText.replaceAll("\\*", ".*");
            MY_PATTERN = Pattern.compile(pattern);
        }catch(Exception e){
            
        }
        
        for (int i = 0; i < array.size(); i++) {
            String option = array.get(i).toLowerCase();
            if (option.contains(enteredText.toLowerCase())) {
                 filterArray.add(array.get(i));
            }else{
                if(MY_PATTERN != null && MY_PATTERN.matcher(option).find()){
                    filterArray.add(array.get(i));
                }
            }
        }
        if (filterArray.size() > 0) {
            this.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            this.getEditor().setItem(enteredText);
         
            this.showPopup();
        }
        else {
            this.hidePopup();
        }
    }
}
