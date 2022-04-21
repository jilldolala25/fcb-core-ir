package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.web.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.IRService;
import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

/**
 * @author sinjen
 * S211-原幣匯入匯款解款
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IROriginalCcyReleaseS211Controller {

    @Autowired
    IRService S211;
    @Autowired
    CommonFeignClient commonFeignClient;

    // ※※※ S211 API清單 ※※※
    // S211A 執行原幣解款資料新增 (A:新增)
    @PostMapping("/originalccy-release/execute")
    @Operation(description = "新增原幣解款案件資料", summary = "新增原幣解款案件資料")
    public Response<IRDto> exeRelaseIRMaster(@Validated @RequestBody IRSaveCmd irSaveCmd) {
        Response<IRDto> response = new Response<IRDto>();

        try {
            IRDto irDto = new IRDto();
            irDto = S211.exeRelaseIRMaster(irSaveCmd);
            response.Success();
            response.setData(irDto);
            log.info("呼叫新增原幣解款案件API：查詢匯入匯款編號" + response.getData().getIrNo() + "已解款");
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫新增原幣解款案件API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    // S211C 執行原幣解款資料更正 (C:更正)

    // S211D 執行原幣解款資料剔除 (D:剔除)
    @PutMapping("/originalccy-release/{irNo}/delete")
    @Operation(description = "剔除原幣解款案件資料", summary = "剔除原幣解款案件資料")
    public Response<IRDto> delRelaseIRMaster(@PathVariable("irNo") String irNo) {
        Response<IRDto> response = new Response<IRDto>();
        try {
            IRDto irDto = S211.deleteRelaseIRMaster(irNo);
            response.Success();
            response.setData(irDto);
            log.info("呼叫剔除原幣解款案件API：查詢匯入匯款編號" + irNo + "已剔除解款");
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫剔除原幣解款案件API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    // S211I 依匯入編號資料查詢解款資料 (A,C,D)
    @GetMapping("/originalccy-release/{irNo}/enquiry")
    @Operation(description = "查詢匯入匯款案件資料", summary = "查詢匯入匯款案件資料")
    public Response<IRDto> qryWaitForRelaseIRMaster(@PathVariable("irNo") String irNo) {
        Response<IRDto> response = new Response<IRDto>();
        try {
            IRDto irDto = S211.getByIrNo(irNo);
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
            }
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫查詢匯入匯款案件API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    // S211P 於交易完成後，端末判斷須列印申報書者，提示訊息，若選擇列印，則發S211P取得申報書資料列印
    @GetMapping("/originalccy-release/{irNo}/print-statement")
    @Operation(description = "列印申報書資料", summary = "列印申報書資料")
    public Response<IRDto> prtStatement(@PathVariable("irNo") String irNo) {
        Response<IRDto> response = new Response<IRDto>();

        try {
            IRDto irDto = S211.getByIrNo(irNo);
            response.Success();
            response.setData(irDto);
            if ("4".equals(irDto.getPaidStats().toString())) {
                log.info("呼叫列印申報書資料API：列印匯入匯款編號" + irNo + "已解款");
            } else if ("0".equals(irDto.getPaidStats().toString())) {
                response.Error("S104", commonFeignClient.getErrorMessage("S104"));
                log.info("呼叫列印申報書資料API：列印匯入匯款編號" + irNo + "未解款");
            } else if ("5".equals(irDto.getPaidStats().toString())) {
                response.Error("S103", commonFeignClient.getErrorMessage("S103"));
                log.info("呼叫列印申報書資料API：列印匯入匯款編號" + irNo + "已退匯");
            }
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫列印申報書資料API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    // S211U 將前端 AML 取號相關資料回寫主檔，並檢核疑似第三方交易、客戶是否曾被婉拒交易
    // NOTCR06 依通報序號查詢通報匯率及通報匯率成本
    // S168R01 單日結匯金額檢查(即期結匯!=0且送件編號!=空白)
    // S168R03 單日結匯金額檢查(即期結匯!=0且送件編號=空白)
    // S168R04 單日結匯金額檢查(匯款幣別=25)
    // SACCR01 查詢帳號-東亞證券複委託客戶
    // SACCR02 台幣扣款帳號&取款碼檢核
    // SACCR03 查詢(或驗證)台幣委託代繳扣款帳號
    // SCOMI 查詢手續費
    // SIDCR01 身分別為５ (檢查統編是否為總行或分行統編)
    // SIDCR02 查詢居留證核發日/居留證有效日
    // TAOCR09 查詢與檢核實際招攬人員、轉介/協銷人員
    // TCTYR02 查詢匯出國別名稱
    // TCTYR05 查詢國籍名稱
    // TCUSR08 讀取顧客名稱 (依客戶編號查詢客戶名稱)
    // TFFVR08 查詢遠期結匯匯率、結匯AMT
    // TFXRR27 依承作日&幣別查詢即期/現鈔匯率資料

}
