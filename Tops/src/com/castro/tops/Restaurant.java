package com.castro.tops;

public class Restaurant {

	private String mName;
	private String mAddress;
	private String mPhone;
	private int mPrice;
	private int mImageFront;
	private int mMap;
	private int mRank;
	private int mVotes;

	private final String DEFAULT_NAME = "";
	private final String DEFAULT_ADDRESS = "Missing address";
	private final String DEFAULT_PHONE = "Missing phone number";
	private final int DEFAULT_PRICE = 0;
	private final int DEFAULT_IMAGE = 0;
	private final int DEFAULT_RANK = 0;

	public Restaurant() {
	}

	public String getAddress() {
		return mAddress;
	}

	public int getMap() {
		return mMap;
	}

	public int getImage() {
		return mImageFront;
	}

	public String getName() {
		return mName;
	}

	public String getPhone() {
		return mPhone;
	}

	public int getPrice() {
		return mPrice;
	}

	public int getRank() {
		return mRank;
	}

	public int getVotes() {
		return mVotes;
	}

	public void setAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public void setImage(int image) {
		mImageFront = image;
	}

	public void setMissingValues() {
		if (mName == null || mName.isEmpty()) {
			mName = DEFAULT_NAME;
		}
		if (mAddress == null || mAddress.isEmpty()) {
			mAddress = DEFAULT_ADDRESS;
		}
		if (mPhone == null || mPhone.isEmpty()) {
			mPhone = DEFAULT_PHONE;
		}
		if (mPrice == 0) {
			mPrice = DEFAULT_PRICE;
		}
		if (mImageFront == 0) {
			mImageFront = DEFAULT_IMAGE;
		}
		if (mRank == 0) {
			mRank = DEFAULT_RANK;
		}
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public void setPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public void setPrice(int mPrice) {
		this.mPrice = mPrice;
	}

	public void setRank(int rank) {
		this.mRank = rank;
	}

	public void setVotes(int votes) {
		this.mVotes = votes;
	}

	public void setMap(int map) {
		this.mMap = map;
	}

}
