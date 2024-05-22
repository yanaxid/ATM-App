package data.repository;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import data.constant.BankCompany;
import data.model.Bank;
import data.model.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class BankRepo {

    private static final long DEFAULT_DAILY_EXPENSE = 5_000_000;
    private static final long DEFAULT_WITHDRAWAL_LIMIT = 2_500_000;

    private static final Set<Bank> store = new HashSet<>();
    
    
    static {
        Bank bri = Bank.builder()
                .id(UUID.randomUUID().toString())
                .name(BankCompany.BRI.getName())
                .maxExpensePerUserDaily(DEFAULT_DAILY_EXPENSE)
                .maxExpensePerWithdrawal(DEFAULT_WITHDRAWAL_LIMIT)
                .build();
        store.add(bri);
        
        //customer
        bri.getCustomers().add(Customer.builder()
                .id(UUID.randomUUID().toString())
                .account("11")
                .pin("11")
                .fullName("Nasabah BRI 1")
                .balance(5_000_000)
                .build());
        
        
        ///------------

        Bank bni = Bank.builder()
                .id(UUID.randomUUID().toString())
                        .name(BankCompany.BNI.getName())
                .depositFeature(true)
                        .maxExpensePerUserDaily(DEFAULT_DAILY_EXPENSE)
                        .maxExpensePerWithdrawal(DEFAULT_WITHDRAWAL_LIMIT)
                .build();
        store.add(bni);
        bni.getCustomers().add(Customer.builder()
                .id(UUID.randomUUID().toString())
                .account("22")
                .pin("22")
                .fullName("Nasabah BNI 1")
                .balance(3_000_000)
                .build());

        
      ///------------
        
        Bank mandiri = Bank.builder()
                .id(UUID.randomUUID().toString())
                .name(BankCompany.MANDIRI.getName())
                .depositFeature(true)
                .maxExpensePerUserDaily(DEFAULT_DAILY_EXPENSE)
                .maxExpensePerWithdrawal(DEFAULT_WITHDRAWAL_LIMIT)
                .build();
        store.add(mandiri);
        mandiri.getCustomers().add(Customer.builder()
                .id(UUID.randomUUID().toString())
                .account("33")
                .pin("33")
                .fullName("Nasabah Mandiri 1")
                .balance(1_500_000)
                .build());

        
        ///------------
        Bank bca = Bank.builder()
                .id(UUID.randomUUID().toString())
                .name(BankCompany.BCA.getName())
                .maxExpensePerUserDaily(DEFAULT_DAILY_EXPENSE)
                 .maxExpensePerWithdrawal(DEFAULT_WITHDRAWAL_LIMIT)
                .build();
        store.add(bca);
        
        bca.getCustomers().add(Customer.builder()
                .id(UUID.randomUUID().toString())
                .account("44")
                .pin("44")
                .fullName("Nasabah BCA 1")
                .balance(240_000)
                .build());
    }

    public static Optional<Bank> findBankByName(String name) {
        return store.stream().filter(item -> name.equals(item.getName())).findAny();
    }

}
