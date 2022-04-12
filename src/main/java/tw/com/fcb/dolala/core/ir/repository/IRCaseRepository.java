package tw.com.fcb.dolala.core.ir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;

import java.util.Optional;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRCaseRepository
 * Author: Han-Ru
 * Date: 2022/3/10 下午 03:34
 * Description: IRSwiftMessageRepository
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Repository
public interface IRCaseRepository extends JpaRepository<IRCaseEntity, Long> {

	Optional<IRCaseEntity> findBySeqNo(String irSeqNo);

	Optional<IRCaseEntity> findBySeqNoAndProcessStatus(String seqNo, String processStatus);

}
