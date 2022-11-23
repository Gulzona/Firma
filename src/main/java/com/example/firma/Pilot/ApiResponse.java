package com.example.firma.Pilot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {

    private String xabar;
    private boolean holat;

}
