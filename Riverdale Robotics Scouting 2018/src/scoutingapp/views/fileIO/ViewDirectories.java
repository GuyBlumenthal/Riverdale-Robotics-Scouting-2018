package scoutingapp.views.fileIO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import scoutingapp.commons.ScoutingApp;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewDirectories extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<String> lstCollections;

	/**
	 * Create the dialog.
	 */
	public ViewDirectories() {
		setBounds(100, 100, 285, 324);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel
				.setBorder(new TitledBorder(null, "Collections", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				lstCollections = new JList();
				scrollPane.setViewportView(lstCollections);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						int[] selections = lstCollections.getSelectedIndices();

						if (selections.length > 1) {

							JOptionPane.showMessageDialog(null, "Please select one collection at a time.",
									"Incorrect Selection", JOptionPane.OK_OPTION);

						} else if (selections.length == 0) {

							JOptionPane.showMessageDialog(null, "Please select a collection", "Incorrect Selection",
									JOptionPane.OK_OPTION);

						} else {

							ScoutingApp.openCollection((String) lstCollections.getSelectedValue());
							dispose();

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

		updateCollectionList();

	}

	public void updateCollectionList() {

		File folder = new File("collections");
		File[] listOfFiles = folder.listFiles();

		DefaultListModel<String> validFiles = new DefaultListModel<String>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {

				String name = listOfFiles[i].getName();

				if (name.substring(name.length() - 4, name.length()).equals("." + ScoutingApp.FILE_EXTENSION)) {

					validFiles.addElement(name.substring(0, name.length() - 4));

				}

			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}

		lstCollections.setModel(validFiles);

	}
}
