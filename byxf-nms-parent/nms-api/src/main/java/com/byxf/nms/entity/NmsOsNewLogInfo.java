package com.byxf.nms.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "nms_os_new_log_info")
@Getter
@Setter
@ApiModel(value = "NmsOsNewLogInfo")
public class NmsOsNewLogInfo implements Serializable {
    @Id
    @GeneratedValue(generator = "NmsOsNewLogInfoGenerator")
    @GenericGenerator(name = "NmsOsNewLogInfoGenerator", strategy = "uuid")
    @Column(name = "new_log_id",nullable = false,length = 32,columnDefinition = "VARCHAR(32)")
    @ApiModelProperty(value = "新闻记录id")
    private String newLogId;
    @Column(name = "new_id",nullable = true,length = 32,columnDefinition = "VARCHAR(32) COMMENT '新闻id'")
    @ApiModelProperty(value = "新闻id")
    private String newId;
    @Column(name = "user_id",nullable = true,length = 32,columnDefinition = "VARCHAR(32) COMMENT '创建人编号'")
    @ApiModelProperty(value = "创建人编号")
    private String userId;
    @Column(name = "log_type",nullable = true,length = 2,columnDefinition = "int(2) COMMENT '0新增1编辑2删除3发布4取消发布'")
    @ApiModelProperty(value = "0新增1编辑2删除3发布4取消发布")
    private int logType;
    @Column(name = "create_time",nullable = true,columnDefinition = "timestamp")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @Column(name = "update_time",nullable = true,columnDefinition = "timestamp")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @Column(name = "is_delete",nullable = true,length = 2,columnDefinition = "int(2) comment '0未删除1删除'")
    @ApiModelProperty(value = "0未删除1删除")
    private int isDelete;
}
