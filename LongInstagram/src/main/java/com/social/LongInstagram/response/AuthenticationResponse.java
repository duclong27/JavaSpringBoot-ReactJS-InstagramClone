package com.social.LongInstagram.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder

public class AuthenticationResponse {

    boolean isAuthenticated;
}
