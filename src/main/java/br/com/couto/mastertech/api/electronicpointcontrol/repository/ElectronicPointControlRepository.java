package br.com.couto.mastertech.api.electronicpointcontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.couto.mastertech.api.electronicpointcontrol.model.ElectronicPointControl;
import br.com.couto.mastertech.api.user.model.User;

@Repository
public interface ElectronicPointControlRepository extends JpaRepository<ElectronicPointControl, Long> {
	
	List<ElectronicPointControl> findByUser(User user);

}
