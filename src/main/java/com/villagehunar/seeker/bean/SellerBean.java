package com.villagehunar.seeker.bean;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@EqualsAndHashCode(callSuper = true)
@Document(collection = "sellers")
public class SellerBean extends UserBean{
    private String storeName;
    private String taxId;
    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password=''" +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
