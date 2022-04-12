package tw.com.fcb.dolala.core.ir.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tw.com.fcb.dolala.core.ir.repository.enums.ChargeType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRCaseVo
 * Author: Han-Ru
 * Date: 2022/3/22 下午 04:00
 * Description:
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */

@Schema(description = "匯入匯款電文檔Vo")
@Data
public class IRCaseVo {
    @Column(name = "ID")
    Long id;

    @Column(name = "SEQ_NO")
    String seqNo;
    //通知單位
    @Column(name = "ADV_BRANCH")
    String advBranch;

    //處理狀態
    @Column(name = "PROCESS_STATUS")
    String processStatus;
    //入帳記號
    @Column(name = "CREDIT_MK")
    String creditMK;
    //受通知單位
    @Column(name = "BE_ADV_BRANCH")
    String beAdvBranch;
    //顧客統編
    @Column(name = "CUSTOMER_ID")
    String customerID;

    @Column(name = "SENDER_SWIFT_CODE")
    String senderSwiftCode;

    @Column(name = "REFERENCE_NO")
    String referenceNo;

    @Column(name = "CURRENCY")
    String currency;

    @Column(name = "AMOUNT")
    BigDecimal irAmount;


    @Column(name = "AUTO_PASS_MK")
    String autoPassMk;

    @NotNull(message = "ValueDate不可為空白")
    @Schema(description = "有效日" )
    LocalDate valueDate;
    @Schema(description = "收件日做自動/單筆查詢印表時之日期" )
    LocalDate receiveDate;

    @Schema(description = "處理時間" )
    String txTime;

    @Column(name = "SENDER_INFO1")
    String senderInfo1;
    @Column(name = "SENDER_INFO2")
    String senderInfo2;
    @Column(name = "SENDER_INFO3")
    String senderInfo3;
    @Column(name = "SENDER_INFO4")
    String senderInfo4;
    @Column(name = "RECEIVER_ACCOUNT")
    String receiverAccount;
    @Column(name = "RECEIVER_INFO1")
    String receiverInfo1;
    @Column(name = "RECEIVER_INFO2")
    String receiverInfo2;
    @Column(name = "RECEIVER_INFO3")
    String receiverInfo3;
    @Column(name = "RECEIVER_INFO4")
    String receiverInfo4;

    @Enumerated(EnumType.STRING)
    ChargeType chargeType;

    @Column(name = "CHARGE_FEE_CURRENCY1")
    String chargeFeeCurrency1;
    @Column(name = "CHARGE_FEE_AMOUNT1")
    BigDecimal chargeFeeAmount1;
    @Column(name = "CHARGE_FEE_CURRENCY2")
    String chargeFeeCurrency2;
    @Column(name = "CHARGE_FEE_AMOUNT2")
    BigDecimal chargeFeeAmount2;
    @Column(name = "CHARGE_FEE_CURRENCY3")
    String chargeFeeCurrency3;
    @Column(name = "CHARGE_FEE_AMOUNT3")
    BigDecimal chargeFeeAmount3;


    @Schema(description = "匯款行一" )
    String remitBankInfo1;

    @Schema(description = "匯款行二" )
    String remitBankInfo2;

    @Schema(description = "匯款行三" )
    String remitBankInfo3;

    @Schema(description = "匯款行四" )
    String remitBankInfo4;

    @Size(max = 11, min = 11,message = "存匯行SWIFT-ID輸入錯誤")
    @Schema(description = "存匯行 SWIFT-TID" )
    String depositBank;
    @Schema(description = "同存記號" )
    String nstVstMk;
}

