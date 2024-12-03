package ru.panyukovnn.kafkamentoring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DemoRequest {

    private String id;
    private String text;
}
