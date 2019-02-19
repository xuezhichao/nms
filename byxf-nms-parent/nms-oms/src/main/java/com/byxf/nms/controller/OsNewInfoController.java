package com.byxf.nms.controller;

import com.alibaba.fastjson.JSON;
import com.byxf.nms.entity.NmsOsNewStatusInfo;
import com.byxf.nms.model.BusinessErrorCode;
import com.byxf.nms.model.CommonResponse;
import com.byxf.nms.entity.NmsOsNewInfo;
import com.byxf.nms.model.BusinessSucCode;
import com.byxf.nms.model.NmsOsNewInfoBean;
import com.byxf.nms.model.OperateMsg;
import com.byxf.nms.model.PageInfo;
import com.byxf.nms.model.PageInfoBean;
import com.byxf.nms.model.PageInfoResponse;
import com.byxf.nms.service.OsNewInfoService;
import com.byxf.nms.util.DateUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/osNewInfo")
public class OsNewInfoController {

    private static final String VERSION = "1.0.0";

    @Resource
    OsNewInfoService osNewInfoService;

    @ApiOperation(value = "官网新闻配置信息保存", notes = "官网新闻配置信息保存")
    @ApiImplicitParam(name = "osNewInfo", required = true, dataType = "NmsOsNewInfo", paramType = "body")
    @RequestMapping(value = "saveOrUpdateOsNewInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveOrUpdateOsNewInfo(@RequestBody NmsOsNewInfo osNewInfo) {
        try {
            osNewInfo = osNewInfoService.saveOrUpdateOsNewInfo(osNewInfo);
            CommonResponse response = new CommonResponse(OperateMsg.SAVEORUPDATEOSNEWINFO.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION, BusinessSucCode.EXEC_SUCC.getCode(),
                    OperateMsg.SAVEORUPDATEOSNEWINFO.getMsg(),
                    JSON.toJSONString(osNewInfo));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(OperateMsg.SAVEORUPDATEOSNEWINFO.getCode(), DateUtil.date2Stringhms(new Date()),
                    VERSION,
                    BusinessErrorCode.ERROR_SYSTEM.getCode(),"信息保存失败！", "");
        }
    }

    @ApiOperation(value = "查询官网新闻配置信息详情", notes = "查询官网新闻配置信息详情")
    @ApiImplicitParam(name = "newId", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "queryOsNewInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse queryOsNewInfo(@RequestParam String newId) {
        NmsOsNewInfo nmsOsNewInfo = osNewInfoService.queryOsNewInfoByNewId(newId);
        if (nmsOsNewInfo != null) {
            CommonResponse response = new CommonResponse(OperateMsg.QUERYOSNEWINFO.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION, BusinessSucCode.EXEC_SUCC.getCode(),
                    OperateMsg.QUERYOSNEWINFO.getMsg(),
                    JSON.toJSONString(nmsOsNewInfo));
            return response;
        } else {
            return new CommonResponse(OperateMsg.QUERYOSNEWINFO.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION, BusinessSucCode.EXEC_SUCC.getCode(),
                    "未查询到数据！",
                    "");
        }
    }

    @ApiOperation(value = "分页查询官网新闻配置信息列表", notes = "分页查询官网新闻配置信息列表")
    @ApiImplicitParam(name = "pageInfo", required = true, dataType = "PageInfoBean", paramType = "body")
    @RequestMapping(value = "queryOsNewInfosByPage", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse queryOsNewInfosByPage(@RequestBody PageInfoBean pageInfo) {
        PageInfoResponse<NmsOsNewInfoBean> datas = osNewInfoService.queryOsNewInfosByPage(pageInfo);
        if (datas != null) {
            return new CommonResponse(OperateMsg.QUERYOSNEWINFOSBYPAGE.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION, BusinessSucCode.EXEC_SUCC.getCode(),
                    OperateMsg.QUERYOSNEWINFOSBYPAGE.getMsg(),
                    JSON.toJSONString(datas));
        } else {
            return new CommonResponse(OperateMsg.QUERYOSNEWINFOSBYPAGE.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION, BusinessSucCode.EXEC_SUCC.getCode(),
                    "信息查询失败！", "");
        }
    }

    @ApiOperation(value = "删除新闻信息", notes = "删除新闻信息")
    @ApiImplicitParam(name = "newIds", required = true, dataType = "List", paramType = "query")
    @RequestMapping(value = "deleteOsNewInfos", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResponse deleteOsNewInfos(@RequestParam String newIds) {
        List<String> listNews = Arrays.asList(newIds.split(","));
        Integer num = osNewInfoService.deleteOsNewInfos(listNews);
        if (num != null) {
            CommonResponse response = new CommonResponse(OperateMsg.DELETEOSNEWINFOS.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION, BusinessSucCode.EXEC_SUCC.getCode(),
                    OperateMsg.DELETEOSNEWINFOS.getMsg(),
                    num.toString());
            return response;
        } else {
            return new CommonResponse(OperateMsg.DELETEOSNEWINFOS.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION, BusinessErrorCode.ERROR_SYSTEM.getCode(),
                    "数据删除失败！",
                    "");
        }
    }

    @ApiOperation(value = "官网新闻配置信息发布", notes = "官网新闻配置信息保存")
    @ApiImplicitParam(name = "newIds", required = true, dataType = "String", paramType = "body")
    @RequestMapping(value = "saveOrUpdateOsNewStatusInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse saveOrUpdateOsNewStatusInfo(@RequestParam String newIds) {
        try {
            List<String> listNews = Arrays.asList(newIds.split(","));
            List<NmsOsNewStatusInfo> osNewStatusInfos = osNewInfoService.saveOrUpdateOsNewStatusInfo(listNews);
            if(osNewStatusInfos.size()>0){
                CommonResponse response = new CommonResponse(OperateMsg.SAVEORUPDATEOSNEWSTATUSINFO.getCode(),
                        DateUtil.date2Stringhms(new Date()), VERSION, BusinessSucCode.EXEC_SUCC.getCode(),
                        OperateMsg.SAVEORUPDATEOSNEWSTATUSINFO.getMsg(),
                        JSON.toJSONString(osNewStatusInfos));
                return response;
            }else{
                return new CommonResponse(OperateMsg.SAVEORUPDATEOSNEWSTATUSINFO.getCode(),
                        DateUtil.date2Stringhms(new Date()), VERSION,
                        BusinessErrorCode.ERROR_SYSTEM.getCode(), "信息发布失败！", "");
            }
        } catch (Exception e) {
            return new CommonResponse(OperateMsg.SAVEORUPDATEOSNEWSTATUSINFO.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION,
                    BusinessErrorCode.ERROR_SYSTEM.getCode(), "信息发布失败！", "");
        }
    }
}
