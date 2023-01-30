package com.example.subpayment.util;

import com.example.subpayment.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatus {

    private Status status;

    private String user_message;

}
