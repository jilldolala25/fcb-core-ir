package tw.com.fcb.dolala.core.ir.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.ir.web.dto.IRAdvicePrintListDto;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

/**
 * @author sinjen
 * S131-印製通知書
 */
@SpringBootTest
class IRAdvicePrintS131ControllerTest {

	@Autowired
	IRAdvicePrintS131Controller S131;
	
	// S131R 「處理種類」為(0、1、2、7或8) 之發查電文。 ==>進行通知書列印(多筆)
	@Test
	void testQryAdvicePrint() {
		String branch = "093";
		Response<List<IRDto>> response = S131.qryAdvicePrint(branch);
		assertEquals("0000", response.getCode());
	}
	
	// S131I1 "「處理種類」為(3或4) 之發查電文。==>回傳「受通知筆數」、「已印製通知書筆數」欄位"
	@Test
	void testQryAdviceCount() {
		String branch = "093";
		Response<int[]> response = S131.qryAdviceCount(branch);
		assertEquals("0000", response.getCode());
	}
	
	// S131I2 "「處理種類」為(5或6) 之發查電文。==>回傳S1311畫面"
	@Test
	void testQryAdviceList() {
		String branch = "093";
		Response<List<IRAdvicePrintListDto>> response = S131.qryAdviceList(branch);
		assertEquals("0000", response.getCode());
	}
}
