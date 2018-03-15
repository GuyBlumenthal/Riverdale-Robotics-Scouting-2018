package scoutingapp.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import scoutingapp.commons.ScoutingApp;

public class PitScouting extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;

	/**
	 * Create the dialog.
	 */
	public PitScouting(int teamNumber, boolean editable) {

		setBounds(100, 100, 450, 300);
		setVisible(true);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				textArea = new JTextArea();
				textArea.setText(ScoutingApp.getPitScouting(teamNumber));
				textArea.setEditable(editable);
				textArea.setMargin(new Insets(20, 2, 2, 2));
				scrollPane.setViewportView(textArea);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			if (!editable) {
				{
					JButton btnEdit = new JButton("Edit");
					btnEdit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							setVisible(false);
							PitScouting pit = new PitScouting(teamNumber, true);
							dispose();

						}
					});
					buttonPane.add(btnEdit);
				}
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						ScoutingApp.setPitScouting(teamNumber, textArea.getText());
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
					public void actionPerformed(ActionEvent e) {

						dispose();

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
