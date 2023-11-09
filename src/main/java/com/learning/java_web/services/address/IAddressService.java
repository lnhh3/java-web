package com.learning.java_web.services.address;

import com.learning.java_web.models.entities.Address;
import com.learning.java_web.models.requests.AddressRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAddressService {
    List<Address> getAddresses();
    Address getAddressById(String id);
    List<Address> getAddressesByUserId(String userId);
    Page<Address> getPageAddress(String searchKey, Pageable pageable);
    void createAddress(AddressRequest addressRequest);
    void updateAddress(String id, AddressRequest addressRequest);
    void deleteAddressById(String id);
}
