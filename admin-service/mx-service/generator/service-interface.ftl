package ${package};

import com.hundsun.jrescloud.rpc.annotation.CloudFunction;
import com.hundsun.jrescloud.rpc.annotation.CloudService;
import com.hundsun.restproxy.base.annotation.RestService;
import com.hundsun.restproxy.base.annotation.RestServiceModule;
import ${basePackage}.dto.req.${tableClass.shortClassName}Req;
import ${basePackage}.dto.resp.${tableClass.shortClassName}Resp;
import com.hundsun.multrust.run.constant.BasicServiceNameConstants;


<#assign dateTime = .now>

/**
* ${tableClass.shortClassName}IService
* <br>
* @author ${author}
* @date ${dateTime?date}
* @version v1.0
*/

public interface I${tableClass.shortClassName}Service {

    /**
    * 分页查询
    * @param req
    * @return
    **/
   // R<${tableClass.shortClassName}> query${tableClass.shortClassName}WithPage(R<${tableClass.shortClassName}> req);


    /**
    * 主键查询
    * @param req
    * @return
    **/
    R<${tableClass.shortClassName}> query${tableClass.shortClassName}ByPrimaryKey(R<${tableClass.shortClassName}> req);
    /**
    * 新增
    * @param req
    * @return
    */
    R<${tableClass.shortClassName}> add${tableClass.shortClassName}(R<${tableClass.shortClassName}> req);

    /**
    * 修改
    * @param req
    * @return
    */
    R<${tableClass.shortClassName}> modify${tableClass.shortClassName}(R<${tableClass.shortClassName}> req);

    /**
    * 删除
    * @param req
    * @return
    */
    R<${tableClass.shortClassName}> delete${tableClass.shortClassName}(R<${tableClass.shortClassName}> req);
}