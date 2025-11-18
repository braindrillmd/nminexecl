package org.example.nminexcel.service.impl;

import org.example.nminexcel.model.dto.NthMinExcelResponseDTO;
import org.example.nminexcel.service.MyFancyService;
import org.example.nminexcel.util.Parser;
import org.springframework.stereotype.Service;

@Service
public class MyFancyServiceImpl  implements MyFancyService {
    @Override
    public NthMinExcelResponseDTO doStuff(int n, String localPath) {
        Integer number = null;

        try{
            number = Parser.parse(n , localPath).orElseThrow();
            return new NthMinExcelResponseDTO(true, number);
        }catch (Exception e){
            return new NthMinExcelResponseDTO(false, number);
        }
    }
}
