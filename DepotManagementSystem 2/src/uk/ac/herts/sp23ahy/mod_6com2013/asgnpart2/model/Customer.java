package src.uk.ac.herts.sp23ahy.mod_6com2013.asgnpart2.model;

import java.io.Serializable;

public class Customer implements Serializable, Comparable<Customer>
{
    private static final long serialVersionUID = 1L;
    private String firstName, lastName;
    private Parcel parcel;

    public Customer(String name, Parcel parcel)
    {
        String[] temp = name.split(" ");
        this.firstName = temp[0];
        this.lastName = temp[1];
        this.parcel = parcel;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Parcel getParcel()
    {
        return parcel;
    }
    
    public Parcel getParcelID()
    {
        return parcel;
    }
    
    public void setParcel(Parcel parcel)
    {
        this.parcel = parcel;
    }

    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Customer that
                && toString().equals(that.toString());
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }

    @Override
    public int compareTo(Customer that)
    {
        return lastName.compareTo(that.lastName);
    }
}
