package com.ps.sec.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;

/**
 * @author fubo
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
    private static final long serialVersionUID = -68497908884667710L;

    private String id;
    private String userId;
    private String username;
    private String password;
    private String residentId;
    private String name;
    private String sex;
    private String phoneNumber;
    private String year;
    private String month;
    private String day;
    private String province;
    private String city;
    private String area;
    private String street;
    private String address;
    private String membership;
    private String registrationTime;
}
