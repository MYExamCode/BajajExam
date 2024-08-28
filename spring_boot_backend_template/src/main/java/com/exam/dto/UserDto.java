package com.exam.dto;

public class UserDto {
 private String firstName;
 private String lastName;
 private Long phoneNumber;
 private String emailId;

 
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

 public Long getPhoneNumber() {
     return phoneNumber;
 }

 public void setPhoneNumber(Long phoneNumber) {
     this.phoneNumber = phoneNumber;
 }

 public String getEmailId() {
     return emailId;
 }

 public void setEmailId(String emailId) {
     this.emailId = emailId;
 }
}
