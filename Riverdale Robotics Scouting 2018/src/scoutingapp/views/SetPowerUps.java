package scoutingapp.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import scoutingapp.commons.ScoutingApp;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class SetPowerUps extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private int matchID;
	private JTextField txtBoostBlue;
	private JTextField txtForceBlue;
	private JTextField txtBoostRed;
	private JTextField txtForceRed;
	
	MatchOverview parent;
	SetPowerUps me = this;
	
	JComboBox<Integer> cmbBlueBoost = new JComboBox<Integer>();
	JComboBox<Integer> cmbBlueForce = new JComboBox<Integer>();
	
	JComboBox<Integer> cmbBoostRed = new JComboBox<Integer>();
	JComboBox<Integer> cmbRedForce = new JComboBox<Integer>();

	/**
	 * Create the dialog.
	 */
	
	public SetPowerUps(int matchID, MatchOverview parent) {
		setResizable(false);
		
		for (int i = 1; i < 4; i++) {
			cmbBlueBoost.addItem(i);
			cmbBlueForce.addItem(i);
			
			cmbBoostRed.addItem(i);
			cmbRedForce.addItem(i);
		}
		
		this.parent = parent;
		this.matchID = matchID;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pnlBlue = new JPanel();
		pnlBlue.setBorder(new TitledBorder(null, "Blue", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlBlue.setBounds(10, 11, 205, 206);
		contentPanel.add(pnlBlue);
		pnlBlue.setLayout(null);
		
		JLabel lblBoostBlue = new JLabel("Boost:");
		lblBoostBlue.setBounds(10, 28, 46, 14);
		pnlBlue.add(lblBoostBlue);
		
		txtBoostBlue = new JTextField();
		txtBoostBlue.setBounds(87, 25, 86, 20);
		pnlBlue.add(txtBoostBlue);
		txtBoostBlue.setColumns(10);
		
		JLabel lblForceBlue = new JLabel("Force:");
		lblForceBlue.setBounds(10, 104, 46, 14);
		pnlBlue.add(lblForceBlue);
		
		txtForceBlue = new JTextField();
		txtForceBlue.setBounds(87, 101, 86, 20);
		pnlBlue.add(txtForceBlue);
		txtForceBlue.setColumns(10);
		
		JCheckBox chckbxLevitatedBlue = new JCheckBox("Levitated");
		chckbxLevitatedBlue.setBounds(87, 176, 97, 23);
		pnlBlue.add(chckbxLevitatedBlue);
		
		JPanel pnlRed = new JPanel();
		pnlRed.setBorder(new TitledBorder(null, "Red", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRed.setBounds(225, 11, 199, 206);
		contentPanel.add(pnlRed);
		pnlRed.setLayout(null);
		
		JLabel lblBoostRed = new JLabel("Boost:");
		lblBoostRed.setBounds(10, 26, 46, 14);
		pnlRed.add(lblBoostRed);
		
		txtBoostRed = new JTextField();
		txtBoostRed.setBounds(83, 23, 86, 20);
		pnlRed.add(txtBoostRed);
		txtBoostRed.setColumns(10);
		
		JLabel lblForceRed = new JLabel("Force:");
		lblForceRed.setBounds(10, 102, 46, 14);
		pnlRed.add(lblForceRed);
		
		txtForceRed = new JTextField();
		txtForceRed.setBounds(83, 99, 86, 20);
		pnlRed.add(txtForceRed);
		txtForceRed.setColumns(10);
		
		JCheckBox chckbxLevitatedRed = new JCheckBox("Levitated");
		chckbxLevitatedRed.setBounds(83, 176, 97, 23);
		pnlRed.add(chckbxLevitatedRed);
		
		int[] bluePowers = ScoutingApp.regionalCollection().getMatch(matchID).getBluePowerups();
		int[] redPowers = ScoutingApp.regionalCollection().getMatch(matchID).getRedPowerups();
		
			
		txtBoostBlue.setText((bluePowers[0] == -1) ? "" : ScoutingApp.regionalCollection().secondsToStandard(bluePowers[0]));
		txtForceBlue.setText((bluePowers[1] == -1) ? "" : ScoutingApp.regionalCollection().secondsToStandard(bluePowers[1]));
		chckbxLevitatedBlue.setSelected(bluePowers[2] == 1);
		
		cmbBlueBoost.setBounds(87, 56, 46, 20);
		pnlBlue.add(cmbBlueBoost);
		
		cmbBlueForce.setBounds(87, 132, 46, 20);
		pnlBlue.add(cmbBlueForce);
		
		JLabel lblCubes = new JLabel("Cubes:");
		lblCubes.setBounds(10, 59, 46, 14);
		pnlBlue.add(lblCubes);
		
		JLabel label = new JLabel("Cubes:");
		label.setBounds(10, 135, 46, 14);
		pnlBlue.add(label);
			
		txtBoostRed.setText((redPowers[0] == -1) ? "" : ScoutingApp.regionalCollection().secondsToStandard(redPowers[0]));
		txtForceRed.setText((redPowers[1] == -1) ? "" : ScoutingApp.regionalCollection().secondsToStandard(redPowers[1]));
		chckbxLevitatedRed.setSelected(redPowers[2] == 1);
		
		cmbBoostRed.setBounds(83, 54, 46, 20);
		pnlRed.add(cmbBoostRed);
		
		cmbRedForce.setBounds(83, 130, 46, 20);
		pnlRed.add(cmbRedForce);
		
		JLabel label_1 = new JLabel("Cubes:");
		label_1.setBounds(10, 57, 46, 14);
		pnlRed.add(label_1);
		
		JLabel label_2 = new JLabel("Cubes:");
		label_2.setBounds(10, 133, 46, 14);
		pnlRed.add(label_2);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int[] redPowerups = {(txtBoostRed.getText().equals("")) ? -1 : ScoutingApp.regionalCollection().standardToSeconds(txtBoostRed.getText()), (txtForceRed.getText().equals("")) ? -1 : ScoutingApp.regionalCollection().standardToSeconds(txtForceRed.getText()),
								(chckbxLevitatedRed.isSelected() ? 1 : 0)};
						int[] bluePowerups = {(txtBoostBlue.getText().equals("")) ? -1 : ScoutingApp.regionalCollection().standardToSeconds(txtBoostBlue.getText()), (txtForceBlue.getText().equals("")) ? -1 : ScoutingApp.regionalCollection().standardToSeconds(txtForceBlue.getText()),
								(chckbxLevitatedBlue.isSelected() ? 1 : 0)};
						
						ScoutingApp.regionalCollection().setPowerUps(matchID, redPowerups, true);
						ScoutingApp.regionalCollection().setPowerUps(matchID, bluePowerups, false);
						
						parent.updatePowers();
						me.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						me.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
