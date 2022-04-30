package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.web.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
import tw.com.fcb.dolala.core.ir.service.IRService;
import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRController
 * Author: Han-Ru
 * Date: 2022/3/10 下午 02:08
 * Description: IRDto Controller
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRController {

    @Autowired
    IRService irService;
    @Autowired
    CommonFeignClient commonFeignClient;


    @PostMapping("/irmaster/insert")
    @Operation(description = "匯入匯款主檔資料寫入", summary = "新增匯入匯款主檔")
    public Response<IRMaster> irMasterInsert(@Validated @RequestBody IRSaveCmd irSaveCmd) {
        Response<IRMaster> response = new Response<IRMaster>();

        try {
            // insert irMaster檢核完成，新增至主檔
            IRMaster irMaster = irService.insertIRMaster(irSaveCmd);
            response.Success();
            response.setData(irMaster);
            log.info("呼叫新增匯入匯款主檔API：新增一筆主檔資料");
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫新增匯入匯款主檔API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    @GetMapping("/irmaster/{branch}/count")
    @Operation(description = "傳入受通知單位查詢案件數", summary = "查詢匯入案件數")
    public Response<Integer> getCount(@PathVariable("branch") String branch) {
        Response<Integer> response = new Response<Integer>();
        try {
            Integer count = irService.getIrCaseCount(branch);
            response.Success();
            response.setData(count);
            log.info("呼叫查詢匯入案件數API：" + branch + "分行匯入案件數=" + count);
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫查詢匯入案件數API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    @GetMapping("/irmaster/{irNo}/")
    @Operation(description = "查詢匯入匯款主檔資料", summary = "查詢匯入匯款主檔資料")
    public Response<IRDto> getIRMasterByIrNo(@PathVariable("irNo") String irNo) {
        Response<IRDto> response = new Response<IRDto>();
        try {
            IRDto irDto = irService.getByIrNo(irNo);
            response.Success();
            response.setData(irDto);
            if ("0".equals(irDto.getPaidStats().toString())) {
                log.info("呼叫查詢匯入匯款案件API：查詢匯入匯款編號" + irNo + "待處理");
            } else if ("4".equals(irDto.getPaidStats().toString())) {
                response.Error("S102", commonFeignClient.getErrorMessage("S102"));
                log.info("呼叫查詢匯入匯款案件API：查詢匯入匯款編號" + irNo + "已解款");
            } else if ("5".equals(irDto.getPaidStats().toString())) {
                response.Error("S103", commonFeignClient.getErrorMessage("S103"));
                log.info("呼叫查詢匯入匯款案件API：查詢匯入匯款編號" + irNo + "已退匯");
            } else {
                log.info("呼叫查詢匯入匯款案件API：查詢匯入匯款編號" + irNo + ",狀態 = " + irDto.getPaidStats());
            }
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫查詢匯入匯款案件API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    @GetMapping("/irmaster/{irNo}/enquiry")
    @Operation(description = "傳入匯入匯款編號查詢案件明細", summary = "查詢匯入案件明細")
    public Response<IRDto> getByIrNo(@PathVariable("irNo") String irNo) {
        Response<IRDto> response = new Response<IRDto>();
        try {
            response.Success();
            response.setData(irService.findByIrNoAndPaidStats(irNo));
            log.info("呼叫查詢匯入匯款編號案件API：查詢irNo編號-AFTER" + irNo);
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫查詢匯入匯款編號案件API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }


    @PutMapping("/irmaster/{irNo}/advice-print")
    @Operation(description = "變更印製通知書記號", summary = "印製通知書記號")
    public Response<?> print(@PathVariable("irNo") String irNo) {
        Response<?> response = new Response<>();
        try {
            irService.print(irNo);
            response.Success();
            log.info("呼叫變更印製通知書記號API：irNo編號" + irNo + "通知書印製成功，更新記號為Y");
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫變更印製通知書記號API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    @PutMapping("/irmaster/{irNo}/settle")
    @Operation(description = "變更付款狀態", summary = "付款狀態")
    public Response<?> settle(@PathVariable("irNo") String irNo) {
        Response<?> response = new Response<>();
        try {
            irService.settle(irNo);
            response.Success();
            log.info("呼叫變更付款狀態API：irNo編號" + irNo + "付款狀態變更為已解款");
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫變更付款狀態API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }
}
