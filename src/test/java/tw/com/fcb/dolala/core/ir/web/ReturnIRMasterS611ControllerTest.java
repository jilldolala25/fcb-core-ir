package tw.com.fcb.dolala.core.ir.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

/**
 * @author sinjen
 * S611-分行退匯
 */
@SpringBootTest
class ReturnIRMasterS611ControllerTest {

	@Autowired
	ReturnIRMasterS611Controller S611;
	
	// S611I 查詢匯入退匯資料
	@Test
	void testQryWaitForReturnIRMaster() {
		String irNo = "S1NHA00955";
		Response<IRDto> response = S611.qryWaitForReturnIRMaster(irNo);
		assertEquals("0000", response.getCode());
	}

}
