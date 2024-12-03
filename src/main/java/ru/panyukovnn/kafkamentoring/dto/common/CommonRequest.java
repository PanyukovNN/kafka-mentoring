package ru.panyukovnn.kafkamentoring.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonRequest<T> {

    private T body;
}
