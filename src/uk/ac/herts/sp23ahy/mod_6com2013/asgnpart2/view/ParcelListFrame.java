package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.controller.ParcelManager;
import uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model.Parcel;

public class ParcelListFrame extends JFrame
// XXX: 1st Option: JFrame as listener
// implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private final ParcelManager parcelManager = ParcelManager.getInstance();
	private final JTable tblParcel = new JTable();
	private final JButton btnAdd = new JButton("Add Parcel"),
			btnMark = new JButton("Mark as Collected"),
			btnSave = new JButton("Save Report");

	public ParcelListFrame() throws Exception
	{
		super("Parcel List - Depot Management System");

		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		// XXX: 1st Option: JFrame as listener
		// btnAdd.addActionListener(this);
		// btnMark.addActionListener(this);

		// XXX: 2nd Option: Named inner class as listener
		// btnAdd.addActionListener(new AddButtonListener());
		// btnMark.addActionListener(new MarkButtonListener());

		// XXX: 3rd Option: Anonymous inner class as listener
		// btnAdd.addActionListener(new ActionListener()
		// {
		// @Override
		// public void actionPerformed(ActionEvent e)
		// {
		// System.out.println("Add button clicked");
		// }
		// });
		//
		// btnMark.addActionListener(new ActionListener()
		// {
		// @Override
		// public void actionPerformed(ActionEvent e)
		// {
		// System.out.println("Mark button clicked");
		// }
		// });

		// XXX: 4th Option: Using lambda method
		btnAdd.addActionListener(e -> add());
		btnMark.addActionListener(e -> mark());
		btnSave.addActionListener(e -> save());

		tblParcel.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		display();

		pnlSouth.add(btnAdd);
		pnlSouth.add(btnMark);
		pnlSouth.add(btnSave);

		this.add(new JScrollPane(tblParcel), BorderLayout.CENTER);
		this.add(pnlSouth, BorderLayout.SOUTH);

		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void add()
	{
		new AddParcelDialog(this);
		display();
	}

	private void mark()
	{
		int row = tblParcel.getSelectedRow();

		if (row != -1)
		{
			String parcelID = (String) tblParcel.getModel().getValueAt(row, 0);

			if (parcelID != null)
			{
				Parcel parcel = parcelManager.getParcel(parcelID);

				if (parcel != null && JOptionPane.showConfirmDialog(this,
						"Are you sure want to mark this parcel as collected?",
						getTitle(),
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{
					if (parcelManager.deleteParcel(parcel))
					{
						JOptionPane.showMessageDialog(this,
								"Parcel " + parcel + " successfully collected.",
								getTitle(), JOptionPane.INFORMATION_MESSAGE);
						display();
					}
				}
			}
			else
				JOptionPane.showMessageDialog(this,
						"Unable to read the parcel ID.", getTitle(),
						JOptionPane.WARNING_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(this,
					"Please select a parcel to mark as collected.", getTitle(),
					JOptionPane.WARNING_MESSAGE);
	}

	private void save()
	{
		if (parcelManager.saveParcels())
		{
			btnSave.setEnabled(false);
			JOptionPane.showMessageDialog(this,
					"Successfully saved the operations.", getTitle(),
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void display()
	{
		btnSave.setEnabled(parcelManager.isModified());
		tblParcel.setModel(new DefaultTableModel(
				parcelManager.getParcels().stream().map(parcel -> new String[] {
						parcel.getParcelID(),
						parcel.getDaysInDepot() + " day(s)",
						parcel.getWeight() + " kg(s)",
						parcel.getWidth() + " cm(s) x " + parcel.getHeight()
								+ " cm(s) x " + parcel.getLength() + " cm(s)"})
						.toArray(String[][]::new),
				new String[] {"Parcel ID", "Days in Depot", "Weight",
						"Dimension"}));
	}

	// XXX: 1st Option: JFrame as listener
	// @Override
	// public void actionPerformed(ActionEvent event)
	// {
	// if (event.getSource() == btnAdd)
	// System.out.println("Add button clicked");
	// else if (event.getSource() == btnMark)
	// System.out.println("Mark button clicked");
	// }

	// XXX: 2nd Option: Named inner class as listener
	// private class AddButtonListener implements ActionListener
	// {
	// @Override
	// public void actionPerformed(ActionEvent e)
	// {
	// System.out.println("Add button clicked");
	// }
	// }
	//
	// private class MarkButtonListener implements ActionListener
	// {
	// @Override
	// public void actionPerformed(ActionEvent e)
	// {
	// System.out.println("Mark button clicked");
	// }
	// }

	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		new ParcelListFrame();
	}
}
