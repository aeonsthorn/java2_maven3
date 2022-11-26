
package com.example;

import static org.junit.Assert.*;

import org.junit.*;

public class RoomTest {

  // getType

  @Test
  public void testGetRoomDouble() {
    Room room = new Room("double");
    assertEquals("double", room.getType());
  }

  @Test
  public void testGetRoomQueen() {
    Room room = new Room("queen");
    assertEquals("queen", room.getType());
  }

  @Test
  public void testGetRoomKing() {
    Room room = new Room("king");
    assertEquals("king", room.getType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRoomInvalidCoucou() {
    new Room("coucou");
  }

  // getPrice

  @Test
  public void testGetPriceDouble() {
    Room room = new Room("double");
    assertEquals(90, room.getPrice(), 0.01);
  }

  @Test
  public void testGetPriceQueen() {
    Room room = new Room("queen");
    assertEquals(110, room.getPrice(), 0.01);
  }

  @Test
  public void testGetPriceKing() {
    Room room = new Room("king");
    assertEquals(150, room.getPrice(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPriceInvalidBoubou() {
    new Room("boubou");
  }

  @Test
  public void testGetDefaultAvailability() {
    Room room = new Room("double");
    assertEquals(true, room.getAvailability());
  }

  @Test
  public void testChangeAvailabilityDefaultToFalse() {
    Room room = new Room("double");

    assertEquals(true, room.getAvailability());
    room.changeAvailability();

    assertEquals(false, room.getAvailability());
  }

  @Test
  public void testChangeAvailabilityFalseToTrue() {
    Room room = new Room("double");
    assertEquals(true, room.getAvailability());
    room.changeAvailability();
    assertEquals(false, room.getAvailability());
    room.changeAvailability();
    assertEquals(true, room.getAvailability());
  }

  @Test
  public void testFindAvailableRoomWhenThereIsNoRooms() {
    Room[] rooms = {};

    Room availableRoom = Room.findAvailableRoom(rooms, "double");

    assertEquals(null, availableRoom);
  }

  @Test
  public void testFindAvailableRoomWhenDesiredTypeIsInvalid() {
    Room[] rooms = { new Room("double") };

    Room availableRoom = Room.findAvailableRoom(rooms, "coucou");

    assertEquals(null, availableRoom);
  }

  @Test
  public void testFindAvailableRoomWhenThereIsNoneFromDesiredType() {
    Room[] rooms = { new Room("double") };

    Room availableRoom = Room.findAvailableRoom(rooms, "queen");

    assertEquals(null, availableRoom);
  }

  @Test
  public void testFindAvailableRoomWhenThereIsNoneAvailableFromDesiredType() {
    Room room1 = new Room("double");
    room1.changeAvailability();

    Room[] rooms = { room1 };

    Room availableRoom = Room.findAvailableRoom(rooms, "double");

    assertEquals(null, availableRoom);
  }

  @Test
  public void testFindAvailableRoomWhenThereIsOneAvailableFromDesiredType() {
    Room room1 = new Room("queen");
    Room room2 = new Room("double");

    Room[] rooms = { room1, room2 };

    Room availableRoom = Room.findAvailableRoom(rooms, "double");

    assertEquals(room2, availableRoom);
  }

  @Test
  public void testFindAvailableRoomWhenThereIsOneAvailableFromDesiredTypeAndManyAreNot() {
    Room room1 = new Room("double");
    room1.changeAvailability();
    Room room2 = new Room("double");
    room2.changeAvailability();

    Room room3 = new Room("double");

    Room[] rooms = { room1, room2, room3 };

    Room availableRoom = Room.findAvailableRoom(rooms, "double");

    assertEquals(room3, availableRoom);
  }

  @Test
  public void testFindAvailableRoomWhenThereIsOneAvailableFromDesiredTypeIgnoringCase() {
    Room room1 = new Room("double");

    Room[] rooms = { room1 };

    Room availableRoom = Room.findAvailableRoom(rooms, "Double");

    assertEquals(room1, availableRoom);
  }

  @Test
  public void testFindAvailableRoomWhenThereIsMoreThanOneAvailableFromDesiredType() {
    Room room1 = new Room("double");
    Room room2 = new Room("double");

    Room[] rooms = { room1, room2 };

    Room availableRoom = Room.findAvailableRoom(rooms, "double");

    assertEquals(room1, availableRoom);
  }

  @Test
  public void testFindAvailableRoomWhenThereIsMoreThanOneAvailableFromDesiredTypeIgnoringCase() {
    Room room1 = new Room("double");
    Room room2 = new Room("double");

    Room[] rooms = { room1, room2 };

    Room availableRoom = Room.findAvailableRoom(rooms, "Double");

    assertEquals(room1, availableRoom);
  }
}
