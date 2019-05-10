/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.demo.common;


/**
 * 库存Entity
 * @author luotianwen
 * @version 2017-10-29
 */
public class Stock  {
	
	private static final long serialVersionUID = 1L;
	private String articleno;		// 商品货号
	private String brandname;		// 品牌
	private String uksize;		// 尺码2
	private String size;		// 尺码1
	private int innernum;		// 库存数量
	private double marketprice;		// 市场价
	private String quarter;		// 商品上市季节



	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getUksize() {
		return uksize;
	}

	public void setUksize(String uksize) {
		this.uksize = uksize;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getInnernum() {
		return innernum;
	}

	public void setInnernum(int innernum) {
		this.innernum = innernum;
	}

	public double getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(double marketprice) {
		this.marketprice = marketprice;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
}