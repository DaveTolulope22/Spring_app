package com.david.managementappv2.settings.repositories;


import com.david.managementappv2.settings.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

}