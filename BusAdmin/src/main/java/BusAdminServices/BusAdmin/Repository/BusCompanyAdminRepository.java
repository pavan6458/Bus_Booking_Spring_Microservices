package BusAdminServices.BusAdmin.Repository;

import BusAdminServices.BusAdmin.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusCompanyAdminRepository extends JpaRepository<BusCompanyAdmin,Integer> {
    public BusCompanyAdmin findByCompanyEmail(String email);
}
