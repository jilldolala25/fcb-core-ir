package tw.com.fcb.dolala.core.ir.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;

@SpringBootTest
class IRMasterRepositoryTest {

	@Autowired
	IRMasterRepository repository;

	@Test
	void testFindByIrNo() {
		IRMaster irMaster = repository.findByIrNo("S1NHA00947").orElseThrow();
		assertEquals("3456.78", irMaster.getIrAmount().toString());
	}

}
