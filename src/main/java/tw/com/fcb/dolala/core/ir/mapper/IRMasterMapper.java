package tw.com.fcb.dolala.core.ir.mapper;

import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRAdvicePrintListDto;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

public interface IRMasterMapper {

    IRDto irMasterToIRDto (IRMaster irMaster);

    IRMaster irSaveCmdToIRMaster(IRSaveCmd irSaveCmd);

    IRSaveCmd irCaseDtoToIRSaveCmd(IRCaseDto irCaseDto);

    IRAdvicePrintListDto irMasterToIrAdvicePrintDto(IRMaster irMaster);
}
