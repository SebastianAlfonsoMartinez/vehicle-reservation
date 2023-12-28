package com.sistemareservas_reservasvehiculos.controller;

import com.sistemareservas_reservasvehiculos.domain.dto.BookingDto;
import com.sistemareservas_reservasvehiculos.exception.BookingException;
import com.sistemareservas_reservasvehiculos.service.BookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/booking")
public record BookingController(
        BookingService bookingService
) {

    @PostMapping("/create")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> registerBooking(@RequestBody BookingDto bookingDto) {
        bookingService.registerBooking(bookingDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findAllBooking(
            @PathVariable("offset") Integer offset,
            @PathVariable("limit") Integer limit) throws BookingException {
        List<BookingDto> bookings = bookingService.findAllBooking(offset, limit);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findBookingById(@PathVariable("id") Integer id) throws BookingException {
        BookingDto booking = bookingService.findBookingById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> editBooking(@PathVariable("id") Integer id, @RequestBody BookingDto bookingDto) throws BookingException {
        bookingService.editBooking(id, bookingDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> removeBooking(@PathVariable("id") Integer id) throws BookingException {
        bookingService.removeBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
