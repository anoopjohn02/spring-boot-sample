package com.anoop.examples.data.entities;

import com.anoop.examples.enums.Severity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Data
@Document(collection = "alert")
public class AlertEntity extends GenericModel{
    @Id
    private ObjectId _id;
    private String uId;
    private String deviceId;
    private String type;
    private Severity severity;
    private String key;
    private String description;
    private Date dateTime;
    private Map<String, Object> metadata;
}
