package br.com.thiagosousa.firebaseexamples.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemRecyclerView implements Parcelable {
    private String title;
    private String description;
    private String date;
    private int logo;

    public ItemRecyclerView() {
    }

    public ItemRecyclerView(String title, String description, String date, int logo) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.logo = logo;
    }

    protected ItemRecyclerView(Parcel in) {
        title = in.readString();
        description = in.readString();
        date = in.readString();
        logo = in.readInt();
    }

    public static final Creator<ItemRecyclerView> CREATOR = new Creator<ItemRecyclerView>() {
        @Override
        public ItemRecyclerView createFromParcel(Parcel in) {
            return new ItemRecyclerView(in);
        }

        @Override
        public ItemRecyclerView[] newArray(int size) {
            return new ItemRecyclerView[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeInt(logo);
    }
}
