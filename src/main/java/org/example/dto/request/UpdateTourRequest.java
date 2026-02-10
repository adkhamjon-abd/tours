package org.example.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTourRequest {
    private String name;
    private int companyId;
    private int viewCount;
}
