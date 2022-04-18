package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.web.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.IRCaseService;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;

/**
 * @author sinjen
 * S061-作業部退匯(無匯入編號)
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class ReturnIRCaseS061Controller {

	@Autowired
	IRCaseService S061;
	@Autowired
	CommonFeignClient commonFeignClient;
	
	// ※※※ S061 API清單 ※※※
	// S061A	退匯(無匯入編號) (A)
	@PutMapping("/return-ircase/{seqNo}/execute")
	@Operation(description = "退匯作業(無匯入編號)", summary="退匯作業(無匯入編號)")
	public Response<IRCaseDto> exeReturnIRCase(@PathVariable("seqNo") String seqNo) {
		Response<IRCaseDto> response = new Response<IRCaseDto>();
		try {
			IRCaseDto irCaseDto = S061.exeReturnIRCase(seqNo);
			response.Success();
            response.setData(irCaseDto);
            log.info("呼叫作業部退匯API：SeqNo編號" + seqNo + "已執行退匯作業");
		}catch(Exception e){
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫作業部退匯API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}
	
	// S061C	退匯(無匯入編號) (C)
	
	// S061D	退匯(無匯入編號) (D)
	@PutMapping("/return-ircase/{seqNo}/delete")
	@Operation(description = "退匯刪除作業(無匯入編號)", summary="退匯刪除作業(無匯入編號)")
	public Response<IRCaseDto> deleteReturnIRCase(@PathVariable("seqNo") String seqNo) {
		Response<IRCaseDto> response = new Response<IRCaseDto>();
		try {
			IRCaseDto irCaseDto = S061.deleteReturnIRCase(seqNo);
			response.Success();
            response.setData(irCaseDto);
            log.info("呼叫作業部退匯API：SeqNo編號" + seqNo + "已執行退匯刪除作業");
		}catch(Exception e){
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫作業部退匯API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}
	
	// S061P	退匯(無匯入編號) (P)
	// S061I	退匯(無匯入編號) (A/C/D/P) 前資料查詢
	@GetMapping("/return-ircase/{seqNo}/enquiry")
	@Operation(description = "查詢待退匯案件(無匯入編號)", summary="查詢待退匯案件(無匯入編號)")
	public Response<IRCaseDto> qryWaitForReturnIRCase(@PathVariable("seqNo") String seqNo) {
		Response<IRCaseDto> response = new Response<IRCaseDto>();
		try {
			IRCaseDto irCaseDto = S061.getByIRSeqNo(seqNo);
			response.Success();
            response.setData(irCaseDto);
            if("1".equals(irCaseDto.getProcessStatus())) {
            	log.info("呼叫作業部退匯API：查詢SeqNo編號" + seqNo + "待處理");
            } else if("3".equals(irCaseDto.getProcessStatus())) {
            	response.Error("S002",commonFeignClient.getErrorMessage("S002"));
            	log.info("呼叫作業部退匯API：查詢SeqNo編號" + seqNo + "主管已放行");
            } else if("8".equals(irCaseDto.getProcessStatus())) {
            	response.Error("S003",commonFeignClient.getErrorMessage("S003"));
            	log.info("呼叫作業部退匯API：查詢SeqNo編號" + seqNo + "已退匯");
            }
		}catch(Exception e){
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫作業部退匯API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}

}
