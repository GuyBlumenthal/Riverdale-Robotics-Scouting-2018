package scoutingapp.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import scoutingapp.commons.ScoutingApp;

public class SetMatchPoints extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private int matchID;
	private JTextField txtForceBlue;
	private JTextField txtForceRed;

	/**
	 * Create the dialog.
	 */

	public SetMatchPoints(int matchID) {

		setResizable(false);
		setModal(true);

		setBounds(100, 100, 450, 137);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel pnlBlue = new JPanel();
		pnlBlue.setBorder(new TitledBorder(null, "Blue", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlBlue.setBounds(10, 11, 205, 55);
		contentPanel.add(pnlBlue);
		pnlBlue.setLayout(null);

		JLabel lblForceBlue = new JLabel("Force:");
		lblForceBlue.setBounds(10, 25, 46, 14);
		pnlBlue.add(lblForceBlue);

		txtForceBlue = new JTextField();
		txtForceBlue.setBounds(87, 22, 86, 20);
		pnlBlue.add(txtForceBlue);
		txtForceBlue.setColumns(10);

		JPanel pnlRed = new JPanel();
		pnlRed.setBorder(new TitledBorder(null, "Red", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRed.setBounds(225, 11, 199, 55);
		contentPanel.add(pnlRed);
		pnlRed.setLayout(null);

		JLabel lblForceRed = new JLabel("Force:");
		lblForceRed.setBounds(10, 25, 46, 14);
		pnlRed.add(lblForceRed);

		txtForceRed = new JTextField();
		txtForceRed.setBounds(83, 22, 86, 20);
		pnlRed.add(txtForceRed);
		txtForceRed.setColumns(10);

		int[] bluePowers = ScoutingApp.regionalCollection().getMatch(matchID).getBluePowerups();
		int[] redPowers = ScoutingApp.regionalCollection().getMatch(matchID).getRedPowerups();
		txtForceBlue.setText(
				(bluePowers[1] == -1) ? "" : ScoutingApp.regionalCollection().secondsToStandard(bluePowers[1]));
		txtForceRed
				.setText((redPowers[1] == -1) ? "" : ScoutingApp.regionalCollection().secondsToStandard(redPowers[1]));

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
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
