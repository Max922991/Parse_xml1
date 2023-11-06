package com.example.parse_xml1;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressObjectDTO {

    private String id;
    private String objectId;
    private String objectGuid;
    private String changeId;
    private String name;
    private String typeName;
    private String level;
    private String operTypeId;
    private String prevId;
    private String nextId;
    private String updateDate;
    private String startDate;
    private String endDate;
    private String isActual;
    private String isActive;
}
