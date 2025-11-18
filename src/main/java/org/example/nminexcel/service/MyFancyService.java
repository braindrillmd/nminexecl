package org.example.nminexcel.service;

import org.example.nminexcel.model.dto.NthMinExcelResponseDTO;

public interface MyFancyService {
    NthMinExcelResponseDTO doStuff(int n, String localPath);
}