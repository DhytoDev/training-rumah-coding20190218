package id.co.rumahcoding.sekolahku.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String gender;
    private String education;
    private String hobby;
    private String address;

    public Student() {}

    protected Student(Parcel in) {
        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        phone = in.readString();
        email = in.readString();
        gender = in.readString();
        education = in.readString();
        hobby = in.readString();
        address = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(phone);
        parcel.writeString(email);
        parcel.writeString(gender);
        parcel.writeString(education);
        parcel.writeString(hobby);
        parcel.writeString(address);
    }
}
