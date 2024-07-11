package vo;

import lombok.Data;

@Data
public class BankVo {

    private int depwit_no;
    private int fk_account_no ;
    private String tran_date;
    private String tran_tar;
    private int tran_amo;
    private int depwit_cnt;
}
