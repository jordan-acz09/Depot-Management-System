package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeSet;

import uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model.Customer;

public class CustomerManager
{
	private final static CustomerManager INSTANCE = new CustomerManager();
	private final TreeSet<Customer> customers = new TreeSet<>();

	public static CustomerManager getInstance()
	{
		return INSTANCE;
	}

	private CustomerManager()
	{
		ParcelManager parcelManager = ParcelManager.getInstance();

		try (BufferedReader br = new BufferedReader(
				new FileReader("Custs.csv")))
		{
			br.lines().map(input -> input.split(","))
					.map(array -> new Customer(array[0],
							parcelManager.getParcel(array[1])))
					.forEach(customers::add);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void printCustomers()
	{
		for (Customer customer : customers)
			System.out.println(customer + ": " + customer.getParcel());
	}
}
