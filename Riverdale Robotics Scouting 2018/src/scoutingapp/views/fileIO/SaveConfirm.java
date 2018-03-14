package scoutingapp.views.fileIO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import scoutingapp.commons.ScoutingApp;
import scoutingapp.commons.fileIO.DirectoryAction;

public class SaveConfirm extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8968875710919584812L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * 
	 * @param open
	 *            <code>true</code> for open file and <code>false</code> for new
	 *            collection
	 * 
	 */
	public SaveConfirm(DirectoryAction action) {
		setBounds(100, 100, 450, 105);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblTheCollectionIs = new JLabel(
					"The collection is still unsaved. Would you like to save before continuing?");
			contentPanel.add(lblTheCollectionIs);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						ScoutingApp.saveCollection();

						if (action == DirectoryAction.OPEN) {
							ScoutingApp.openCollection();
						} else if (action == DirectoryAction.NEW) {
							ScoutingApp.newCollection();
						} else if (action == DirectoryAction.MERGE) {
							ScoutingApp.mergeCollection();
						}

						dispose();

					}
				});
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
			}
			{
				JButton btnNoSave = new JButton("Don't Save");
				btnNoSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						ScoutingApp.setSaved();
						dispose();

						if (action == DirectoryAction.OPEN) {
							ScoutingApp.openCollection();
						} else if (action == DirectoryAction.NEW) {
							ScoutingApp.newCollection();
						} else if (action == DirectoryAction.MERGE) {
							ScoutingApp.mergeCollection();
						}

					}
				});
				btnNoSave.setActionCommand("OK");
				buttonPane.add(btnNoSave);
				getRootPane().setDefaultButton(btnNoSave);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						dispose();

					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}

}
