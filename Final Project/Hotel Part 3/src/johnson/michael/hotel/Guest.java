package johnson.michael.hotel;

import java.io.Serializable;

/**
 * Represents a guest and their party.
 */
public class Guest implements Serializable {
  /**
   * The guest's first name.
   */
  private String firstName;
  /**
   * The guest's last name.
   */
  private String lastName;
  /**
   * The guest's address, including street, city, state, and ZIP code.
   */
  private String address;
  /**
   * The number of children in the guest's party.
   */
  private int numberChildrenInParty;
  /**
   * The number of adults in the guest's party.
   */
  private int numberAdultsInParty;

  @Override
  public String toString() {
    return this.firstName + " " + this.lastName + "\nLiving at:\n" + this.address + "\nParty of "
        + this.numberAdultsInParty + " adults and " + this.numberChildrenInParty + " children";
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof Guest)) {
      // We're comparing Guest to another class: they're never equal.
      return false;
    }

    final Guest other = (Guest) obj;

    // Two Guest objects with the same first name, last name, and address are equal.
    if (!this.getFirstName().equals(other.getFirstName())) {
      return false;
    }
    if (!this.getLastName().equals(other.getLastName())) {
      return false;
    }
    if (!this.getAddress().equals(other.getAddress())) {
      return false;
    }

    return true;
  }

  /**
   * Retrieves the guest's first name.
   * @return The guest's first name.
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Sets the guest's first name.
   * @param firstName The new first name for the guest.
   */
  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  /**
   * Retrieves the guest's last name.
   * @return The guest's last name.
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Sets the guest's last name.
   * @param lastName The new last name for the guest.
   */
  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  /**
   * Retrieves the guest's address.
   * @return The guest's address.
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * Sets the guest's address.
   * @param address The new address for the guest.
   */
  public void setAddress(final String address) {
    this.address = address;
  }

  /**
   * Retrieves the number of children in the guest's party.
   * @return The number of children in the guest's party.
   */
  public int getNumberChildrenInParty() {
    return this.numberChildrenInParty;
  }

  /**
   * Sets the number of children in the guest's party.
   * @param numberChildrenInParty The new number of children in the guest's party.
   */
  public void setNumberChildrenInParty(final int numberChildrenInParty) {
    this.numberChildrenInParty = numberChildrenInParty;
  }

  /**
   * Retrieves the number of adults in the guest's party.
   * @return The number of adults in the guest's party.
   */
  public int getNumberAdultsInParty() {
    return this.numberAdultsInParty;
  }

  /**
   * Sets the number of adults in the guest's party.
   * @param numberAdultsInParty The new number of adults in the guest's party.
   */
  public void setNumberAdultsInParty(final int numberAdultsInParty) {
    this.numberAdultsInParty = numberAdultsInParty;
  }
}
