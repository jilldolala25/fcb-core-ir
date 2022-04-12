package tw.com.fcb.dolala.core.ir.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;

/**
 * @author sinjen
 * S031-匯款資料輸入
 */
@SpringBootTest
class IRCaseEntryS031ControllerTest {

	@Autowired
	IRCaseEntryS031Controller S031;
	
	// S031I 執行匯款資料查詢 (C:更正/ D:剔除/T:複製電文)前資料查詢
	@Test
	void testQryIRCase() {
		String seqNo = "123456789012348";
		Response<IRCaseDto> response = S031.qryIRCase(seqNo);
		assertEquals("0000", response.getCode());
	}

}
