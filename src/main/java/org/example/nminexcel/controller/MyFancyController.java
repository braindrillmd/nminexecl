package org.example.nminexcel.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.nminexcel.model.dto.NthMinExcelDTO;
import org.example.nminexcel.model.dto.NthMinExcelResponseDTO;
import org.example.nminexcel.service.MyFancyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MyFancyController {
    private final MyFancyService myFancyService;

    @PostMapping("/do-stuff/")
    @Operation(description = "Найти N-ый минимальный элемент в колонке Excel (.xslx) файла")
    ResponseEntity<NthMinExcelResponseDTO> doStuff(@RequestBody @Valid NthMinExcelDTO nthMinExcelDTO) {
        NthMinExcelResponseDTO response =
            myFancyService.doStuff(nthMinExcelDTO.n(), nthMinExcelDTO.localPath());
        if(response.success()){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
