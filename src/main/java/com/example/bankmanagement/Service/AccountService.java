package com.example.bankmanagement.Service;

import DTO.AccountDTO_out;
import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.Model.Account;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AccountRepository;
import com.example.bankmanagement.Repository.AuthUserRepository;
import com.example.bankmanagement.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AuthUserRepository userRepository;


    public List<Account>getAll(){
        return accountRepository.findAll();
    }

    public List<Account>getMyAccounts(Integer customerId){
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null){
            throw new ApiException("Customer not found");
        }
       return accountRepository.findAllByCustomer(customerId);
    }





    public void add(Integer customerId,Account account){

        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null){
            throw new ApiException("Customer not found");
        }
        account.setCustomer(customer);
        accountRepository.save(account);
    }

    public void update(Integer customerId,Integer accountId,Account account){
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null){
            throw new ApiException("Customer not found");
        }
        Account oldAccount=accountRepository.findAccountById(accountId);
            if(oldAccount==null){
                throw new ApiException("Account not found");
            }
        if(!oldAccount.getCustomer().equals(customer)){
            throw new ApiException("This account does not belong to this customer");
        }
            oldAccount.setBalance(account.getBalance());
            oldAccount.setIsActive(account.getIsActive());
            accountRepository.save(oldAccount);
        }



        public void delete(Integer customerId,Integer accountId){
            Customer customer=customerRepository.findCustomerById(customerId);
            if(customer==null){
                throw new ApiException("Customer not found");
            }
            Account account =accountRepository.findAccountById(accountId);
            if(account ==null){
                throw new ApiException("Account not found");
            }
            if(!account.getCustomer().equals(customer)){
                throw new ApiException("This account does not belong to this customer");
            }
            accountRepository.delete(account);
        }


        public AccountDTO_out getDetails(Integer customerId , Integer accountId){

            Customer customer=customerRepository.findCustomerById(customerId);
            if(customer==null){
                throw new ApiException("Customer not found");
            }
            Account account =accountRepository.findAccountById(accountId);
            if(account ==null){
                throw new ApiException("Account not found");
            }

            if(!account.getCustomer().equals(customer)){
                throw new ApiException("This account does not belong to this customer");
            }

            AccountDTO_out dto=new AccountDTO_out();
            dto.setAccountNumber(account.getAccountNumber());
            dto.setBalance(account.getBalance());
            dto.setIsActive(account.getIsActive());

            return dto;
        }




        public void deposit (Integer customerId,Integer accountId,Integer amount){

            Customer customer=customerRepository.findCustomerById(customerId);
            if(customer==null){
                throw new ApiException("Customer not found");
            }
            Account account=accountRepository.findAccountById(accountId);
            if(account==null)
            {
                throw new ApiException("account not found");
            }
            if(!account.getCustomer().equals(customer)){
                throw new ApiException("This account does not belong to this customer");
            }

            if(account.getIsBlock()){
                throw new ApiException("Your account is block");
            }

            if (amount <= 0) {
                throw new ApiException("Amount must be greater than zero");
            }

            account.setBalance(account.getBalance()+amount);
            accountRepository.save(account);

        }



    public void Withdraw (Integer customerId,Integer accountId,Integer amount) {

        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("account not found");
        }

        if (!account.getCustomer().equals(customer)) {
            throw new ApiException("This account does not belong to this customer");
        }


        if(account.getIsBlock()){
            throw new ApiException("Your account is block");
        }

        if (amount <= 0) {
            throw new ApiException("Amount must be greater than zero");
        }
        if(account.getBalance()<amount){
            throw new ApiException("Insufficient balance");
        }

        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);



    }



        public void transfer(Integer customerIdOne,Integer accountOneId,Integer accountTowId,Integer amount){

          Customer customerOne=customerRepository.findCustomerById(customerIdOne);
            if(customerOne==null){
                throw new ApiException("Customer not found");
            }
            Account accountOne=accountRepository.findAccountById(accountOneId);
            if(accountOne==null)
            {
                throw new ApiException("account not found");
            }
            if(!accountOne.getCustomer().equals(customerOne)){
                throw new ApiException("This account does not belong to this customer");
            }

            Account accountTow=accountRepository.findAccountById(accountTowId);
            if(accountTow==null){
                throw new ApiException("Receiver account not found");
            }

            if(accountOne.getId().equals(accountTow.getId())){
                throw new ApiException("Cannot transfer to the same account ");
            }

            if(accountOne.getIsBlock()){
                throw new ApiException("Your account is block");
            }
            if(accountTow.getIsBlock()){
                throw new ApiException("Receiver account is block");
            }



            if(amount <= 0) {
                throw new ApiException("Amount must be greater than zero");
            }

            ///
            if(accountOne.getBalance()<amount){
                throw new ApiException("Insufficient balance");
            }

            accountOne.setBalance(accountOne.getBalance()-amount);
            accountRepository.save(accountOne);
            accountTow.setBalance(accountTow.getBalance()+amount);
            accountRepository.save(accountTow);
        }


        public void block(Integer adminId ,Integer accountId){
            User admin=userRepository.findById(adminId).orElse(null);
            if(admin==null){
                throw new ApiException("user Not found");
            }

            if (!admin.getRole().equals("ADMIN")) {
                throw new ApiException("Only admin can block an account");
            }

            Account account=accountRepository.findAccountById(accountId);

            if(account==null){
                throw new ApiException("account not found");
            }


            if(account.getIsBlock()){
                throw new ApiException("already block this account");
            }
            account.setIsBlock(true);
            accountRepository.save(account);
        }









}
