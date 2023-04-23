package org.TastyTiffin.model.request;

import java.util.Optional;

public class AddUserRequest {
    private Optional<String> name;
    private Optional<String> address;
    private Optional<String> id;
    private Optional<String> phoneNum;
    private Optional<String> email;

    public AddUserRequest() {
        name = Optional.empty();
        address = Optional.empty();
        id = Optional.empty();
        phoneNum = Optional.empty();
        email = Optional.empty();
    }

    public AddUserRequest(Optional<String> name, Optional<String> address, Optional<String> id, Optional<String> phoneNum, Optional<String> email) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getAddress() {
        return address;
    }

    public void setAddress(Optional<String> address) {
        this.address = address;
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Optional<String> getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Optional<String> phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AddUserRequest{" +
                "name=" + name +
                ", address=" + address +
                ", id=" + id +
                ", phoneNum=" + phoneNum +
                ", email=" + email +
                '}';
    }
}
