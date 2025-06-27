package BusPassangersservice.BusPassangers.Service.serviceImpp;


import BusPassangersservice.BusPassangers.DTO.PassangerDto;
import BusPassangersservice.BusPassangers.DTO.PassangerList;
import BusPassangersservice.BusPassangers.Entity.Booking;
import BusPassangersservice.BusPassangers.Entity.Passenger;
import BusPassangersservice.BusPassangers.Exception.DataNotFounException;
import BusPassangersservice.BusPassangers.Repository.BookingRepository;
import BusPassangersservice.BusPassangers.Repository.PassengerRepository;
import BusPassangersservice.BusPassangers.Service.PassangerService;
import BusPassangersservice.BusPassangers.Service.Client.seatAvailabilityFeign;
import BusPassangersservice.BusPassangers.Utils.GenerateId;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PassangersServiceImpp implements PassangerService {
    private PassengerRepository passengerRepository;

    private BookingRepository bookingRepository;
    private ModelMapper modelMapper;
    private seatAvailabilityFeign seatAvailable;

    public PassangersServiceImpp(PassengerRepository passengerRepository, BookingRepository bookingRepository, ModelMapper modelMapper, seatAvailabilityFeign seatAvailable) {
        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
        this.seatAvailable = seatAvailable;
    }


    @Override
    public PassangerDto addPassangersToBooking(PassangerDto passangerDto) {

        ResponseEntity<Boolean> notReserved=seatAvailable.mscheckavailiability(passangerDto);
        List<PassangerDto> passangerDtos = new ArrayList<>();
        if(notReserved.getBody()!=null && notReserved.getBody())
        {


            Booking booking = bookingRepository.findById(passangerDto.getBookingId()).orElseThrow(() ->
                    new DataNotFounException("Booking not found with id " + passangerDto.getBookingId()));
            booking.setStatus("Booked");

            List<PassangerList> collect = passangerDto.getPassangerLists().stream().map((list) ->
            {
                Passenger passenger = convertPassangerDtoTOPassanget(list);
                passenger.setId(GenerateId.BuildId());
                passenger.setScheduleId(passangerDto.getScheduleId());
                passenger.setBooking(booking);
                Passenger objectSaved = passengerRepository.save(passenger);
                return convertPAssangetTopassangerDTo.apply(objectSaved);

            }).collect(Collectors.toList());

            PassangerDto passangerDto1 = new PassangerDto();
            passangerDto1.setPassangerLists(collect);
            passangerDto1.setBookingId(passangerDto.getScheduleId());
            passangerDto1.setScheduleId(passangerDto.getScheduleId());
            return passangerDto1;
        }
        else
            throw new DataNotFounException("Please restart the booking");
    }

    @Override
    public List<PassangerList> GetAllSchedulePassangersList(Integer scheduleId) {
        List<Passenger> PassengerList = passengerRepository.findByScheduleId(scheduleId);
        return PassengerList.stream().map(convertPAssangetTopassangerDTo).collect(Collectors.toList());


    }

    @Override
    public List<PassangerList> getPassangersByBookingId(Integer bookingId) {
        List<Passenger> byBookingId = passengerRepository.findByBookingId(bookingId);
return    byBookingId.stream().map(convertPAssangetTopassangerDTo).collect(Collectors.toList());

    }

    public Passenger convertPassangerDtoTOPassanget(PassangerList passangerList)
    {
        return  modelMapper.map(passangerList,Passenger.class);

    }



    Function<Passenger,PassangerList> convertPAssangetTopassangerDTo = p1 -> modelMapper.map(p1,PassangerList.class);
}
