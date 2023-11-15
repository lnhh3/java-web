package com.learning.java_web.repositories;

import com.learning.java_web.models.entities.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAddressRepo extends JpaRepository<Address, String> {
    @Query("select a from Address as a, User as u where a.userId = u.id and u.id = :user_id")
    List<Address> getAddressesByUserId(@Param("user_id") String userId);

    @Query("select a from Address a where a.name like :search_key")
    Page<Address> getPageAddressWithAddress(@Param("search_key") String searchKey, Pageable pageable);

    Address findByName(String name);

}
