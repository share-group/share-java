package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案表
 */
@Pojo
public class DCase extends DSuper {
	/**
	 * 专案名称
	 */
	private String name;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 订单id
	 */
	private long orderId;
	/**
	 * 专案描述
	 */
	private String description;
	/**
	 * 品牌(定位/形象)
	 */
	private String brand;
	/**
	 * 产品/服务特点
	 */
	private String feature;
	/**
	 * 目标人群/用户特征
	 */
	private String target;
	/**
	 * 背景介绍(同类竞争产品)
	 */
	private String background;
	/**
	 * 目的(满足用户的需求)
	 */
	private String intent;
	/**
	 * 补充
	 */
	private String supplement;
	/**
	 * 专案类型
	 */
	private String type;
	/**
	 * 征集类型
	 */
	private int style;
	/**
	 * 是否显示(1-是 0-否)
	 */
	private byte isShow;
	/**
	 * 是否审核通过(1-是 0-否)
	 */
	private byte isPass;
	/**
	 * 参与人数
	 */
	private int takePartInNum;
	/**
	 * 关注人数
	 */
	private int fansNum;
	/**
	 * 创意数
	 */
	private int ideaNum;
	/**
	 * 吐槽数
	 */
	private int flowNum;
	/**
	 * 统计专案的点赞数
	 */
	private int praiseNum;
	/**
	 * 阅读数
	 */
	private int readNum;
	/**
	 * 专案结束时间
	 */
	private int endTime;
	/**
	 * 专案金额
	 */
	private int points;
	/**
	 * 列表排序字段
	 */
	private long orderIndex;
	/**
	 * 图片数组JSON
	 */
	private String image;
	/**
	 * 视频地址
	 */
	private String video;
	/**
	 * 报告下载地址
	 */
	private String report;
	/**
	 * 专案模板id
	 */
	private long caseTemplateId;
	/**
	 * 模块列表JSON
	 */
	private String moduleList;
	/**
	 * 创意模板id
	 */
	private long ideaTemplateId;
	/**
	 * 创意模板主层级id
	 */
	private long ideaLayerId;
	/**
	 * 是否匹配执行方
	 */
	private int isExecute;
	/**
	 * 是否媒体投放
	 */
	private int isMedia;

	/**
	 * 获取专案名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置专案名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取企业id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置企业id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获取用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取订单id
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * 设置订单id
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取专案描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置专案描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取品牌(定位/形象)
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * 设置品牌(定位/形象)
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * 获取产品/服务特点
	 */
	public String getFeature() {
		return feature;
	}

	/**
	 * 设置产品/服务特点
	 */
	public void setFeature(String feature) {
		this.feature = feature;
	}

	/**
	 * 获取目标人群/用户特征
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * 设置目标人群/用户特征
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * 获取背景介绍(同类竞争产品)
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * 设置背景介绍(同类竞争产品)
	 */
	public void setBackground(String background) {
		this.background = background;
	}

	/**
	 * 获取目的(满足用户的需求)
	 */
	public String getIntent() {
		return intent;
	}

	/**
	 * 设置目的(满足用户的需求)
	 */
	public void setIntent(String intent) {
		this.intent = intent;
	}

	/**
	 * 获取补充
	 */
	public String getSupplement() {
		return supplement;
	}

	/**
	 * 设置补充
	 */
	public void setSupplement(String supplement) {
		this.supplement = supplement;
	}

	/**
	 * 获取专案类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置专案类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取征集类型
	 */
	public int getStyle() {
		return style;
	}

	/**
	 * 设置征集类型
	 */
	public void setStyle(int style) {
		this.style = style;
	}

	/**
	 * 获取是否显示(1-是 0-否)
	 */
	public byte getIsShow() {
		return isShow;
	}

	/**
	 * 设置是否显示(1-是 0-否)
	 */
	public void setIsShow(byte isShow) {
		this.isShow = isShow;
	}

	/**
	 * 获取是否审核通过(1-是 0-否)
	 */
	public byte getIsPass() {
		return isPass;
	}

