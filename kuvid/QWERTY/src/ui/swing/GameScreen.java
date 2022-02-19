package ui.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import ui.handler.UIHandler;
import ui.objects.StatsUIHolder;


public class GameScreen extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	UIHandler gameListener = new UIHandler();
	static JLabel obj1 = new JLabel();
	static JLabel obj2 = new JLabel();
	static JRadioButton firstRadio;
    ButtonGroup radioGroup;
    static JRadioButton secondRadio;
    static JRadioButton thirdRadio;
    static JRadioButton fourthRadio;
    static JRadioButton firstRadio_1;
    static JRadioButton secondRadio_1;
    static JRadioButton thirdRadio_1;
    static JRadioButton fourthRadio_1;
    static StatsUIHolder statsHolder;

    JLabel lblTime;
    JLabel lblScore;
    JLabel lblHealth;
    JLabel sigmaCount;
    JLabel gammaCount;
    JLabel betaCount;
    JLabel alphaCount;
    
    JLabel alphabcount;
    JLabel betabcount;
    JLabel sigmabcount;
    JLabel gammabbcount;
    
    JLabel etacount;
    JLabel lotacount;
    JLabel thetacount;
    JLabel zetacount;
    JLabel nickname;
    
	public void updateStats() {
		String time;
		time = (statsHolder.getTime()/60 < 10) ? "0"+statsHolder.getTime()/60+":" : statsHolder.getTime()/60+":";
		time = (statsHolder.getTime()%60 < 10) ? time + "0"+statsHolder.getTime()%60 : time + statsHolder.getTime()%60;
		lblTime.setText("Time: " + time);
		lblScore.setText("Score: " + statsHolder.getScore());
		lblHealth.setText("Health: "+ statsHolder.getHealth());
		sigmaCount.setText(statsHolder.getSigma()+"");
		gammaCount.setText(statsHolder.getGamma()+"");
		betaCount.setText(statsHolder.getBeta()+"");
		alphaCount.setText(statsHolder.getAlpha()+"");
		alphabcount.setText(statsHolder.getAlphaPU()+"");
		betabcount.setText(statsHolder.getBetaPU()+"");
		sigmabcount.setText(statsHolder.getSigmaPU()+"");
		gammabbcount.setText(statsHolder.getGammaPU()+"");
		etacount.setText(statsHolder.getEta()+"");
	    lotacount.setText(statsHolder.getLota()+"");;
	    thetacount.setText(statsHolder.getTheta()+"");;
	    zetacount.setText(statsHolder.getZeta()+"");
	    nickname.setText(StatsUIHolder.NICKNAME);
	}
	
	public GameScreen(JPanel animation, StatsUIHolder StatisticHolder) {
		statsHolder = StatisticHolder;
		
		setResizable(true);
		pack();
		Insets insets = getInsets();
		int frameLeftBorder = insets.left;
		int frameRightBorder = insets.right;
		int frameTopBorder = insets.top;
		int frameBottomBorder = insets.bottom;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(StatsUIHolder.GAME_WIDTH +frameRightBorder+frameLeftBorder, StatsUIHolder.GAME_HEIGHT+frameTopBorder+frameBottomBorder);
		setContentPane(animation);
		
		int k = StatsUIHolder.LENGTH_L*13;
		int j = StatsUIHolder.LENGTH_L*14;
		
		//Stats labels
		
		lblHealth = new JLabel("Health: 100");
		lblHealth.setForeground(Color.RED);
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHealth.setBackground(new Color(184, 134, 11));
		lblHealth.setBounds(k, 109, 110, 30);
		getContentPane().add(lblHealth);

		lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTime.setBounds(k, 40, 90, 30);
		getContentPane().add(lblTime);

		lblScore = new JLabel("Score:");
		lblScore.setForeground(new Color(255, 0, 0));
		lblScore.setBackground(new Color(184, 134, 11));
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblScore.setBounds(k, 80, 110, 30);
		getContentPane().add(lblScore);
		
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "UP");
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "D");
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "A");
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("P"), "P");
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "R");
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("C"), "C");
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "S");
		obj1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("L"), "L");
		obj1.getActionMap().put("UP", new KeyAction().setComand("UP"));
		obj1.getActionMap().put("RIGHT", new KeyAction().setComand("RIGHT"));
		obj1.getActionMap().put("P", new KeyAction().setComand("P"));
		obj1.getActionMap().put("R", new KeyAction().setComand("R"));
		obj1.getActionMap().put("LEFT", new KeyAction().setComand("LEFT"));
		obj1.getActionMap().put("D", new KeyAction().setComand("D"));
		obj1.getActionMap().put("A", new KeyAction().setComand("A"));
		obj1.getActionMap().put("C", new KeyAction().setComand("C"));
		obj1.getActionMap().put("S", new KeyAction().setComand("S"));
		obj1.getActionMap().put("L", new KeyAction().setComand("L"));
		getContentPane().add(obj1);
		
		//Blender
		
		ImageIcon blender = new ImageIcon(getClass().getResource("/blender.png"));
		Image blen = blender.getImage().getScaledInstance(90, 90,  Image.SCALE_SMOOTH);
		JButton btnNewButton = new JButton(new ImageIcon(blen));
		btnNewButton.setBounds(k, 150, 90, 90);
		btnNewButton.setContentAreaFilled(false);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameListener.sendBlend();
			}
		});
		
		//Blend buttons
		
		firstRadio = new JRadioButton("");
		firstRadio.setForeground(Color.GRAY);
		firstRadio.setSelected(true);
		firstRadio.setBounds(k, 289, 21, 23);
		firstRadio.setContentAreaFilled(false);
		getContentPane().add(firstRadio);
		
		secondRadio = new JRadioButton("");
		secondRadio.setForeground(Color.GRAY);
		secondRadio.setBounds(k, 327, 21, 23);
		secondRadio.setContentAreaFilled(false);
		getContentPane().add(secondRadio);
		
		thirdRadio = new JRadioButton("");
		thirdRadio.setForeground(Color.GRAY);
		thirdRadio.setBounds(k, 368, 21, 23);
		thirdRadio.setContentAreaFilled(false);
		getContentPane().add(thirdRadio);

		
		fourthRadio = new JRadioButton("");
		fourthRadio.setForeground(Color.GRAY);
		fourthRadio.setBounds(k, 406, 21, 23);
		fourthRadio.setContentAreaFilled(false);
		getContentPane().add(fourthRadio);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(firstRadio);
		radioGroup.add(secondRadio);
		radioGroup.add(thirdRadio);
		radioGroup.add(fourthRadio);
		
		//Atom images
		
		ImageIcon sigma = new ImageIcon(getClass().getResource("/atoms/sigma.png"));
		JLabel lblNewLabel_2 = new JLabel(sigma);
		
		sigmaCount=new JLabel("");
		sigmaCount.setBounds((int) (j*1.036), 406, 36, 30);
		getContentPane().add(sigmaCount);
		
		lblNewLabel_2.setBounds(j, 406, 36, 30);
		getContentPane().add(lblNewLabel_2);
		
		ImageIcon gamma = new ImageIcon(getClass().getResource("/atoms/gamma.png"));
		JLabel lblNewLabel_2_1 = new JLabel(gamma);
		lblNewLabel_2_1.setBounds(j, 368, 36, 30);
		getContentPane().add(lblNewLabel_2_1);
		
		gammaCount=new JLabel("");
		gammaCount.setBounds((int) (j*1.036), 368, 36, 30);
		getContentPane().add(gammaCount);
		
		ImageIcon beta = new ImageIcon(getClass().getResource("/atoms/beta.png"));
		JLabel lblNewLabel_2_2 = new JLabel(beta);
		lblNewLabel_2_2.setBounds(j, 327, 36, 30);
		getContentPane().add(lblNewLabel_2_2);
		
		betaCount=new JLabel("");
		betaCount.setBounds((int) (j*1.036), 327, 36, 30);
		getContentPane().add(betaCount);
		
		ImageIcon alpha = new ImageIcon(getClass().getResource("/atoms/alpha.png"));
		JLabel lblNewLabel_2_3 = new JLabel(alpha);
		lblNewLabel_2_3.setBounds(j, 289, 35, 30);
		getContentPane().add(lblNewLabel_2_3);
		
		alphaCount=new JLabel("");
		alphaCount.setBounds((int) (j*1.036), 289, 36, 30);
		getContentPane().add(alphaCount);
		
		//Blend buttons
		
		firstRadio_1 = new JRadioButton("");
		firstRadio_1.setSelected(true);
		firstRadio_1.setForeground(Color.GRAY);
		firstRadio_1.setBounds((int) (j*0.97), 289, 21, 23);
		firstRadio_1.setContentAreaFilled(false);
		getContentPane().add(firstRadio_1);
		
		secondRadio_1 = new JRadioButton("");
		secondRadio_1.setForeground(Color.GRAY);
		secondRadio_1.setBounds((int) (j*0.97), 327, 21, 23);
		secondRadio_1.setContentAreaFilled(false);
		getContentPane().add(secondRadio_1);
		
		thirdRadio_1 = new JRadioButton("");
		thirdRadio_1.setForeground(Color.GRAY);
		thirdRadio_1.setBounds((int) (j*0.97), 368, 21, 23);
		thirdRadio_1.setContentAreaFilled(false);
		getContentPane().add(thirdRadio_1);
		
		fourthRadio_1 = new JRadioButton("");
		fourthRadio_1.setForeground(Color.GRAY);
		fourthRadio_1.setBounds((int) (j*0.97), 406, 21, 23);
		fourthRadio_1.setContentAreaFilled(false);
		getContentPane().add(fourthRadio_1);
		
		ButtonGroup radioGroupUse = new ButtonGroup();
		radioGroupUse.add(firstRadio_1);
		radioGroupUse.add(secondRadio_1);
		radioGroupUse.add(thirdRadio_1);
		radioGroupUse.add(fourthRadio_1);
		
		//Create - Use labels
		
		JLabel lblNewLabel_4 = new JLabel("Create");
		lblNewLabel_4.setBounds(k, 276, 45, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Use");
		lblNewLabel_4_1.setBounds((int) (j*0.97), 280, 36, 14);
		getContentPane().add(lblNewLabel_4_1);
		
		//Powerup buttons
		
		ImageIcon sigma_b = new ImageIcon(getClass().getResource("/powerups/+sigma-b.png"));
		Image sigmaim = sigma_b.getImage().getScaledInstance(StatsUIHolder.LENGTH_L*2/3, StatsUIHolder.LENGTH_L*2/3,  Image.SCALE_SMOOTH);
		JButton sigmab = new JButton(new ImageIcon(sigmaim));
		sigmab.setBounds(k, 435, (int) (StatsUIHolder.LENGTH_L*0.6), (int) (StatsUIHolder.LENGTH_L*0.6));
		sigmab.setContentAreaFilled(false);
		getContentPane().add(sigmab);
		sigmab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameListener.sendPowerupAction(3);
            }
        });
		
		sigmabcount = new JLabel("aaa");
		sigmabcount.setBounds((int) (k*1.05), 435, (int) (StatsUIHolder.LENGTH_L*0.6), (int) (StatsUIHolder.LENGTH_L*0.6));
		getContentPane().add(sigmabcount);
		
		ImageIcon beta_b = new ImageIcon(getClass().getResource("/powerups/+beta-b.png"));
		Image betaim = beta_b.getImage().getScaledInstance(StatsUIHolder.LENGTH_L*2/3, StatsUIHolder.LENGTH_L*2/3,  Image.SCALE_SMOOTH);
		JButton betab = new JButton(new ImageIcon(betaim));
		betab.setBounds(k+StatsUIHolder.LENGTH_L, 435, (int)(StatsUIHolder.LENGTH_L*0.6), (int)(StatsUIHolder.LENGTH_L*0.6));
		betab.setContentAreaFilled(false);
		getContentPane().add(betab);
		betab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameListener.sendPowerupAction(1);
            }
        });
		
		betabcount = new JLabel("aaa");
		betabcount.setBounds((int) (k*1.05)+StatsUIHolder.LENGTH_L, 435, (int) (StatsUIHolder.LENGTH_L*0.6), (int) (StatsUIHolder.LENGTH_L*0.6));
		getContentPane().add(betabcount);
		
		ImageIcon alha_b = new ImageIcon(getClass().getResource("/powerups/+alpha-b.png"));
		Image alphaim = alha_b.getImage().getScaledInstance(StatsUIHolder.LENGTH_L*2/3, StatsUIHolder.LENGTH_L*2/3,  Image.SCALE_SMOOTH);
		JButton alphab = new JButton(new ImageIcon(alphaim));
		alphab.setBounds(k, 435+StatsUIHolder.LENGTH_L*2/3, (int)(StatsUIHolder.LENGTH_L*0.6), (int)(StatsUIHolder.LENGTH_L*0.6));
		alphab.setContentAreaFilled(false);
		getContentPane().add(alphab);
		alphab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameListener.sendPowerupAction(0);
            }
        });
		
		alphabcount = new JLabel("aaa");
		alphabcount.setBounds((int) (k*1.05), 435+StatsUIHolder.LENGTH_L*2/3, (int) (StatsUIHolder.LENGTH_L*0.6), (int) (StatsUIHolder.LENGTH_L*0.6));
		getContentPane().add(alphabcount);
		
		ImageIcon gamma_b = new ImageIcon(getClass().getResource("/powerups/+gamma-b.png"));
		Image gammaim = gamma_b.getImage().getScaledInstance(StatsUIHolder.LENGTH_L*2/3, StatsUIHolder.LENGTH_L*2/3,  Image.SCALE_SMOOTH);
		JButton gammab = new JButton(new ImageIcon(gammaim));
		gammab.setBounds(k+StatsUIHolder.LENGTH_L, 435+StatsUIHolder.LENGTH_L*2/3, (int)(StatsUIHolder.LENGTH_L*0.6), (int)(StatsUIHolder.LENGTH_L*0.6));
		gammab.setContentAreaFilled(false);
		getContentPane().add(gammab);
		gammab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameListener.sendPowerupAction(2);
            }
        });
		
		gammabbcount = new JLabel("aaa");
		gammabbcount.setBounds((int) (k*1.05)+StatsUIHolder.LENGTH_L, 435+StatsUIHolder.LENGTH_L*2/3, (int) (StatsUIHolder.LENGTH_L*0.6), (int) (StatsUIHolder.LENGTH_L*0.6));
		getContentPane().add(gammabbcount);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(653, 134, 89, 79);
		getContentPane().add(lblNewLabel_1);
		
		//Shield buttons
		
		JButton shield1 = new JButton("Eta");
		shield1.setBounds(k, 542, StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L);
		shield1.setContentAreaFilled(false);
		getContentPane().add(shield1);
		shield1.addActionListener(this);
		
		etacount = new JLabel("");
		etacount.setBounds((int) (k*1.034), 552, StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L);
		getContentPane().add(etacount);
		
		JButton shield2 = new JButton("Lota");
		shield2.setBounds((int) (k*1.078), 542, StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L);
		shield2.setContentAreaFilled(false);
		getContentPane().add(shield2);
		shield2.addActionListener(this);
		
		lotacount = new JLabel("");
		lotacount.setBounds((int) (k*1.076+StatsUIHolder.LENGTH_L/2), 552, StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L);
		getContentPane().add(lotacount);
		
		JButton shield3 = new JButton("Theta");
		shield3.setBounds(k, 542+StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L);
		shield3.setContentAreaFilled(false);
		getContentPane().add(shield3);
		shield3.addActionListener(this);
		
		thetacount = new JLabel("");
		thetacount.setBounds((int) (k*1.034), 552+StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L);
		getContentPane().add(thetacount);
		
		nickname = new JLabel("aaaaaaaaa");
		nickname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nickname.setBounds(k, 20,  90, 30);
		getContentPane().add(nickname);
		
		
		JButton shield4 = new JButton("Zeta");
		shield4.setBounds((int) (k*1.078), 542+StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L);
		shield4.setContentAreaFilled(false);
		getContentPane().add(shield4);
		shield4.addActionListener(this);
		
		zetacount = new JLabel("");
		zetacount.setBounds((int) (k*1.076+StatsUIHolder.LENGTH_L/2), 552+StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L, StatsUIHolder.LENGTH_L);
		getContentPane().add(zetacount);

		
		new Timer(10, e-> {updateStats(); checkIfGameEnd();} ).start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gameListener.sendCommand(e);
	}

	boolean isShowed = false;
	public void checkIfGameEnd() {
		if(StatsUIHolder.isEND && !isShowed) {
			isShowed = true;
			gameListener.sendCommandPause(false);
			JFrame frame = new GameEndFrame();
			frame.setVisible(true);
		}
	}
	
	public static int getRadioCreate() {
        if(firstRadio.isSelected()) {
            return 0;
        }
        if(secondRadio.isSelected()) {
            return 1;
        }
        if(thirdRadio.isSelected()) {
            return 2;
        }
        return 3;
    }
	
    public static int getRadioUse() {
        if(firstRadio_1.isSelected()) {
            return 0;
        }
        if(secondRadio_1.isSelected()) {
            return 1;
        }
        if(thirdRadio_1.isSelected()) {
            return 2;
        }
        return 3;
    }
    
    private class KeyAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
    	private String command;
    	public KeyAction setComand(String command){
    		this.command = command;
    		return this;
    	}
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		
    		gameListener.sendCommandString(command);
    	}
    }
}
