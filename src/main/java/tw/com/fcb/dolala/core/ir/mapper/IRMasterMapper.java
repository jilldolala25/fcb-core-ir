package tw.com.fcb.dolala.core.ir.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRAdvicePrintListDto;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

@Mapper
public interface IRMasterMapper {

    IRMaster irDtoToIRMaster(IRDto irDto);


    IRDto irMasterToIRDto(IRMaster irMaster);



    IRMaster irSaveCmdToIRMaster(IRSaveCmd irSaveCmd);

    IRSaveCmd irCaseDtoToIRSaveCmd(IRCaseDto irCaseDto);

    IRAdvicePrintListDto irMasterToIrAdvicePrintDto(IRMaster irMaster);


    IRMaster updateIRMasterFromirSaveCmd(IRSaveCmd irSaveCmd,@MappingTarget IRMaster irMaster);


}
