package com.example.dataanalyticrestfulapi.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
        @NotBlank(message = "Username is required !")
        private String username;
        @NotBlank(message = "Gender is also required !")
        private String gender;
        private String address;

}
