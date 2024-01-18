package BusPassangersservice.BusPassangers.Controller;



import BusPassangersservice.BusPassangers.DTO.BookingDTO;
import BusPassangersservice.BusPassangers.DTO.Response.BookingRegResp;
import BusPassangersservice.BusPassangers.Service.BookingService;
import BusPassangersservice.BusPassangers.Utils.ResponseGenerater;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/booking")
@CrossOrigin(origins = "*")
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> BookTicket(@RequestBody BookingDTO BookingDTO){
        BookingDTO booking = bookingService.createBooking(BookingDTO);
        return ResponseGenerater.ResponseBuilder(HttpStatus.CREATED,"Booked successfully",booking);
    }

    @GetMapping("/All/{userid}")
    public ResponseEntity<Object> getAllBookings (@PathVariable (name = "userid") Integer userId)
    {
        List<BookingRegResp> allBookings = bookingService.getAllBookings(userId);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Booked fetched successfully",allBookings);
    }

    @GetMapping("/bookingId")
    public ResponseEntity<Object> getBookingByBookingID (@PathVariable (name = "bookingId") Integer bookingid)
    {
        BookingRegResp bookingById = bookingService.getBookingById(bookingid);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Booked fetched successfully",bookingById);

    }
    @DeleteMapping("/{bookingID}/delete")

    public ResponseEntity<Object> deleteBookingByID(@PathVariable(name="bookingID")Integer bookingID)
    {
        BookingRegResp deletedResponse = bookingService.cancaelBookingById(bookingID);
        return ResponseGenerater.ResponseBuilder(HttpStatus.OK,"Booked cancelled successfully",deletedResponse);

    }
}
