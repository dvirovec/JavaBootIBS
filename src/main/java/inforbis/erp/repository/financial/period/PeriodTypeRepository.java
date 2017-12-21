package inforbis.erp.repository.financial.period;

import inforbis.erp.model.financial.period.PeriodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by dvirovec on 2.7.2017..
 */
public interface PeriodTypeRepository extends JpaRepository<PeriodType, Long>{

    @Query("select a from PeriodType a where a.code = :code" )
    Iterable<PeriodType> findPeriodTypeByCode(@Param("code") String code);

}
