package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.web.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.IRCaseService;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

/**
 * @author ijoshua29
 * S121-匯入匯款案件放行
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRCaseAuthorizationS121Controller {

	@Autowired
	IRCaseService irCaseService;
	@Autowired
	CommonFeignClient commonFeignClient;
			
	// ※※※ S121 API清單 ※※※
	// S121I 查詢待放行資料
	@GetMapping("/ircase-authorization/{seqNo}/enquiry")
	@Operation(description = "查詢待放行資料", summary = "查詢待放行")
	public Response<IRCaseDto> qryWaitForAuthorization(@PathVariable("seqNo") String seqNo) {
		Response<IRCaseDto> response = new Response<IRCaseDto>();
		try {
			IRCaseDto irCaseDto = irCaseService.getByIRSeqNo(seqNo);
			response.Success();
			response.setData(irCaseDto);
			if ("1".equals(irCaseDto.getProcessStatus())) {
				log.info("呼叫作業部查詢待放行案件API：查詢SeqNo編號" + seqNo + "待處理");
			} else if ("3".equals(irCaseDto.getProcessStatus())) {
				response.Error("S002", commonFeignClient.getErrorMessage("S002"));
				log.info("呼叫作業部查詢待放行案件API：查詢SeqNo編號" + seqNo + "主管已放行");
			} else if ("8".equals(irCaseDto.getProcessStatus())) {
				response.Error("S003", commonFeignClient.getErrorMessage("S003"));
				log.info("呼叫作業部查詢待放行案件API：查詢SeqNo編號" + seqNo + "已退匯");
			}
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫作業部查詢待放行案件API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}
	
	// S121A 執行MT103放行
	@PutMapping("/ircase-authorization/{seqNo}/execute")
	@Operation(description = "執行MT103放行", summary = "MT103放行")
	public Response<IRDto> exeCaseAuthorization(@PathVariable("seqNo") String seqNo) {
		Response<IRDto> response = new Response<IRDto>();
		try {
			IRDto irDto = irCaseService.exeCaseAuthorization(seqNo);
			response.Success();
			response.setData(irDto);
			log.info("呼叫作業部匯入匯款案件放行API：SeqNo編號" + seqNo + "已放行成功");
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫作業部匯入匯款案件放行API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}

}
