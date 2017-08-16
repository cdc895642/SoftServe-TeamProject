package com.softserve.teamproject.entity;

import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "first_name")
  @Size(min = 2, max = 20, message = "Wrong size")
  @Pattern(regexp = "[\\p{IsAlphabetic}\\p{IsWhite_Space}-/]+", message = "Wrong pattern")
  private String firstName;

  @Column(name = "last_name")
  @Size(min = 2, max = 20, message = "Wrong size")
  @Pattern(regexp = "[\\p{IsAlphabetic}\\p{IsWhite_Space}-/]+", message = "Wrong pattern")
  private String lastName;

  @Lob
  @Column(name = "image")
  private byte[] image;

  @Column(name = "image_name")
  @Pattern(regexp = ".+(\\.jpg|\\.jpeg|\\.tiff|\\.png)",
      message = "{illegalArgs.student.cvOrImage.name}")
  private String imageName;

  @Lob
  @Column(name = "cv")
  private byte[] cv;

  @Column(name = "cv_name")
  @Pattern(regexp = ".+(\\.doc|\\.docx|\\.pdf|\\.rtf)",
      message = "{illegalArgs.student.cvOrImage.name}")
  private String cvName;

  @ManyToOne
  @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
  private Group group;

  @ManyToOne
  @JoinColumn(name = "english_level_id", referencedColumnName = "id")
  private EnglishLevel englishLevel;

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public byte[] getCv() {
    return cv;
  }

  public void setCv(byte[] cv) {
    this.cv = cv;
  }

  public String getCvName() {
    return cvName;
  }

  public void setCvName(String cvName) {
    this.cvName = cvName;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public EnglishLevel getEnglishLevel() {
    return englishLevel;
  }

  public void setEnglishLevel(EnglishLevel englishLevel) {
    this.englishLevel = englishLevel;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }
    if (otherObject == null) {
      return false;
    }
    if (getClass() != otherObject.getClass()) {
      return false;
    }
    Student other = (Student) otherObject;
    return Objects.equals(firstName, other.getFirstName()) && Objects
        .equals(lastName, other.getLastName()) && Objects
        .equals(group, other.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  @Override
  public String toString() {
    return "User{"
        + "id=" + id
        + ", firstName='" + firstName + '\''
        + ", lastName='" + lastName + '\''
        + ", image=" + Arrays.toString(image)
        + ", group='" + group.getName() + '\''
        + ", englishLevel='" + englishLevel.getName() + '\''
        + '}';
  }
}
