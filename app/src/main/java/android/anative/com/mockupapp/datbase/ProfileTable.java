package android.anative.com.mockupapp.datbase;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */
@Table(name = "profile_table")
public class ProfileTable extends Model {
    public ProfileTable(){

    }
    @Column(name = "_id")
    private long _id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "department")
    private String department;

    @Column(name = "gender")
    private String gender;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "color_code")
    private int color;

    @Column(name = "age")
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String  age) {
        this.age = age;
    }

    @Column(name = "pets_count")
    private String pets_count;

    @Column(name = "about")
    private String about;

    @Column(name = "dob")
    private String date;

    public static List<ProfileTable> getPostComments() {
        return new Select().from(ProfileTable.class).orderBy("id").execute();
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getUser_name() {
        return user_name == null ? "" : user_name.equals("null") ? "" : user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDepartment() {
        return department == null ? "" : department.equals("null") ? "" : department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender == null ? "" : gender.equals("null") ? "" : gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification == null ? "" : qualification.equals("null") ? "" : qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getPets_count() {
        return pets_count == null ? "" : pets_count.equals("null") ? "" : pets_count;
    }

    public void setPets_count(String pets_count) {
        this.pets_count = pets_count;
    }

    public String getAbout() {
        return about == null ? "" : about.equals("null") ? "" : about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDate() {
        return date == null ? "" : date.equals("null") ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
