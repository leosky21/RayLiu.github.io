package cn.hhit.eshop.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ProductBean {
	// Fields

		private Integer id;
		@JsonIgnoreProperties(value={"account"})
		private Category category;
		private String name;
		private Double price;
		private byte[] pic;
		private String remark;
		private String xremark;
		private Timestamp date;
		private Boolean commend;
		private Boolean open;

		public ProductBean() {
			super();
		}

		// Constructors
		public ProductBean(Integer id, Category category, String name, Double price, byte[] pic, String remark,
				String xremark, Timestamp date, Boolean commend, Boolean open) {
			super();
			this.id = id;
			this.category = category;
			this.name = name;
			this.price = price;
			this.pic = pic;
			this.remark = remark;
			this.xremark = xremark;
			this.date = date;
			this.commend = commend;
			this.open = open;
		}
		
		
		public Integer getId() {
			return this.id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		
		public Category getCategory() {
			return this.category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getPrice() {
			return this.price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public byte[] getPic() {
			return this.pic;
		}

		public void setPic(byte[] pic) {
			this.pic = pic;
		}

		public String getRemark() {
			return this.remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getXremark() {
			return this.xremark;
		}

		public void setXremark(String xremark) {
			this.xremark = xremark;
		}

		public Timestamp getDate() {
			return this.date;
		}

		public void setDate(Timestamp date) {
			this.date = date;
		}

		public Boolean getCommend() {
			return this.commend;
		}

		public void setCommend(Boolean commend) {
			this.commend = commend;
		}

		public Boolean getOpen() {
			return this.open;
		}

		public void setOpen(Boolean open) {
			this.open = open;
		}

}
