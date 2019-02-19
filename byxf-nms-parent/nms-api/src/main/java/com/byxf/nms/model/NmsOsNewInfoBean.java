package com.byxf.nms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@ApiModel(value = "新闻信息表")
public class NmsOsNewInfoBean implements Serializable {
    @ApiModelProperty(value = "新闻id")
    private String newId;
    @ApiModelProperty(value = "创建人编号")
    private String userId;
    @ApiModelProperty(value = "新闻封面图片地址")
    private String newCoverImgUrl;
    @ApiModelProperty(value = "新闻摘要")
    private String newAbstract;
    @ApiModelProperty(value = "新闻时间")
    private Date newTime;
    @ApiModelProperty(value = "新闻标题")
    private String newTitle;
    @ApiModelProperty(value = "新闻正文")
    private String newContent;
    @ApiModelProperty(value = "原文链接")
    private String newLinkUrl;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "是否删除0 未删 1删除")
    private int isDelete;
    @ApiModelProperty(value = "是否发布 0未发布 1发布")
    private Integer newStatus;
}
