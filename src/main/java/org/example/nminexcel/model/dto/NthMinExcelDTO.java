package org.example.nminexcel.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(name = "NthMinExcel", description = "dummy")
public record NthMinExcelDTO(
    @Schema(
        description = "'N' в 'N-ом минимальном числе'",
        defaultValue = "3",
        minimum = "1")
    @NotNull
    Integer n,

    @Schema(
        description = "Путь к локальному файлу",
        defaultValue = "./data/input.xlsx")
    @NotEmpty
    String localPath
) {
}
