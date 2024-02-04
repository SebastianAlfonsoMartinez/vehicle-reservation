package com.sistemareservas_reservasvehiculos.aplication.controller;

import com.sistemareservas_reservasvehiculos.domain.dto.BillDto;
import com.sistemareservas_reservasvehiculos.aplication.exception.BookingException;
import com.sistemareservas_reservasvehiculos.aplication.service.BillService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/bill")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/create")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createBill(@RequestBody BillDto billDto){
        billService.createBill(billDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findAllBill(
            @PathVariable("offset") Integer offset,
            @PathVariable("limit") Integer limit
    ) throws BookingException {
        List<BillDto> bills = billService.findAllBill(offset, limit);
        return new ResponseEntity<>(bills, HttpStatus.FOUND);
    }


    @GetMapping("/search/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findBillByID(@PathVariable("id") Integer id ) throws BookingException {
        BillDto bill = billService.findBillById(id);
        return new ResponseEntity<>(bill, HttpStatus.FOUND);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteBill(@PathVariable("id") Integer id) throws BookingException {
        billService.deleteBill(id);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateBill(@PathVariable("id") Integer id, @RequestBody BillDto billDto)
            throws BookingException {
        billService.updateBill(id, billDto);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
