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
import javax.swing.UIManager;
import java.awt.Color;

public class SetPowerUps extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private int matchID;
	private JTextField txtBoost;
	private JTextField txtForce;

	JComboBox<Integer> cmbBoost = new JComboBox<Integer>();
	JComboBox<Integer> cmbForce = new JComboBox<Integer>();

	/**
	 * Create the dialog.
	 */

	public SetPowerUps(int matchID, boolean isRed) {

		setResizable(false);
		setModal(true);

		for (int i = 1; i < 4; i++) {
			cmbBoost.addItem(i);
			cmbForce.addItem(i);
		}

		setBounds(100, 100, 229, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel pnlPowerUps = new JPanel();
		pnlPowerUps.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Power Ups",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlPowerUps.setBounds(10, 11, 205, 206);
		contentPanel.add(pnlPowerUps);
		pnlPowerUps.setLayout(null);

		JLabel lblBoost = new JLabel("Boost:");
		lblBoost.setBounds(10, 28, 46, 14);
		pnlPowerUps.add(lblBoost);

		txtBoost = new JTextField();
		txtBoost.setBounds(87, 25, 86, 20);
		pnlPowerUps.add(txtBoost);
		txtBoost.setColumns(10);

		JLabel lblForce = new JLabel("Force:");
		lblForce.setBounds(10, 104, 46, 14);
		pnlPowerUps.add(lblForce);

		txtForce = new JTextField();
		txtForce.setBounds(87, 101, 86, 20);
		pnlPowerUps.add(txtForce);
		txtForce.setColumns(10);

		JCheckBox chckbxLevitated = new JCheckBox("Levitated");
		chckbxLevitated.setBounds(87, 176, 97, 23);
		pnlPowerUps.add(chckbxLevitated);

		int[] powers;

		if (isRed) {
			powers = ScoutingApp.regionalCollection().getMatch(matchID).getRedPowerups();
		} else {
			powers = ScoutingApp.regionalCollection().getMatch(matchID).getBluePowerups();
		}

		txtBoost.setText((powers[0] == -1) ? "" : Integer.toString(powers[0]));
		txtForce.setText((powers[1] == -1) ? "" : Integer.toString(powers[1]));
		chckbxLevitated.setSelected(powers[2] == 1);

		cmbBoost.setBounds(87, 56, 46, 20);
		pnlPowerUps.add(cmbBoost);

		cmbForce.setBounds(87, 132, 46, 20);
		pnlPowerUps.add(cmbForce);
		
		
		cmbBoost.setSelectedItem((isRed) ? ScoutingApp.regionalCollection().getMatch(matchID).getRedPowerUpCubes()[0] : ScoutingApp.regionalCollection().getMatch(matchID).getBluePowerUpCubes()[0]);
		cmbForce.setSelectedItem((isRed) ? ScoutingApp.regionalCollection().getMatch(matchID).getRedPowerUpCubes()[1] : ScoutingApp.regionalCollection().getMatch(matchID).getBluePowerUpCubes()[1]);

		JLabel lblCubes = new JLabel("Cubes:");
		lblCubes.setBounds(10, 59, 46, 14);
		pnlPowerUps.add(lblCubes);

		JLabel label = new JLabel("Cubes:");
		label.setBounds(10, 135, 46, 14);
		pnlPowerUps.add(label);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int[] powerUps = {
								(txtBoost.getText().equals("")) ? -1
										: Integer.parseInt(txtBoost.getText()),
								(txtForce.getText().equals("")) ? -1
										: Integer.parseInt(txtForce.getText()),
								(chckbxLevitated.isSelected() ? 1 : 0) };
						
						int[] powerUpCubes = {
								Integer.parseInt(String.valueOf(cmbBoost.getSelectedItem())),
								Integer.parseInt(String.valueOf(cmbForce.getSelectedItem()))
						};

						ScoutingApp.setPowerUps(matchID, powerUps, powerUpCubes, isRed);
						ScoutingApp.updateMatchView(matchID);

						dispose();

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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
