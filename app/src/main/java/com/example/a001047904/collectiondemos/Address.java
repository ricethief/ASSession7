package com.example.a001047904.collectiondemos;


/**
 *  The <code>Address</code> class stores postal address data.
 * @author  Santi Ruiz
 * @version 1
 * @since   March 2002
 *
 */
import java.io.*;

@SuppressWarnings("rawtypes")
public class Address implements Comparable {

//---------------------- CONSTANTS ---------------------------------------------
  public static final Address DEF_ADDRESS = new Address();
  public static final String DEF_STREET_NUMBER = "NOT SET";
  public static final String DEF_STREET_NAME = "NOT SET";
  public static final String DEF_SUBURB = "NOT SET";
  public static final String DEF_STATE = "S.A.";
  public static final String DEF_COUNTRY = "Australia";
  public static final String DEF_POSTCODE = "NOT SET";
//---------------------- INSTANCE VARIABLES-------------------------------------
  private String streetNumber;
  private String streetName;
  private String suburb;
  private String state;
  private String country;
  private String postcode;

//---------------------- CONSTRUCTORS ------------------------------------------
  public Address() {
    this(DEF_STREET_NUMBER, DEF_STREET_NAME, DEF_SUBURB,
            DEF_STATE, DEF_COUNTRY, DEF_POSTCODE);
  }

//------------------------------------------------------------------------------
  public Address(Address a) {
    this(a.getStreetNumber(), a.getStreetName(), a.getSuburb(), a.getState(),
            a.getCountry(), a.getPostcode());
  }

//------------------------------------------------------------------------------
  public Address(String streetNumber, String streetName, String suburb,
          String state, String country, String postcode) {
    this.streetNumber = streetNumber;
    this.streetName = streetName;
    this.suburb = suburb;
    this.state = state;
    this.country = country;
    this.postcode = postcode;
  }

//-------------------------- GETTERS -------------------------------------------
  public String getStreetNumber() {
    return this.streetNumber;
  }

  public String getStreetName() {
    return this.streetName;
  }

  public String getSuburb() {
    return this.suburb;
  }

  public String getState() {
    return this.state;
  }

  public String getCountry() {
    return this.country;
  }

  public String getPostcode() {
    return this.postcode;
  }

//------------------------ SETTERS --------------------------------------------
  public Address setCountry(String country) {
    this.country = country;
    return this;
  }

  public Address setPostcode(String postcode) {
    this.postcode = postcode;
    return this;
  }

  public Address setStreetName(String streetName) {
    this.streetName = streetName;
    return this;
  }

  public Address setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
    return this;
  }

  public Address setState(String state) {
    this.state = state;
    return this;
  }

  public Address setSuburb(String suburb) {
    this.suburb = suburb;
    return this;
  }

  public void writeToFile(DataOutputStream theFile) throws IOException {
    theFile.writeUTF(this.streetNumber);
    theFile.writeUTF(this.streetName);
    theFile.writeUTF(this.suburb);
    theFile.writeUTF(this.state);
    theFile.writeUTF(this.country);
    theFile.writeUTF(this.postcode);
  }

  public void readFromFile(DataInputStream theFile) throws IOException {
    this.streetNumber = theFile.readUTF();
    this.streetName = theFile.readUTF();
    this.suburb = theFile.readUTF();
    this.state = theFile.readUTF();
    this.country = theFile.readUTF();
    this.postcode = theFile.readUTF();
  }

  //---------------------------- STANDARD OBJECT METHODS --------------------------------
  public String toString() {
    return ("\n[" +
            super.toString() +
            ",streetNumber= " + this.streetNumber +
            ",streetName= " + this.streetName +
            ",suburb= " + this.suburb +
            ",state= " + this.state +
            ",postcode= " + this.postcode +
            ",country= " + this.country +
            "]");
  }


  //---------------------------- COMPARABLE INTERFACE METHODS --------------------------------
  public int compareTo(Object obj) {
    // compareTo should return a 0 if this is equal to the oobject passed in obj
    // -1 if this is less than (<) than obj and +1 if this is greater than (>)
    // obj
    Address otherAddress;

    otherAddress = (Address) obj;

    // A simple but poor compareTo would be based on postcode. This, of course
    // would cause many Address objects to be considered equal (all addresses
    // from the same suburb). UnComment the following line if you wish to try
    // out the demo programs using postcode as the basis for comparing Address
    // objects
    // return (postcode.compareTo(otherAddress.getPostcode()));

    // A more sensible comparison would be based on street number and street
    // name and postcode although the definition of "<" and ">" has little
    // real meaning

    String thisKey;
    String otherKey;
    int result;

    thisKey = this.postcode;
    otherKey = otherAddress.postcode;
    result = thisKey.compareTo(otherKey);
    if (result == 0) {
      thisKey = this.streetName;
      otherKey = otherAddress.streetName;
      result = thisKey.compareTo(otherKey);
      if (result == 0) {
        thisKey = this.streetNumber;
        otherKey = otherAddress.streetNumber;
        result = thisKey.compareTo(otherKey);
      }
    }

    return result;
  }


  //---------------------------- METHODS REQUIRED FOR HASHING ------------
  public boolean equals(Object obj) {

    if (!(obj instanceof Address)){
      return false;
    }

    Address otherAddress = (Address) obj;
    return (postcode.equals(otherAddress.getPostcode()) &&
            streetName.equalsIgnoreCase(otherAddress.getStreetName()) &&
            streetNumber.equals(otherAddress.getStreetNumber()));
  }

  /* The following method is needed to get any classes that use hashing
   * (hashSet or hashMap) to work properly.
   *
   */
  public int hashCode() {
    // NOTE : it is quite common for classes to create a String from the data
    // and then rely on the String classes hashCode method for the hash code
    String thisHashStr;

    thisHashStr = this.getStreetNumber() + this.getStreetName() + this.getPostcode();

    return (thisHashStr.hashCode());

  }
}
