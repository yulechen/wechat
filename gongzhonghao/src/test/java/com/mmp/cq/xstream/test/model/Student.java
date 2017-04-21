package com.mmp.cq.xstream.test.model;

class Student {

    private int RollNo;

    private String firstName;
    private String lastName;
    private String className;

    private Address address;


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


  


    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    public Address getAddress() {
        return address;
    }


    public void setAddress(Address address) {
        this.address = address;
    }


    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Student [ ");
        stringBuilder.append("\nfirstName: ");
        stringBuilder.append(firstName);
        stringBuilder.append("\nlastName: ");
        stringBuilder.append(lastName);
        stringBuilder.append("\nrollNo: ");
        stringBuilder.append(RollNo);
        stringBuilder.append("\nclassName: ");
        stringBuilder.append(className);
        stringBuilder.append("\naddress: ");
        stringBuilder.append(address);
        stringBuilder.append(" ]");

        return stringBuilder.toString();
    }


    public int getRollNo() {
        return RollNo;
    }


    public void setRollNo(int rollNo) {
        RollNo = rollNo;
    }
}