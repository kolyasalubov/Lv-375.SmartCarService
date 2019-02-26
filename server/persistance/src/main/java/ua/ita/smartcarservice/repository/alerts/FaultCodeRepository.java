package ua.ita.smartcarservice.repository.alerts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.alerts.FaultCode;

@Repository
public interface FaultCodeRepository extends JpaRepository<FaultCode, Long>{

	@Query ("SELECT f FROM FaultCode f WHERE f.faultCode = :faultCode")
	FaultCode getFaultCode (@Param ("faultCode") String faultCode);

}
