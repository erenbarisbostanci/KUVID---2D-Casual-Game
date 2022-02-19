package ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ui.handler.SaveLoadHandler;
import ui.handler.UIHandler;

public class EnterSaveNameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField saveField;
	
	public EnterSaveNameFrame(UIHandler UIhandler) {
		setBounds(100, 100, 225, 150);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(50, 68, 90, 35);
		contentPane.add(saveButton);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!saveField.getText().equalsIgnoreCase("")) {
					SaveLoadHandler.save(saveField.getText());
				}
			}
		});
		
		JLabel info = new JLabel("Enter Save File Name");
		info.setBounds(10, 10, 130, 30);
		contentPane.add(info);
		
		saveField = new JTextField();
		saveField.setBounds(50, 39, 90, 19);
		contentPane.add(saveField);
		saveField.setColumns(10);
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	UIhandler.sendCommandResume();		        
		    }
		});
	}
}
