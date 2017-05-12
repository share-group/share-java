package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户收货地址
 */
@Pojo
public class DUserAddress extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 收件人姓名
	 */
	private String name;
	/**
	 * 省份id
	 */
	private String provinceId;
	/**
	 * 城市id
	 */
	private String cityId;
	/**
	 * 地区
	 */
	private int region;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String postcode;
	/**
	 * 备注
	 */
	private String remark;

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
	 * 获取联系电话
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置联系电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取收件人姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置收件人姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取省份id
	 */
	public String getProvinceId() {
		return provinceId;
	}

	/**
	 * 设置省份id
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 获取城市id
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * 设置城市id
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * 获取地区
	 */
	public int getRegion() {
		return region;
	}

	/**
	 * 设置地区
	 */
	public void setRegion(int region) {
		this.region = region;
	}

	/**
	 * 获取地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取邮政编码
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * 设置邮政编码
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * 获取备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


}