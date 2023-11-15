package com.learning.java_web.services.address;

import com.learning.java_web.models.entities.Address;
import com.learning.java_web.models.requests.AddressRequest;
import com.learning.java_web.models.requests.UpdateAddressRequest;
import com.learning.java_web.models.responses.PagingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IAddressService {
    List<Address> getAddresses();
    Address getAddressById(String id);
    List<Address> getAddressesByUserId(String userId);
    PagingResponse getPageAddress(int pageNumber, int pageSize, Sort.Direction sortType, String searchKey);
    void createAddress(AddressRequest addressRequest);
    void updateAddress(String id, UpdateAddressRequest addressRequest);
    void deleteAddressById(String id);
}
