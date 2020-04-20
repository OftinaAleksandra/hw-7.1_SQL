package data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VerificationCode {

    private String id;
    private String user_id;
    private String code;
    private String created;
}
