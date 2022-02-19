package ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.objects.StatsUIHolder;

public class GameEndFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public GameEndFrame() {
		setBounds(100, 100, 400, 190);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton okButton = new JButton("OK");
		okButton.setBounds(10, 105, 90, 35);
		contentPane.add(okButton);
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JLabel info = new JLabel(StatsUIHolder.END_MESSAGE);
		info.setBounds(20, 20, 250, 30);
		contentPane.add(info);

		JLabel info1 = new JLabel("PLAYER: " + StatsUIHolder.NICKNAME);
		info1.setBounds(20, 40, 130, 26);
		contentPane.add(info1);
		
		JLabel score = new JLabel("SCORE: " + StatsUIHolder.END_SCORE);
		score.setBounds(20, 60, 130, 26);
		contentPane.add(score);
		
		
	}
}
