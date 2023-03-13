package ru.dinerik.ECM.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность поручения для ответов (отображения)")
public class OrderForResponse {

    @Schema(description = "Идентификатор поручения")
    private Long id;
    @Schema(description = "Предмет поручения")
    private String subject;
    @Schema(description = "Срок исполнения поручения")
    private LocalDateTime timeExecution;
    @Schema(description = "Текст поручения")
    private String text;
    @Schema(description = "Автор поручения")
    private Long authorId;
    @Schema(description = "Исполнители поручения")
    private Set<Long> executorsIds;
    @Schema(description = "Статус поручения")
    private String orderStateString;
}