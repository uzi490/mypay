package ${package};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ${basePackage}.mapper.${tableClass.shortClassName}Mapper;
import ${basePackage}.dto.req.${tableClass.shortClassName}Req;
import ${basePackage}.dto.resp.${tableClass.shortClassName}Resp;
import tk.mybatis.mapper.entity.Example;
import ${tableClass.fullClassName};
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hundsun.jrescloud.common.exception.BaseBizException;
import com.hundsun.multrust.run.constant.BasicErrorNoConstants;
import org.apache.commons.lang3.StringUtils;

<#assign dateTime = .now>

/**
* ${tableClass.shortClassName}Logic
* <br>
* @author ${author}
* @date ${dateTime?date}
* @version v1.0
*/
@Component
public class ${tableClass.shortClassName}Logic {
    private static Logger log = LoggerFactory.getLogger(${tableClass.shortClassName}Logic.class);

    @Autowired
    private ${tableClass.shortClassName}Mapper ${tableClass.variableName}Mapper;




}