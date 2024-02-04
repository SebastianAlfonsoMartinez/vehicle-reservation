package com.sistemareservas_reservasvehiculos.aplication.service;

import com.sistemareservas_reservasvehiculos.domain.dto.BookingDto;
import com.sistemareservas_reservasvehiculos.domain.entity.Booking;
import com.sistemareservas_reservasvehiculos.aplication.exception.BookingException;
import com.sistemareservas_reservasvehiculos.aplication.lasting.EMessage;
import com.sistemareservas_reservasvehiculos.aplication.lasting.EState;
import com.sistemareservas_reservasvehiculos.aplication.mapper.BookingMapper;
import com.sistemareservas_reservasvehiculos.domain.repository.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BookingService (
        BookingRepository bookingRepository,
        BookingMapper mapper
){

    public void registerBooking(BookingDto bookingDto){
        Booking booking = mapper.toEntity(bookingDto);
        booking.setState(EState.ACTIVE);
        bookingRepository.save(booking);
    }

    public List<BookingDto> findAllBooking(Integer offset, Integer limit) throws BookingException {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Booking> bookings = bookingRepository.findAll(pageable);
        if (bookings.getContent().isEmpty()) {
            throw new BookingException(EMessage.DATA_NOT_FOUND);
        }
        return mapper.toDtoList(bookings.getContent());
    }

    public BookingDto findBookingById(Integer id) throws BookingException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingException(EMessage.DATA_NOT_FOUND));
        return mapper.toDto(booking);
    }

    public void editBooking(Integer id, BookingDto bookingDto) throws BookingException {
        bookingRepository.findById(id).orElseThrow(() -> new BookingException(EMessage.DATA_NOT_FOUND));
        Booking booking = mapper.toEntity(bookingDto);
        bookingRepository.save(booking);
    }

    public void removeBooking(Integer id) throws BookingException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingException(EMessage.DATA_NOT_FOUND));
        bookingRepository.delete(booking);
    }

}
