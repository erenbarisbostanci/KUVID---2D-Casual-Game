package ui.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import ui.handler.BuilderHandler;
import ui.handler.UIHandler;
import ui.objects.AnimationPanel;
import ui.objects.StatsUIHolder;

public class BuilderScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public static JFrame build;

	private String atomNumber = "100";
	private String moleculeCount = "100";
	private String blockerCount = "10";
	private String powerUpCount = "20";
	private JTextField txtPlayerName;
	private JTextField textField_5;
	
	private JComboBox<String> moleculeBox;
	private JCheckBox spinBox;
	
	public static void main(String[] args) {
		build = new BuilderScreen();
		if(args.length>0) {
			UIHandler.setSaveLoadSystem(args[0]);
		}else {
			UIHandler.setSaveLoadSystem(null);
		}
		build.setVisible(true);
	}
	
	
	public BuilderScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 392);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setName("KUVID");
		JLabel lblNewLabel = new JLabel("Atom Number:");
		lblNewLabel.setBounds(10, 65, 132, 21);
		contentPane.add(lblNewLabel);

		JLabel lblPowerupNumber = new JLabel("Power-Up Number:");
		lblPowerupNumber.setBounds(10, 96, 132, 21);
		contentPane.add(lblPowerupNumber);

		JLabel lblNewLabel_1_1 = new JLabel("Molecule Number:");
		lblNewLabel_1_1.setBounds(10, 128, 132, 21);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Reaction Blocker Number:");
		lblNewLabel_1_2.setBounds(10, 160, 160, 21);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Length Unit L:");
		lblNewLabel_1_3.setBounds(10, 192, 132, 21);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Difficulty:");
		lblNewLabel_1_4.setBounds(10, 249, 132, 21);
		contentPane.add(lblNewLabel_1_4);

		textField = new JTextField();
		textField.setBounds(180, 65, 86, 20);
		textField.setText(atomNumber);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(180, 96, 86, 20);
		textField_1.setText(powerUpCount);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(180, 128, 86, 20);
		textField_2.setText(moleculeCount);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(180, 160, 86, 20);
		textField_3.setText(blockerCount);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(180, 192, 86, 20);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setText("5");
		textField_5.setColumns(10);
		textField_5.setBounds(179, 33, 86, 20);
		contentPane.add(textField_5);
		
		txtPlayerName = new JTextField();
		txtPlayerName.setToolTipText("Well, it doesn't make any difference but it might make you fell better :D");
		txtPlayerName.setText("Nickname");
		txtPlayerName.setBounds(10, 11, 86, 20);
		contentPane.add(txtPlayerName);
		txtPlayerName.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(180, 248, 86, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Easy");
		comboBox.addItem("Medium");
		comboBox.addItem("Hard");

		JButton btnNewButton = new JButton("START");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(180, 296, 143, 46);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[8];
				arr[0] = textField.getText();
				arr[1] = textField_1.getText();
				arr[2] = textField_2.getText();
				arr[3] = textField_3.getText();
				arr[4] = textField_4.getText();
				arr[5] = textField_5.getText();
				arr[6] = comboBox.getSelectedItem().toString();
				arr[7] = txtPlayerName.getText();
				
				StatsUIHolder StatisticHolder = new StatsUIHolder();
				if(moleculeBox.getSelectedItem().toString().equalsIgnoreCase("Straight")) {
					StatsUIHolder.isStraight = true;					
				}else if(moleculeBox.getSelectedItem().toString().equalsIgnoreCase("Branched")) {
					StatsUIHolder.isStraight = false;					
				}
				
				if(spinBox.isSelected()) {
					StatsUIHolder.areMoleculesSpin = true;
				}else {
					StatsUIHolder.areMoleculesSpin = false;
				}
				
				if(arr[4].length()!=0&&(Integer.parseInt(arr[4])>69)) {
					StatisticHolder.setL(Integer.parseInt(arr[4]));
				}
				
				AnimationPanel AnimationPanel = new AnimationPanel(StatisticHolder.getWidth(), StatisticHolder.getHeight());
				
				BuilderHandler bh = new BuilderHandler();
				bh.sendCreationListener(AnimationPanel);
				bh.sendStatisticListener(StatisticHolder);
				bh.sendCommandStringArr(arr);

				System.out.println("Game is initializing...");
				build.setVisible(false);

				GameScreen gameScreen = new GameScreen(AnimationPanel, StatisticHolder);
				build = gameScreen;
				gameScreen.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to KUVID game, ");
		lblNewLabel_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(276, 52, 245, 46);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_5 = new JLabel("you can initialize the game values");
		lblNewLabel_1_5.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 11));
		lblNewLabel_1_5.setBounds(276, 83, 245, 46);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("from the left side. Length L values");
		lblNewLabel_1_6.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 11));
		lblNewLabel_1_6.setBounds(276, 115, 245, 46);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("higher than 55 are recommended");
		lblNewLabel_1_7.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 11));
		lblNewLabel_1_7.setBounds(276, 147, 245, 46);
		contentPane.add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_7_1 = new JLabel("for a smooth gaming experience.");
		lblNewLabel_1_7_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 11));
		lblNewLabel_1_7_1.setBounds(276, 179, 245, 46);
		contentPane.add(lblNewLabel_1_7_1);
		
		JLabel lblNewLabel_1_7_1_1 = new JLabel("Have fun!!! ");
		lblNewLabel_1_7_1_1.setToolTipText("I mean it!!!");
		lblNewLabel_1_7_1_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 11));
		lblNewLabel_1_7_1_1.setBounds(276, 236, 245, 46);
		contentPane.add(lblNewLabel_1_7_1_1);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("ULTRA GRAPHICS");
		tglbtnNewToggleButton.setForeground(new Color(220, 20, 60));
		tglbtnNewToggleButton.setToolTipText("Only recommended for High-end PCs.");
		tglbtnNewToggleButton.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 11));
		tglbtnNewToggleButton.setBounds(179, 214, 132, 23);
		contentPane.add(tglbtnNewToggleButton);
		
		JLabel lblShieldNumber = new JLabel("Shield Number:");
		lblShieldNumber.setToolTipText("Number of shields in each type.");
		lblShieldNumber.setBounds(10, 33, 132, 21);
		contentPane.add(lblShieldNumber);
		
		moleculeBox = new JComboBox<String>();
		moleculeBox.setBounds(400, 33, 86, 22);
		contentPane.add(moleculeBox);
		moleculeBox.addItem("Straight");
		moleculeBox.addItem("Branched");
		
		spinBox = new JCheckBox();
		spinBox.setBounds(500, 33, 17, 17);
		contentPane.add(spinBox);
	}
}
