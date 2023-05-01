package org.TastyTiffin.model.response;

import java.util.Optional;

public class GetUserResopnse {
 private Optional<String> key ;
     private Optional<String> name;
     private Optional<String> id;
     private Optional<String> address;
     private Optional<String> phoneNum;
     private Optional<String> email;

    public GetUserResopnse() {
        key=Optional.empty();
        name=Optional.empty();
        id=Optional.empty();
        address=Optional.empty();
        phoneNum=Optional.empty();
        email=Optional.empty();
    }

    public GetUserResopnse(Optional<String> key, Optional<String> name, Optional<String> id, Optional<String> address, Optional<String> phoneNum, Optional<String> email) {
        this.key = key;
        this.name = name;
        this.id = id;
        this.address = address;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Optional<String> getKey() {
        return key;
    }

    public void setKey(Optional<String> key) {
        this.key = key;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Optional<String> getAddress() {
        return address;
    }

    public void setAddress(Optional<String> address) {
        this.address = address;
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
}
