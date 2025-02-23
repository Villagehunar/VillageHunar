package com.villagehunar.seeker.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "customers")
public class CustomerBean extends UserBean{
    private String shippingAddress;
    private String billingAddress;
    private String paymentMethod;
}
