package BusPassangersservice.BusPassangers.Service.serviceImpp;


import BusPassangersservice.BusPassangers.DTO.BookingDTO;
import BusPassangersservice.BusPassangers.DTO.Response.BookingRegResp;
import BusPassangersservice.BusPassangers.Entity.Booking;
import BusPassangersservice.BusPassangers.Exception.DataNotFounException;
import BusPassangersservice.BusPassangers.Repository.BookingRepository;
import BusPassangersservice.BusPassangers.Service.BookingService;
import BusPassangersservice.BusPassangers.Utils.GenerateId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpp implements BookingService {

    private ModelMapper mapper;
    private BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpp( ModelMapper mapper, BookingRepository bookingRepository) {

        this.mapper = mapper;
        this.bookingRepository = bookingRepository;

    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = boookingDTOToBooking(bookingDTO);
        booking.setId(GenerateId.BuildId());
        booking.setUserId(bookingDTO.getUserId());
        booking.setScheduleId(bookingDTO.getScheduleId());

        Booking save = bookingRepository.save(booking);
        return BookingToBookingDTo(save);
    }

    public BookingDTO BookingToBookingDTo(Booking booking)
    {
        return mapper.map(booking,BookingDTO.class);
    }

    public Booking boookingDTOToBooking(BookingDTO bookingDTO)
    {
        return mapper.map(bookingDTO,Booking.class);
    }


    @Override
    public List<BookingRegResp> getAllBookings(Integer userId) {
        List<Booking> userBookings = bookingRepository.findByUserId(userId);

        if(userBookings.size()>0)
        {
            List<BookingRegResp> collect = userBookings.stream().map((list) -> convertBookingToBookingRegResp(list)).collect(Collectors.toList());
            return collect;
        }
        else {
            throw new DataNotFounException("No Booking found with id "+ userId);
        }

    }

    @Override
    public BookingRegResp getBookingById(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new DataNotFounException("boooking not found with id " + bookingId));
        return convertBookingToBookingRegResp(booking);
    }

    @Override
    public BookingRegResp cancaelBookingById(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new DataNotFounException("boooking not found with id " + bookingId));
        bookingRepository.delete(booking);
        Optional<Booking> deleteeduser = bookingRepository.findById(bookingId);
        if(deleteeduser.isEmpty())
        {
            return convertBookingToBookingRegResp(booking);
        }
       return null;
    }

    public BookingRegResp convertBookingToBookingRegResp(Booking booking)
    {
        return mapper.map(booking,BookingRegResp.class);
    }
}
