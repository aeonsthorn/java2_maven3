
package com.example;

import static org.junit.Assert.*;

import org.junit.*;

public class ReservationTest {

  @Test
  public void testGetName() {
    Room room = new Room("double");
    String personName = "Simon";

    Reservation reservation = new Reservation(room, personName);

    assertEquals(personName, reservation.getName());
  }

  @Test
  public void testGetRoom() {
    Room room = new Room("double");
    String personName = "Simon";

    Reservation reservation = new Reservation(room, personName);

    assertEquals(room, reservation.getRoom());
  }
}
