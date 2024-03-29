package BusAdminServices.BusAdmin.Repository;

import BusAdminServices.BusAdmin.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus ,Integer> {
    public List<Bus> findByBusCompanyAdminId(Integer Id);
}
