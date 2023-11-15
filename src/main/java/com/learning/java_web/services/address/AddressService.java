package com.learning.java_web.services.address;

import com.learning.java_web.commons.responses.RestApiMessage;
import com.learning.java_web.commons.responses.RestApiStatus;
import com.learning.java_web.commons.validators.Validator;
import com.learning.java_web.models.entities.Address;
import com.learning.java_web.models.entities.User;
import com.learning.java_web.models.requests.AddressRequest;
import com.learning.java_web.models.requests.UpdateAddressRequest;
import com.learning.java_web.models.responses.PagingResponse;
import com.learning.java_web.repositories.IAddressRepo;
import com.learning.java_web.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IAddressService{
    @Autowired
    IAddressRepo addressRepo;

    @Autowired
    IUserRepo userRepo;

    @Override
    public List<Address> getAddresses() {
        return addressRepo.findAll();
    }

    @Override
    public Address getAddressById(String id) {
        Address address = addressRepo.findById(id).orElse(null);
        Validator.notNull(address, RestApiStatus.NOT_FOUND, RestApiMessage.ADDRESS_NOT_FOUND);
        return address;
    }

    @Override
    public List<Address> getAddressesByUserId(String userId) {
        Validator.notNullAndNotEmpty(userId, RestApiStatus.BAD_REQUEST, RestApiMessage.USER_ID_INVALID);
        User user = userRepo.findById(userId).orElse(null);
        Validator.notNull(user, RestApiStatus.NOT_FOUND, RestApiMessage.USER_NOT_FOUND);
        return addressRepo.getAddressesByUserId(userId);
    }

    @Override
    public PagingResponse getPageAddress(int pageNumber, int pageSize, Sort.Direction sortType, String searchKey) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(sortType, "name"));
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        PagingResponse pagingResponse = new PagingResponse(addressRepo.getPageAddressWithAddress("%" + searchKey + "%", pageable));
        return pagingResponse;
    }

    @Override
    public void createAddress(AddressRequest addressRequest) {
        Validator.notNullAndNotEmpty(addressRequest.getName(), RestApiStatus.BAD_REQUEST, RestApiMessage.ADDRESS_NAME_INVALID);
        Validator.notNullAndNotEmpty(addressRequest.getUserId(), RestApiStatus.BAD_REQUEST, RestApiMessage.USER_ID_INVALID);
        User user = userRepo.findById(addressRequest.getUserId()).orElse(null);
        Validator.notNull(user, RestApiStatus.NOT_FOUND, RestApiMessage.USER_NOT_FOUND);
        Address address = Address.builder()
                .name(addressRequest.getName())
                .userId(addressRequest.getUserId())
                .build();
        addressRepo.save(address);
    }

    @Override
    public void updateAddress(String id, UpdateAddressRequest addressRequest) {
        Address address = addressRepo.findById(id).orElse(null);
        Validator.notNull(address, RestApiStatus.NOT_FOUND, RestApiMessage.ADDRESS_NOT_FOUND);
        Validator.notNullAndNotEmpty(addressRequest.getName(), RestApiStatus.BAD_REQUEST, RestApiMessage.ADDRESS_NAME_INVALID);
        address.setName(addressRequest.getName());

        addressRepo.save(address);
    }

    @Override
    public void deleteAddressById(String id) {
        Address address = addressRepo.findById(id).orElse(null);
        Validator.notNull(address, RestApiStatus.NOT_FOUND, RestApiMessage.ADDRESS_NOT_FOUND);
        addressRepo.deleteById(id);
    }
}
