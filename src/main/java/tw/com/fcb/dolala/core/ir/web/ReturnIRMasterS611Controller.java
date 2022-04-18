package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.web.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.IRService;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

/**
 * @author sinjen
 * S611-分行退匯
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class ReturnIRMasterS611Controller {

	@Autowired
	IRService S611;
	@Autowired
	CommonFeignClient commonFeignClient;

	// ※※※ S611 API清單 ※※※
	// S611I 查詢匯入退匯資料
	@GetMapping("/return-irmaster/{irNo}/enquiry")
	@Operation(description = "查詢匯入退匯資料", summary = "查詢匯入退匯資料")
	public Response<IRDto> qryWaitForReturnIRMaster(@PathVariable("irNo") String irNo) {
		Response<IRDto> response = new Response<IRDto>();
		try {
			IRDto irDto = S611.getByIrNo(irNo);
			response.Success();
			response.setData(irDto);
			if ("0".equals(irDto.getPaidStats().toString())) {
				log.info("呼叫分行退匯API：查詢匯入匯款編號" + irNo + "待處理");
			} else if ("4".equals(irDto.getPaidStats().toString())) {
				response.Error("S102", commonFeignClient.getErrorMessage("S102"));
				log.info("呼叫分行退匯API：查詢匯入匯款編號" + irNo + "已解款");
			} else if ("5".equals(irDto.getPaidStats().toString())) {
				response.Error("S103", commonFeignClient.getErrorMessage("S103"));
				log.info("呼叫分行退匯API：查詢匯入匯款編號" + irNo + "已退匯");
			}
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫分行退匯API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}
	
	// S611A	新增退匯交易
	@PutMapping("/return-irmaster/{irNo}/execute")
	@Operation(description = "新增匯入匯款退匯交易", summary="新增匯入匯款退匯交易")
	public Response<IRDto> exeReturnIRMaster(@PathVariable("irNo") String irNo) {
		Response<IRDto> response = new Response<IRDto>();
		try {
			IRDto irDto = S611.exeReturnIRMaster(irNo);
			response.Success();
			response.setData(irDto);
			log.info("呼叫匯入匯款退匯交易API：匯入匯款編號" + irNo + "已退匯");
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫匯入匯款退匯交易API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}
	
	// S611C	更正退匯交易
	
	// S611D	剔除退匯交易
	@PutMapping("/return-irmaster/{irNo}/delete")
	@Operation(description = "剔除匯入匯款退匯交易", summary="剔除匯入匯款退匯交易")
	public Response<IRDto> deleteReturnIRMaster(@PathVariable("irNo") String irNo) {
		Response<IRDto> response = new Response<IRDto>();
		try {
			IRDto irDto = S611.delReturnIRMaster(irNo);
			response.Success();
			response.setData(irDto);
			log.info("呼叫剔除匯入匯款退匯交易API：匯入匯款編號" + irNo + "已剔除退匯");
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫剔除匯入匯款退匯交易API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}
	
	// NOTCR07	依通報序號查詢通報匯率及通報匯率成本
	// TCTYR05	讀取都市國別檔
	// SIDCR01	身分別為５ (檢查統編是否為總行或分行統編)
	// TCUSR08	依客戶編號查詢客戶名稱(MIXED)
	// TFXRR29	讀取即期賣匯匯率及賣匯匯率成本資料

}
