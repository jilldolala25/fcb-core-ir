package tw.com.fcb.dolala.core.ir.web.mapper;

import org.mapstruct.Mapper;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;

/**
 * Copyright (C),2022,FirstBank
 * FileName: IRCaseDtoMapper
 * Author: Han-Ru
 * Date: 2022/4/17 下午 02:07
 * Description: IRCaseToDtoMapper
 */
@Mapper
public interface IRCaseMapper {

    IRCaseDto toIRCaseDto(SwiftMessageSaveCmd saveCmd);

}
