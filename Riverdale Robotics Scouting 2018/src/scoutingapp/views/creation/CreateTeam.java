package scoutingapp.views.creation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import scoutingapp.commons.ExistingException;
import scoutingapp.views.TeamHub;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateTeam extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumber;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			CreateTeam dialog = new CreateTeam();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateTeam() {

		setModal(true);

		setBounds(100, 100, 383, 110);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTeamNumber = new JLabel("Team Number");
		lblTeamNumber.setBounds(10, 11, 78, 14);
		contentPanel.add(lblTeamNumber);

		txtNumber = new JTextField();
		txtNumber.setBounds(85, 8, 86, 20);
		contentPanel.add(txtNumber);
		txtNumber.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(266, 8, 86, 20);
		contentPanel.add(txtName);

		JLabel lblTeamName = new JLabel("Team Name");
		lblTeamName.setBounds(191, 11, 78, 14);
		contentPanel.add(lblTeamName);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					TeamHub.regionalCollection.createTeam(Integer.parseInt(txtNumber.getText()), txtName.getText());
					dispose();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Please fill in the team number with a number.",
							"Improper Team Number", JOptionPane.OK_OPTION);
				} catch (ExistingException e) {
					JOptionPane.showMessageDialog(null, "This team already exists!", "Exisiting Team",
							JOptionPane.OK_OPTION);
				}

			}
		});
		okButton.setBounds(231, 37, 47, 23);
		contentPanel.add(okButton);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setBounds(287, 37, 65, 23);
		contentPanel.add(cancelButton);
		cancelButton.setActionCommand("Cancel");
	}

}
