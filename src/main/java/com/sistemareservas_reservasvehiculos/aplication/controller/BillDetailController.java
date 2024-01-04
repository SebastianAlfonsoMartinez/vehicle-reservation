package com.sistemareservas_reservasvehiculos.aplication.controller;

import com.sistemareservas_reservasvehiculos.domain.dto.BillDetailDto;
import com.sistemareservas_reservasvehiculos.aplication.exception.BookingException;
import com.sistemareservas_reservasvehiculos.aplication.service.BillDetailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/bill-detail")
public record BillDetailController(
        BillDetailService billDetailService
) {

    @PostMapping("/create")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createBillDetail(@RequestBody BillDetailDto billDetailDto) {
        billDetailService.createBillDetail(billDetailDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findAllBillsDetail(
            @PathVariable("offset") Integer offset,
            @PathVariable("limit") Integer limit
    ) throws BookingException {
        List<BillDetailDto> billDetailList = billDetailService.findAllBillDetail(offset, limit);
        return new ResponseEntity<>(billDetailList, HttpStatus.FOUND);
    }

    @GetMapping("/search/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findBillDetailById(@PathVariable("id") Integer id) throws BookingException {
        BillDetailDto billDetailDto = billDetailService.findBillDetailById(id);
        return new ResponseEntity<>(billDetailDto, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteBillDetail(@PathVariable("id") Integer id) throws BookingException {
        billDetailService.deleteBillDetail(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateBillDetail(@PathVariable("id") Integer id, BillDetailDto billDetailDto)
            throws BookingException {
        billDetailService.updateBillDetail(id, billDetailDto);
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

}
