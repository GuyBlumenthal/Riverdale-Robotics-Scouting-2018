package scoutingapp.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import scoutingapp.commons.ScoutingApp;

public class SetMatchPoints extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField txtScore;

	/**
	 * Create the dialog.
	 */

	public SetMatchPoints(int matchID, boolean isRed) {

		setResizable(false);
		setModal(true);

		setBounds(100, 100, 185, 100);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel lblScore = new JLabel("Score:");
			contentPanel.add(lblScore);
		}
		{
			txtScore = new JTextField();
			contentPanel.add(txtScore);
			txtScore.setColumns(10);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						try {

							int score = Integer.parseInt(txtScore.getText());

							ScoutingApp.setScore(matchID, score, isRed);
							ScoutingApp.updateMatchView(matchID);

							dispose();

						} catch (NumberFormatException e) {

							JOptionPane.showMessageDialog(null, "Please fill in the form properly.", "Incomplete Form",
									JOptionPane.OK_OPTION);

						}

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
