package tw.com.fcb.dolala.core.ir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.dolala.core.ir.repository.entity.IRMultipleRemitNature;

/**
 * @author sinjen
 * 匯入解款匯款性質分割資料檔
 */
@Repository
public interface IRMultipleRemitNatureRepository extends JpaRepository<IRMultipleRemitNature, Long> {

	Optional<IRMultipleRemitNature> findByIrNo(String irNo);
}
