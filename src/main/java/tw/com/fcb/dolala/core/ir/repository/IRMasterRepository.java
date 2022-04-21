package tw.com.fcb.dolala.core.ir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;

import java.util.List;
import java.util.Optional;

/**
 * @author sinjen
 */
@Repository
public interface IRMasterRepository extends JpaRepository<IRMaster, Long> {
    // select * from IRMaster where irNo = @irNo
    // IRMaster findByIrNo(String irNo);
    Optional<IRMaster> findByIrNo(@Param("irNo") String irNo);

    Optional<IRMaster> findByIrNoAndPaidStats(@Param("irNo") String irNo, @Param("paidStats") Integer paidStats);

    //@Query(name="findByBeAdvBranch",nativeQuery = true,value = "select * from IR_APPLY_MASTER where BE_ADV_BRANCH=:beAdvBranch AND PRINT_ADV_MK = 'N'")

    List<IRMaster> findByBeAdvBranch(@Param("beAdvBranch") String beAdvBranch);

    List<IRMaster> findByBeAdvBranchAndPaidStats(@Param("beAdvBranch") String beAdvBranch, @Param("paidStats") Integer paidStats);

    List<IRMaster> findByBeAdvBranchAndPrintAdvMk(@Param("beAdvBranch") String beAdvBranch, @Param("printAdvMk") String printAdvMk);

    List<IRMaster> findByBeAdvBranchAndPaidStatsAndPrintAdvMk(@Param("beAdvBranch") String beAdvBranch,
                                                              @Param("paidStats") Integer paidStats, @Param("printAdvMk") String printAdvMk);
}
