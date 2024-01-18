package BusPassangersservice.BusPassangers.Service;

import BusPassangersservice.BusPassangers.DTO.BookingDTO;
import BusPassangersservice.BusPassangers.DTO.Response.BookingRegResp;

import java.util.List;

public interface BookingService {

    public BookingDTO createBooking (BookingDTO BookingDTO);


    public List<BookingRegResp> getAllBookings(Integer userId);
    public BookingRegResp getBookingById(Integer id);
    public BookingRegResp cancaelBookingById(Integer id);
}
