package ${package};

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import ${basePackage}.service.I${tableClass.shortClassName}Service ;
import ${basePackage}.logic.${tableClass.shortClassName}Logic;
import ${basePackage}.dto.req.${tableClass.shortClassName}Req;
import ${basePackage}.dto.resp.${tableClass.shortClassName}Resp;
import com.hundsun.multrust.common.util.ValidateParamResult;
import com.hundsun.multrust.run.constant.BasicErrorNoConstants;
<#assign dateTime = .now>

/**
* ${tableClass.shortClassName}Service
* <br>
* @author ${author}
* @date ${dateTime?date}
* @version v1.0
*/
@Slf4j
@Service("tOrderPayService")
public class ${tableClass.shortClassName}ServiceImpl  implements I${tableClass.shortClassName}Service {

    @Autowired
    private ${tableClass.shortClassName}Logic ${tableClass.variableName}Logic;

    /**
    * 分页查询
    * @param req
    * @return
    **/
    //@Override
    //public R<${tableClass.shortClassName}> query${tableClass.shortClassName}WithPage(R<${tableClass.shortClassName}> req){
    //    return ${tableClass.variableName}Logic.query${tableClass.shortClassName}WithPage(req);
    //}


    /**
    * 主键查询
    * @param req
    * @return
    **/
    @Override
    public R<${tableClass.shortClassName}> query${tableClass.shortClassName}ByPrimaryKey(R<${tableClass.shortClassName}> req){
        return ${tableClass.variableName}Logic.query${tableClass.shortClassName}ByPrimaryKey(req);
    }

    /**
    * 新增
    * @param req
    * @return
    */
    @Override
    public R<${tableClass.shortClassName}> add${tableClass.shortClassName}(R<${tableClass.shortClassName}> req){

        // ValidateParamResult result = new ValidateParamResult();
        //1-入参必填项校验
        //result.excValidate("备注:", req.getRemark(), false, 250);
        //if (!result.isValidateSuccess()) {
        //    resp.setResultInfo("BasicErrorNoConstants.ERROR_PARAM", "参数校验不合法：" + result.toString());
        //   return resp;
        //}
        return ${tableClass.variableName}Logic.add${tableClass.shortClassName}(req);
    }

    /**
    * 修改
    * @param req
    * @return
    */
    @Override
    public R<${tableClass.shortClassName}> modify${tableClass.shortClassName}(R<${tableClass.shortClassName}> req){
        return ${tableClass.variableName}Logic.modify${tableClass.shortClassName}(req);
    }

    /**
    * 删除
    * @param req
    * @return
    */
    @Override
    public R<${tableClass.shortClassName}> delete${tableClass.shortClassName}(R<${tableClass.shortClassName}> req){
        return ${tableClass.variableName}Logic.delete${tableClass.shortClassName}(req);
    }
}