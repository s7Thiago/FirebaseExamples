package br.com.thiagosousa.firebaseexamples.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemForActivityWithListViewHolder implements Parcelable{

    private String title;
    private String time;
    private String address;

    public ItemForActivityWithListViewHolder() {
    }

    public ItemForActivityWithListViewHolder(String title, String time, String address) {
        this.title = title;
        this.time = time;
        this.address = address;
    }

    protected ItemForActivityWithListViewHolder(Parcel in) {
        title = in.readString();
        time = in.readString();
        address = in.readString();
    }

    public static final Creator<ItemForActivityWithListViewHolder> CREATOR = new Creator<ItemForActivityWithListViewHolder>() {
        @Override
        public ItemForActivityWithListViewHolder createFromParcel(Parcel in) {
            return new ItemForActivityWithListViewHolder(in);
        }

        @Override
        public ItemForActivityWithListViewHolder[] newArray(int size) {
            return new ItemForActivityWithListViewHolder[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.valueOf(new StringBuilder()
                .append("Title: ").append(this.getTitle())
                .append("Time: ").append(this.getTitle())
                .append("Address:").append(this.getAddress()));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(time);
        dest.writeString(address);
    }
}
