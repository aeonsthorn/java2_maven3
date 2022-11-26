
package com.example;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HotelTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @Before
  public void setOutStream() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void restoreOutStream() {
    System.setOut(originalOut);
  }

  // create

  @Test
  public void testCreateReservationWhenNoRoomOfDesiredTypeIsAvailable() {
    Room[] rooms = { new Room("double") };

    Hotel hotel = new Hotel("Coco", rooms);

    hotel.createReservation("Simon", "double");

    outContent.reset();

    hotel.createReservation("Marie", "double");

    assertEquals(
        "Sorry Marie, we have no available rooms of the desired type.",
        outContent.toString().stripTrailing());
  }

  @Test
  public void testCreateReservationWhenNoRoomOfDesiredTypeExists() {
    Room[] rooms = { new Room("double") };

    Hotel hotel = new Hotel("Coco", rooms);

    hotel.createReservation("Simon", "king");

    assertEquals(
        "Sorry Simon, we have no available rooms of the desired type.",
        outContent.toString().stripTrailing());
  }

  @Test
  public void testCreateReservationForOneDoubleWhenOneIsAvailable() {
    Room[] rooms = { new Room("double") };

    Hotel hotel = new Hotel("Coco", rooms);

    hotel.createReservation("Simon", "double");

    assertEquals(
        "You have successfully reserved a double room under the name Simon. We look forward having you at Coco",
        outContent.toString().stripTrailing());
  }

  @Test
  public void testCreateReservationForOneDoubleWhenAllRoomsAlreadyBooked() {
    Room[] rooms = { new Room("double"), new Room("king") };

    Hotel hotel = new Hotel("Coco", rooms);

    hotel.createReservation("Simon", "double");
    hotel.createReservation("Simon", "king");
    outContent.reset();
    hotel.createReservation("Marie", "double");

    assertEquals(
        "Sorry, all rooms are already reserved",
        outContent.toString().stripTrailing());
  }

  @Test
  public void testCancelReservationForOneDoubleForSimon() {
    Room[] rooms = { new Room("double") };

    Hotel hotel = new Hotel("Coco", rooms);

    hotel.createReservation("Simon", "double");

    outContent.reset();

    hotel.cancelReservation("Simon", "double");

    assertEquals(
        "Simon, your reservation for a doubleroom has been successfully cancelled.",
        outContent.toString().stripTrailing());
  }

  @Test
  public void testCancelReservationForOneDoubleForSimonButNoneExists() {
    Room[] rooms = { new Room("double") };

    Hotel hotel = new Hotel("Coco", rooms);

    outContent.reset();

    hotel.cancelReservation("Simon", "double");

    assertEquals(
        "There is no reservation for a double room under the name of Simon.",
        outContent.toString().stripTrailing());
  }

  // print

  @Test
  public void testPrintInvoiceForSimonForOneDoubleReservation() {
    Room[] rooms = { new Room("double") };

    Hotel hotel = new Hotel("Coco", rooms);

    hotel.createReservation("Simon", "double");

    outContent.reset();

    hotel.printInvoice("Simon");

    assertEquals(
        "Simon's invoice is of $90.0",
        outContent.toString().stripTrailing());
  }

  @Test
  public void testPrintInvoiceForMarieForOneDoubleReservation() {
    Room[] rooms = { new Room("double"), new Room("double") };

    Hotel hotel = new Hotel("Coco", rooms);

    hotel.createReservation("Simon", "double");
    hotel.createReservation("Marie", "double");

    outContent.reset();

    hotel.printInvoice("Marie");

    assertEquals(
        "Marie's invoice is of $90.0",
        outContent.toString().stripTrailing());
  }

  @Test
  public void testPrintInvoiceForSimonIgnoreCaseForOneDoubleReservation() {
    Room[] rooms = { new Room("double") };

    Hotel hotel = new Hotel("Coco", rooms);

    hotel.createReservation("Simon", "double");

    outContent.reset();

    hotel.printInvoice("simon");

    assertEquals(
        "simon's invoice is of $90.0",
        outContent.toString().stripTrailing());
  }

  @Test
  public void testPrintInvoiceForSimonWithNothingUnderHerName() {
    Room[] rooms = { new Room("double") };

    Hotel hotel = new Hotel("Coco", rooms);

    outContent.reset();

    hotel.printInvoice("Simon");

    assertEquals(
        "Simon's invoice is of $0.0",
        outContent.toString().stripTrailing());
  }

  // toString

  @Test
  public void testToStringHotelCocoWithOneAvailableDoubleRoom() {
    String hotelName = "Coco";

    Room[] rooms = { new Room("double") };

    Hotel hotel = new Hotel(hotelName, rooms);

    assertEquals(
        "Hotel name: Coco\nAvailable Rooms: 1 double, 0 queen, 0 king.",
        hotel.toString());
  }

  @Test
  public void testToStringHotelKikiWithOneReservedDoubleRoom() {
    String hotelName = "Kiki";

    Room room1 = new Room("double");

    Room[] rooms = { room1 };

    Hotel hotel = new Hotel(hotelName, rooms);

    hotel.createReservation("Simon", "double");

    assertEquals(
        "Hotel name: Kiki\nAvailable Rooms: 0 double, 0 queen, 0 king.",
        hotel.toString());
  }

  @Test
  public void testToStringHotelKikiWithOneReservedDoubleKingAndQueenRoom() {
    String hotelName = "Kiki";

    Room room1 = new Room("double");
    Room room2 = new Room("king");
    Room room3 = new Room("queen");

    Room[] rooms = { room1, room2, room3 };

    Hotel hotel = new Hotel(hotelName, rooms);

    hotel.createReservation("Simon", "double");
    hotel.createReservation("Simon", "queen");
    hotel.createReservation("Simon", "king");

    assertEquals(
        "Hotel name: Kiki\nAvailable Rooms: 0 double, 0 queen, 0 king.",
        hotel.toString());
  }

  @Test
  public void testToStringHotelKikiWithOneRoomOfEachType() {
    String hotelName = "Kiki";

    Room room1 = new Room("double");

    Room room2 = new Room("queen");
    Room room3 = new Room("king");

    Room[] rooms = { room1, room2, room3 };

    Hotel hotel = new Hotel(hotelName, rooms);

    assertEquals(
        "Hotel name: Kiki\nAvailable Rooms: 1 double, 1 queen, 1 king.",
        hotel.toString());
  }
}
