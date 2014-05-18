package com.castro.tops;

public class Restaurant {

	private String mName;
	private String mAddress;
	private String mPhone;
	private int mPrice;
	private int mImage;
	private int mRank;

	public Restaurant() {
	}

	public Restaurant(String name, String address, String phone, int price,
			int image) {
		mName = name;
		mAddress = address;
		mPhone = phone;
		mPrice = price;
		mImage = image;
	}

	public int getImage() {
		return mImage;
	}

	public int getRank() {
		return mRank;
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public String getAddress() {
		return mAddress;
	}

	public void setAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public void setRank(int rank) {
		this.mRank = rank;
	}

	public String getPhone() {
		return mPhone;
	}

	public void setPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public int getPrice() {
		return mPrice;
	}

	public void setPrice(int mPrice) {
		this.mPrice = mPrice;
	}

	public void setImage(int image) {
		mImage = image;
	}

}
