package vo;

import lombok.Data;

@Data
public class AccountVo {

    private int account_no;
    private int fk_mem_no;
    private String create_date;
    private int balance;
}
