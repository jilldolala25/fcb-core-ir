package tw.com.fcb.dolala.core.ir.mapper;

import org.mapstruct.Mapper;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;
import tw.com.fcb.dolala.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;

@Mapper
public interface IRCaseMapper {

    IRCaseEntity irCaseDtoToEntity(IRCaseDto irCaseDto);

    IRCaseDto irCaseEntityToDto(IRCaseEntity irCaseEntity);

    IRSaveCmd irCaseDtoToIRSaveCmd(IRCaseDto irCaseDto);

}