	/**
	 * 设置是否审核通过(1-是 0-否)
	 */
	public void setIsPass(byte isPass) {
		this.isPass = isPass;
	}

	/**
	 * 获取参与人数
	 */
	public int getTakePartInNum() {
		return takePartInNum;
	}

	/**
	 * 设置参与人数
	 */
	public void setTakePartInNum(int takePartInNum) {
		this.takePartInNum = takePartInNum;
	}

	/**
	 * 获取关注人数
	 */
	public int getFansNum() {
		return fansNum;
	}

	/**
	 * 设置关注人数
	 */
	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}

	/**
	 * 获取创意数
	 */
	public int getIdeaNum() {
		return ideaNum;
	}

	/**
	 * 设置创意数
	 */
	public void setIdeaNum(int ideaNum) {
		this.ideaNum = ideaNum;
	}

	/**
	 * 获取吐槽数
	 */
	public int getFlowNum() {
		return flowNum;
	}

	/**
	 * 设置吐槽数
	 */
	public void setFlowNum(int flowNum) {
		this.flowNum = flowNum;
	}

	/**
	 * 获取统计专案的点赞数
	 */
	public int getPraiseNum() {
		return praiseNum;
	}

	/**
	 * 设置统计专案的点赞数
	 */
	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	/**
	 * 获取阅读数
	 */
	public int getReadNum() {
		return readNum;
	}

	/**
	 * 设置阅读数
	 */
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	/**
	 * 获取专案结束时间
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 设置专案结束时间
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取专案金额
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * 设置专案金额
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * 获取列表排序字段
	 */
	public long getOrderIndex() {
		return orderIndex;
	}

	/**
	 * 设置列表排序字段
	 */
	public void setOrderIndex(long orderIndex) {
		this.orderIndex = orderIndex;
	}

	/**
	 * 获取图片数组JSON
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 设置图片数组JSON
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 获取视频地址
	 */
	public String getVideo() {
		return video;
	}

	/**
	 * 设置视频地址
	 */
	public void setVideo(String video) {
		this.video = video;
	}

	/**
	 * 获取报告下载地址
	 */
	public String getReport() {
		return report;
	}

	/**
	 * 设置报告下载地址
	 */
	public void setReport(String report) {
		this.report = report;
	}

	/**
	 * 获取专案模板id
	 */
	public long getCaseTemplateId() {
		return caseTemplateId;
	}

	/**
	 * 设置专案模板id
	 */
	public void setCaseTemplateId(long caseTemplateId) {
		this.caseTemplateId = caseTemplateId;
	}

	/**
	 * 获取模块列表JSON
	 */
	public String getModuleList() {
		return moduleList;
	}

	/**
	 * 设置模块列表JSON
	 */
	public void setModuleList(String moduleList) {
		this.moduleList = moduleList;
	}

	/**
	 * 获取创意模板id
	 */
	public long getIdeaTemplateId() {
		return ideaTemplateId;
	}

	/**
	 * 设置创意模板id
	 */
	public void setIdeaTemplateId(long ideaTemplateId) {
		this.ideaTemplateId = ideaTemplateId;
	}

	/**
	 * 获取创意模板主层级id
	 */
	public long getIdeaLayerId() {
		return ideaLayerId;
	}

	/**
	 * 设置创意模板主层级id
	 */
	public void setIdeaLayerId(long ideaLayerId) {
		this.ideaLayerId = ideaLayerId;
	}

	/**
	 * 获取是否匹配执行方
	 */
	public int getIsExecute() {
		return isExecute;
	}

	/**
	 * 设置是否匹配执行方
	 */
	public void setIsExecute(int isExecute) {
		this.isExecute = isExecute;
	}

	/**
	 * 获取是否媒体投放
	 */
	public int getIsMedia() {
		return isMedia;
	}

	/**
	 * 设置是否媒体投放
	 */
	public void setIsMedia(int isMedia) {
		this.isMedia = isMedia;
	}


}