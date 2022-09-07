
package com.david.managementappv2.settings.repositories;


import com.david.managementappv2.settings.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}

