package com.byxf.nms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;

@ApiModel(value="图片上传类",description="图片上传类")
public class Picture {

    @ApiModelProperty(value="图片名称",name="name",example="")
    private String name;
    @ApiModelProperty(value="截取图片信息",name="imgCode")
    private String imgCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }
}
