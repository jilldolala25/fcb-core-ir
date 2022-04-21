package tw.com.fcb.dolala.core.ir.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.common.web.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.IRService;
import tw.com.fcb.dolala.core.ir.web.dto.IRAdvicePrintListDto;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ijoshua29
 * S131-印製通知書
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRAdvicePrintS131Controller {

    @Autowired
    IRService irService;
    @Autowired
    CommonFeignClient commonFeignClient;

    // ※※※ S131 API清單 ※※※
    // S131R 「處理種類」為(0、1、2、7或8) 之發查電文。 ==>進行通知書列印(多筆)
    @PutMapping("/advice-print/{branch}/enquiry")
    @Operation(description = "進行通知書列印", summary = "通知書列印")
    public Response<List<IRDto>> qryAdvicePrint(String branch) {
        Response<List<IRDto>> response = new Response<List<IRDto>>();
        List<IRDto> listData = new ArrayList<IRDto>();

        try {
            listData = irService.qryAdvicePrint(branch);
            response.Success();
            response.setData(listData);

            if (listData.size() != 0) {
                log.info("呼叫分行通知書列印API：" + branch + "分行列印" + listData.size() + "筆通知書");
            } else {
                log.info("呼叫分行通知書列印API：" + branch + "分行查無需列印資料");
            }
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫分行通知書列印API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    // S131I1 "「處理種類」為(3或4) 之發查電文。==>回傳「受通知筆數」、「已印製通知書筆數」欄位"
    @GetMapping("/advice-print/{branch}/count")
    @Operation(description = "受通知筆數", summary = "受通知筆數")
    public Response<int[]> qryAdviceCount(String branch) {
        Response<int[]> response = new Response<int[]>();
        int[] adviceCount = new int[2];

        try {
            adviceCount = irService.qryAdviceCount(branch);
            response.Success();
            response.setData(adviceCount);

            // 受通知筆數
            if (adviceCount[0] != 0) {
                log.info("呼叫分行查詢受通知筆數API：" + branch + "分行受通知筆數:" + adviceCount[0] + "筆");
                log.info("呼叫分行查詢受通知筆數API：" + branch + "已印製通知書筆數:" + adviceCount[1] + "筆");
            } else {
                log.info("呼叫分行查詢受通知筆數API：" + branch + "分行查無資料");
            }
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫分行查詢受通知筆數API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    // S131I2 "「處理種類」為(5或6) 之發查電文。==>回傳S1311畫面"
    @GetMapping("/advice-print/{branch}/enquiry-list")
    @Operation(description = "分行通知書列表", summary = "通知書列表")
    public Response<List<IRAdvicePrintListDto>> qryAdviceList(String branch) {
        Response<List<IRAdvicePrintListDto>> response = new Response<List<IRAdvicePrintListDto>>();
        List<IRAdvicePrintListDto> listData = new ArrayList<IRAdvicePrintListDto>();

        try {
            listData = irService.qryAdviceList(branch);
            response.Success();
            response.setData(listData);

            if (listData.size() != 0) {
                log.info("呼叫分行查詢通知書明細API：" + branch + "分行共:" + listData.size() + "筆通知書");
            } else {
                log.info("呼叫分行查詢通知書明細API：" + branch + "分行查無資料");
            }
        } catch (Exception e) {
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫分行查詢通知書明細API：" + commonFeignClient.getErrorMessage(e.getMessage()));
        }
        return response;
    }

    // S162I "「處理種類」為B之發查電文。==>回傳S1312畫面"
    // S162B S1312畫面上／下頁查詢。

}
