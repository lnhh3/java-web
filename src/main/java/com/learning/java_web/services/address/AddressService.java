package com.learning.java_web.services.address;

import com.learning.java_web.commons.responses.RestApiMessage;
import com.learning.java_web.commons.responses.RestApiStatus;
import com.learning.java_web.commons.validators.Validator;
import com.learning.java_web.models.entities.Address;
import com.learning.java_web.models.entities.User;
import com.learning.java_web.models.requests.AddressRequest;
import com.learning.java_web.repositories.IAddressRepo;
import com.learning.java_web.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        return addressRepo.getAddressByUserId(userId);
    }

    @Override
    public Page<Address> getPageAddress(String searchKey, Pageable pageable) {
        return addressRepo.getPageAddressWithAddress("%" + searchKey + "%", pageable);
    }

    @Override
    public void createAddress(AddressRequest addressRequest) {
        Address findNameAddress = addressRepo.findByName(addressRequest.getName());
        Validator.notNull(findNameAddress, RestApiStatus.BAD_REQUEST, RestApiMessage.ADDRESS_NAME_INVALID);
        Validator.notExisted(findNameAddress.getName().equals(addressRequest.getName()),
                RestApiStatus.BAD_REQUEST, RestApiMessage.NAME_ALREADY_EXISTED);
        Optional<User> user = userRepo.findById(addressRequest.getUserId());
        Validator.notNull(user, RestApiStatus.NOT_FOUND, RestApiMessage.USER_NOT_FOUND);
        Address address = Address.builder()
                .name(addressRequest.getName())
                .userId(addressRequest.getUserId())
                .build();
        addressRepo.save(address);
    }

    @Override
    public void updateAddress(String id, AddressRequest addressRequest) {
        Address address = addressRepo.findById(id).orElse(null);
        Validator.notNull(address, RestApiStatus.NOT_FOUND, RestApiMessage.ADDRESS_NOT_FOUND);
        Validator.notNullAndNotEmpty(addressRequest.getName(), RestApiStatus.BAD_REQUEST, RestApiMessage.ADDRESS_NAME_INVALID);
        Optional<User> user = userRepo.findById(addressRequest.getUserId());
        Validator.notNull(user, RestApiStatus.NOT_FOUND, RestApiMessage.ADDRESS_NOT_FOUND);
        Address addressFindByName = addressRepo.findByName(addressRequest.getName());
        Validator.notExisted(addressFindByName.getName().equals(address.getName()), RestApiStatus.BAD_REQUEST,
                RestApiMessage.NAME_ALREADY_EXISTED);

        addressRepo.save(address);
    }

    @Override
    public void deleteAddressById(String id) {
        Address address = addressRepo.findById(id).orElse(null);
        Validator.notNull(address, RestApiStatus.NOT_FOUND, RestApiMessage.ADDRESS_NOT_FOUND);
        addressRepo.deleteById(id);
    }
}
