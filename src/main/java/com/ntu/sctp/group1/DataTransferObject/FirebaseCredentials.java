package com.ntu.sctp.group1.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FirebaseCredentials extends InputStream {
    private String type;
    private String project_id;
    private String private_key_id;
    private String private_key;
    private String client_email;
    private String client_id;
    private String auth_uri;
    private String token_uri;
    private String auth_provider_x509_cert_url;
    private String client_x509_cert_url;

    @Override
    public int read() throws IOException {
        return 0;
    }

}

