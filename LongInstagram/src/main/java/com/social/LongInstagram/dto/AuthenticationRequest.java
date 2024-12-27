package com.social.LongInstagram.dto;

import lombok.*;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationRequest {

    private String email;
    private String password;


}
