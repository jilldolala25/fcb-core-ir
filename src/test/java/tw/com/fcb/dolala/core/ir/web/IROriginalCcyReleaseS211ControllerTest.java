package tw.com.fcb.dolala.core.ir.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

/**
 * @author sinjen
 * S211-原幣匯入匯款解款
 */
@SpringBootTest
class IROriginalCcyReleaseS211ControllerTest {

	@Autowired
	IROriginalCcyReleaseS211Controller S211;
	
	// S211I 依匯入編號資料查詢解款資料 (A,C,D)
	@Test
	void testQryWaitForRelaseIRMaster() {
		String irNo = "S1NHA00955";
		Response<IRDto> response = S211.qryWaitForRelaseIRMaster(irNo);
		assertEquals("0000", response.getCode());
	}

}
