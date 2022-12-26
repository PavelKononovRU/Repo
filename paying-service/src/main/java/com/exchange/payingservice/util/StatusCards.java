package com.exchange.payingservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusCards {

    private String title;
    private String user_message;
    private Long card_id;

}
