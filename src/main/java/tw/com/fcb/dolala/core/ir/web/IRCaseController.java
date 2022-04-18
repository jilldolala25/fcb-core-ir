package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.web.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.AutoPassCheckService;
import tw.com.fcb.dolala.core.ir.service.IRCaseService;
import tw.com.fcb.dolala.core.ir.service.IRService;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;
import tw.com.fcb.dolala.core.ir.web.mapper.IRCaseMapper;

import javax.validation.constraints.NotNull;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRCaseController
 * Author: Han-Ru
 * Date: 2022/3/10 下午 02:51
 * Description: IRSwiftController
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Slf4j
@RestController
@RequestMapping("/ir")
@OpenAPIDefinition(info = @Info(title = "DoLALA多啦啦's  匯入  API", version = "v1.0.0"))
public class IRCaseController {

    @Autowired
    IRCaseService irCaseService;
    @Autowired
	CommonFeignClient commonFeignClient;
	@Autowired
	AutoPassCheckService autoPassCheckService;
	@Autowired
    IRCaseMapper dtoMapper;

	@Autowired
	IRService irService;

	@PostMapping("/ircase/receive-swift")
	@Operation(description = "接收 SWIFT 電文並存到 SwiftMessage", summary = "接收及儲存 SWIFT 電文")
	public Response<String> receiveSwift(@Validated @RequestBody SwiftMessageSaveCmd message) {
		Response<String> response = new Response();
		String insertIRCaseResult;
		try {
//			IRCaseDto irCaseDto = new IRCaseDto();
//			BeanUtils.copyProperties(message, irCaseDto);
			IRCaseDto irCaseDto = dtoMapper.toIRCaseDto(message);
			//insert，將電文資料新增至IRCase檔案
			insertIRCaseResult = irCaseService.irCaseInsert(irCaseDto);

			response.Success();
			response.setData(insertIRCaseResult);
			log.info("呼叫接收 SWIFT 電文 API：接收及儲存一筆 SWIFT 電文");
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫接收 SWIFT 電文 API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}
	@PutMapping("/ircase/{irSeqNo}/autopass")
	@Operation(description = "檢核電文是否可自動放行", summary = "更新AUTO_PASS欄位")
	public Response<String> checkAutoPassMK(@NotNull @PathVariable("irSeqNo") String irSeqNo) {
		Response<String> response = new Response<String>();
		try {
			IRCaseDto irCaseDto = irCaseService.getByIRSeqNo(irSeqNo);
			// check 是否可自動放行
			// update IRCaseDto AutoPassMk
			irCaseDto.setAutoPassMk(autoPassCheckService.checkAutoPass(irCaseDto));
			irCaseService.updateByIRSeqNo(irCaseDto);
//			if (irCaseDto.getAutoPassMk().equals("Y")){
//				irService.autoPassInsertIRMaster(irCaseDto);
//			}
			response.Success();
			response.setData("success");
			log.info("呼叫檢核電文是否可自動放行API：SeqNo編號" + irSeqNo + "是否已自動放行:" + irCaseDto.getAutoPassMk());
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫檢核電文是否可自動放行API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}

	@GetMapping("/ircase/{irSeqNo}/enquiry")
	@Operation(description = "取得seqNo電文資料", summary = "取得seqNo電文資料")
	public Response<IRCaseDto> getBySeqNo(@NotNull @PathVariable("irSeqNo") String irSeqNo) {
		Response<IRCaseDto> response = new Response<IRCaseDto>();
		try {
			IRCaseDto irCaseDto = irCaseService.getByIRSeqNo(irSeqNo);
			response.Success();
			response.setData(irCaseDto);
			log.info("呼叫查詢匯款電文資料API：查詢SeqNo編號" + irSeqNo);
		} catch (Exception e) {
			response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
			log.info("呼叫查詢匯款電文資料API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}

}
