package tw.com.fcb.dolala.core.ir.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;

/**
 * @author sinjen
 * S061-作業部退匯(無匯入編號)
 */
@SpringBootTest
class ReturnIRCaseS061ControllerTest {

	@Autowired
	ReturnIRCaseS061Controller S061;
	
	// S061I 退匯(無匯入編號) (A/C/D/P) 前資料查詢
	@Test
	void testQryWaitForReturnIRCase() {
		String seqNo = "123456789012348";
		Response<IRCaseDto> response = S061.qryWaitForReturnIRCase(seqNo);
		assertEquals("0000", response.getCode());
	}

}
