package fr.iutvalence.info.m4104.gildedroseinn;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
	private final String name;

	private int sellIn;

	private int image;

	private int quality;

	public Item(String name, int image, int sellIn, int quality) {
		this.name = name;
		this.image = image;
		this.sellIn = sellIn;
		this.setQuality(quality);
	}

	public Item(Parcel parcel) {
		name = parcel.readString();
		sellIn = parcel.readInt();
		image = parcel.readInt();
		quality = parcel.readInt();
	}

	public String getName() {
		return this.name;
	}

	public int getImage() {
		return this.image;
	}

	public void setSellIn(int sellIn) {
		this.sellIn = sellIn;
	}

	public int getSellIn() {
		return this.sellIn;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(this.name);
		parcel.writeInt(sellIn);
		parcel.writeInt(image);
		parcel.writeInt(quality);
	}

	public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
		public Item createFromParcel(Parcel in) {
			return new Item(in);
		}

		@Override
		public Item[] newArray(int i) {
			return new Item[i];
		}
	};
}

