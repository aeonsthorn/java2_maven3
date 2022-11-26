
package com.example;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import org.junit.*;

public class BookingSystemTest {

  @Test
  public void testShouldConstruct() {
    BookingSystem bookingSystem = new BookingSystem();

    assertNotNull(bookingSystem);

  }

  @Test
  public void testGetRandomType() {
    String randomType = BookingSystem.getRandomType();

    String[] typeOfRooms = { "double", "queen", "king" };

    List<String> typeOfRoomsList = Arrays.asList(typeOfRooms);

    assertTrue(typeOfRoomsList.contains(randomType));
  }

  // TODO Comment in the code is lying
  @Test
  public void testGetRandomNumberOfRooms() {
    for (int i = 0; i < 10; i++) {
      int randomNumberOfRooms = BookingSystem.getRandomNumberOfRooms();
      assertTrue(randomNumberOfRooms >= 5);
      assertTrue(randomNumberOfRooms <= 50);
    }
  }

  @Test
  public void testCreateRoomsWith10Rooms() {
    Room[] rooms = BookingSystem.createRooms(10);

    assertEquals(10, rooms.length);
  }

  @Test
  public void testCreateRoomsWith5Rooms() {
    Room[] rooms = BookingSystem.createRooms(5);

    assertEquals(5, rooms.length);
  }

  @Test
  public void testCreateRoomsWith0Rooms() {
    Room[] rooms = BookingSystem.createRooms(0);

    assertEquals(0, rooms.length);
  }

}
