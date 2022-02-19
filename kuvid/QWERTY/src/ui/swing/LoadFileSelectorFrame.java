package ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.handler.SaveLoadHandler;
import ui.handler.UIHandler; 
public class LoadFileSelectorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JList<String> list;
	private DefaultListModel<String> saves;

	public LoadFileSelectorFrame(UIHandler UIhandler) {
		setBounds(100, 100, 400, 190);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setList();
		list = new JList<String>(saves);
		list.setBounds(205, 10, 170, 130);
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(205, 10, 170, 130);
		contentPane.add(scroll);

		JButton loadButton = new JButton("Load");
		loadButton.setBounds(10, 105, 90, 35);
		contentPane.add(loadButton);
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue() != null) {
					SaveLoadHandler.load(list.getSelectedValue());
				}
			}
		});

		JButton removeButton = new JButton("Remove");
		removeButton.setBounds(105, 105, 90, 35);
		contentPane.add(removeButton);
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue() != null) {
					SaveLoadHandler.remove(list.getSelectedValue());
					int selectedIndex = list.getSelectedIndex();
					saves.remove(selectedIndex);
				}
			}
		});

		JLabel info = new JLabel("Select Save File for");
		info.setBounds(20, 21, 130, 30);
		contentPane.add(info);

		JLabel info1 = new JLabel("Load or Remove");
		info1.setBounds(20, 37, 130, 26);
		contentPane.add(info1);
		
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	UIhandler.sendCommandResume();		        
		    }
		});
	}
	
	private void setList() {
		saves = new DefaultListModel<String>();
		for(int i = 0; i < SaveLoadHandler.getController().getSaveFilesList().size(); i++) {
			saves.addElement(SaveLoadHandler.getController().getSaveFilesList().get(i));
		}
		
	}
} 
