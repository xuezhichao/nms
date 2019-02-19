package com.byxf.nms.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "nms_os_new_info")
@Getter
@Setter
@ApiModel(value = "NmsOsNewInfo")
public class NmsOsNewInfo implements Serializable {
    @Id
    @GeneratedValue(generator = "NmsOsNewInfoGenerator")
    @GenericGenerator(name = "NmsOsNewInfoGenerator", strategy = "uuid")
    @Column(name = "new_id",nullable = false,length = 32,columnDefinition = "VARCHAR(32) COMMENT '新闻id'")
    @ApiModelProperty(value = "新闻id")
    private String newId;
    @Column(name = "user_id",nullable = true,length = 32,columnDefinition = "VARCHAR(32) COMMENT '创建人编号'")
    @ApiModelProperty(value = "创建人编号")
    private String userId;
    @Column(name = "new_cover_img_url",nullable = true,length = 128,columnDefinition = "VARCHAR(128) COMMENT '新闻封面图片地址'")
    @ApiModelProperty(value = "新闻封面图片地址")
    private String newCoverImgUrl;
    @Column(name = "new_abstract",nullable = true,length = 300,columnDefinition = "VARCHAR(300) COMMENT '新闻摘要'")
    @ApiModelProperty(value = "新闻摘要")
    private String newAbstract;
    @Column(name = "new_time",nullable = true,columnDefinition = "timestamp COMMENT '新闻时间'")
    @ApiModelProperty(value = "新闻时间")
    private Date newTime;
    @Column(name = "new_title",nullable = true,length = 50,columnDefinition = "VARCHAR(50) COMMENT '新闻标题'")
    @ApiModelProperty(value = "新闻标题")
    private String newTitle;
    @Column(name = "new_content",nullable = true,length = 8000,columnDefinition = "VARCHAR(8000) COMMENT '新闻正文'")
    @ApiModelProperty(value = "新闻正文")
    private String newContent;
    @Column(name = "new_link_url",nullable = true,length = 64,columnDefinition = "VARCHAR(64) COMMENT '原文链接'")
    @ApiModelProperty(value = "原文链接")
    private String newLinkUrl;
    @Column(name = "create_time",nullable = true,columnDefinition = "timestamp COMMENT '创建时间'")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @Column(name = "update_time",nullable = true,columnDefinition = "timestamp COMMENT '更新时间'")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @Column(name = "is_delete",nullable = true,length = 1,columnDefinition = "int(1) COMMENT '是否删除0 未删 1删除'")
    @ApiModelProperty(value = "是否删除0 未删 1删除")
    private int isDelete;
    @OneToOne(mappedBy = "nmsOsNewInfo")
    private NmsOsNewStatusInfo nmsOsNewStatusInfo;
}
