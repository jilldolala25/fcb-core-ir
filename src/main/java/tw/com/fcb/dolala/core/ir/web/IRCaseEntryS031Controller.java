package tw.com.fcb.dolala.core.ir.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.IRCaseService;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;

/**
 * @author sinjen
 * S031-匯款資料輸入
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRCaseEntryS031Controller {

	@Autowired
	IRCaseService S031;
	@Autowired
    CommonFeignClient commonFeignClient;
	
	// ※※※ S031 API清單 ※※※
	// S031A 執行匯款資料新增 (A:新增/T:複製電文)
	// S031C 執行匯款資料更正 (C:更正)
	// S031D 執行匯款資料剔除 (D:剔除)
	
	// S031I 執行匯款資料查詢 (C:更正/ D:剔除/T:複製電文)前資料查詢
	@GetMapping("/ircase-entry/{seqNo}/enquiry")
	@Operation(description = "查詢匯款電文資料", summary = "查詢匯款電文資料")
	public Response<IRCaseDto> qryIRCase(@PathVariable("seqNo") String seqNo) {
		Response<IRCaseDto> response = new Response<IRCaseDto>();
		try {
			IRCaseDto irCaseDto = S031.getByIRSeqNo(seqNo);
			response.Success();
			response.setData(irCaseDto);
			if ("1".equals(irCaseDto.getProcessStatus())) {
				log.info("呼叫作業部查詢匯款電文資料API：查詢SeqNo編號" + seqNo + "待處理");
			} else if ("3".equals(irCaseDto.getProcessStatus())) {
				response.Error("S002", commonFeignClient.getErrorMessage("S002"));
				log.info("呼叫作業部查詢匯款電文資料API：查詢SeqNo編號" + seqNo + "主管已放行");
			} else if ("8".equals(irCaseDto.getProcessStatus())) {
				response.Error("S003", commonFeignClient.getErrorMessage("S003"));
				log.info("呼叫作業部查詢匯款電文資料API：查詢SeqNo編號" + seqNo + "已退匯");
			}
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫作業部查詢匯款電文資料API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}

}
