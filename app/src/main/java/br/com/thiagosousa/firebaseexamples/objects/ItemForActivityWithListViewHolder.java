package br.com.thiagosousa.firebaseexamples.objects;

public class ItemForActivityWithListViewHolder {

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
}
