package com.exam.entity;

import javax.persistence.*;

@Entity
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String firstName;
 private String lastName;
 private Long phoneNumber;
 private String emailId;


 public Long getId() {
     return id;
 }

 public void setId(Long id) {
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
