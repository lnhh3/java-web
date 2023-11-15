package com.learning.java_web.controllers;

import com.learning.java_web.models.requests.AddressRequest;
import com.learning.java_web.models.requests.UpdateAddressRequest;
import com.learning.java_web.models.responses.PagingResponse;
import com.learning.java_web.services.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController extends AbstractBaseController{
    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity getAddresses() {
        return responseUtil.successResponse(addressService.getAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAddressById(@PathVariable("id") String id) {
        return responseUtil.successResponse(addressService.getAddressById(id));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity getAddressByUserId(@PathVariable("user_id") String userId) {
        return responseUtil.successResponse(addressService.getAddressesByUserId(userId));
    }

    @GetMapping("/page")
    public ResponseEntity getPageAddress(@RequestParam(name = "page_number", defaultValue = "1", required = false) int pageNumber,
                                         @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize,
                                         @RequestParam(name = "sort_type", defaultValue = "ASC", required = false) Sort.Direction sortType,
                                         @RequestParam(name = "search_key", defaultValue = "", required = false) String searchKey)
    {
        return responseUtil.successResponse(addressService.getPageAddress(pageNumber, pageSize, sortType, searchKey));
    }

    @PostMapping
    public ResponseEntity createAddress(@RequestBody AddressRequest addressRequest) {
        addressService.createAddress(addressRequest);
        return responseUtil.successResponse("ok");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAddress(@PathVariable("id") String id, @RequestBody UpdateAddressRequest addressRequest) {
        addressService.updateAddress(id, addressRequest);
        return responseUtil.successResponse("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable("id") String id) {
        addressService.deleteAddressById(id);
        return responseUtil.successResponse("ok");
    }
}
