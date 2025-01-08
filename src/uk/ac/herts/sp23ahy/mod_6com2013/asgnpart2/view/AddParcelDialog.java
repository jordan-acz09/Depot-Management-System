package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.controller.ParcelManager;
import uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model.Parcel;

public class AddParcelDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final ParcelManager parcelManager = ParcelManager.getInstance();
	private final JTextField txtParcelID = new JTextField();
	private final JSpinner spnDaysInDepot = new JSpinner(
			new SpinnerNumberModel(1, 1, 30, 1)),
			spnWeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D)),
			spnWidth = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D)),
			spnHeight = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D)),
			spnLength = new JSpinner(new SpinnerNumberModel(1D, .1, 100D, 1D));
	private final JButton btnAdd = new JButton("Add"),
			btnReset = new JButton("Reset");

	public AddParcelDialog(ParcelListFrame frame)
	{
		super(frame, "Add Parcel Dialog", true);

		JPanel pnlCenter = new JPanel(new GridLayout(6, 2)),
				pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		btnAdd.addActionListener(e -> add());
		btnReset.addActionListener(e -> reset());

		pnlCenter.add(new JLabel("Parcel ID: ", JLabel.RIGHT));
		pnlCenter.add(txtParcelID);
		pnlCenter.add(new JLabel("Days in Depot: ", JLabel.RIGHT));
		pnlCenter.add(spnDaysInDepot);
		pnlCenter.add(new JLabel("Weight (kg): ", JLabel.RIGHT));
		pnlCenter.add(spnWeight);
		pnlCenter.add(new JLabel("Width (cm): ", JLabel.RIGHT));
		pnlCenter.add(spnWidth);
		pnlCenter.add(new JLabel("Height (cm): ", JLabel.RIGHT));
		pnlCenter.add(spnHeight);
		pnlCenter.add(new JLabel("Length (cm): ", JLabel.RIGHT));
		pnlCenter.add(spnLength);

		pnlSouth.add(btnAdd);
		pnlSouth.add(btnReset);

		this.add(pnlCenter, BorderLayout.CENTER);
		this.add(pnlSouth, BorderLayout.SOUTH);

		this.setSize(320, 240);
		this.setResizable(false);
		this.setLocationRelativeTo(frame);
		this.setVisible(true);
	}

	private void add()
	{
		Parcel parcel = new Parcel();

		parcel.setParcelID(txtParcelID.getText());
		parcel.setDaysInDepot((int) spnDaysInDepot.getValue());
		parcel.setWeight((double) spnWeight.getValue());
		parcel.setWidth((double) spnWidth.getValue());
		parcel.setHeight((double) spnHeight.getValue());
		parcel.setLength((double) spnLength.getValue());

		if (parcelManager.addParcel(parcel))
		{
			JOptionPane.showMessageDialog(this, "Parcel successfully added.",
					getTitle(), JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
		else
			JOptionPane.showMessageDialog(this,
					"Unable to add parcel. Please try again.", getTitle(),
					JOptionPane.WARNING_MESSAGE);
	}

	private void reset()
	{
		txtParcelID.setText("");
		spnDaysInDepot.setValue(1);
		spnWeight.setValue(1);
		spnWidth.setValue(1);
		spnHeight.setValue(1);
		spnLength.setValue(1);
	}
}
