package com.example.Banking.controller;

import com.example.Banking.entity.Transactions;
import com.example.Banking.entity.User;
import com.example.Banking.repository.TransactionRepository;
import com.example.Banking.repository.UserRepository;
import com.example.Banking.services.TransactionService;
import com.example.Banking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;
    private User doAuthorization(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user=(UserDetails)auth.getPrincipal();
        return userService.findUserByEmail(user.getUsername());

    }
    @GetMapping("/")
    public String user(){
        User uid=doAuthorization();
        return "redirect:/users/user?uid="+uid.getAccount();
    }
    @GetMapping("/user")
    public String getUser(@RequestParam("uid") int uid, Model model){
        try {
            User temp=doAuthorization();
            if(temp.getAccount()!=uid){
                return "redirect:/users/user?uid="+temp.getAccount();
            }
            User user = userService.findUserByAccountNo(uid);
            model.addAttribute("user", user);
            return "user";
        }
        catch(Exception e){
            return e.toString();
        }
    }

    @GetMapping("/deposit")
    public String deposit(@RequestParam("uid") int uid,Model model){
        User temp=doAuthorization();
        if(temp.getAccount()!=uid) uid=temp.getAccount();
        User user=userService.findUserByAccountNo(uid);
        model.addAttribute("user",user);
        return "deposit";
    }

    @PostMapping("/deposit")
    public String postDeposit(@ModelAttribute("user") User user){
        User temp=userService.findUserByAccountNo(user.getAccount());
        user.setBalance(user.getBalance()+temp.getBalance());
        userService.saveUser(user);
        return "redirect:/users/user?uid="+user.getAccount();
    }

    @GetMapping("/transfer")
    public String transfer(@RequestParam("uid") int uid,Model model){
        User temp=doAuthorization();
        if(temp.getAccount()!=uid) uid=temp.getAccount();
        Transactions transactions=new Transactions();
        transactions.setDate(LocalDateTime.now().toString());
        transactions.setFromAccount(uid);
        model.addAttribute("transactions",transactions);

        return "transfer";
    }


    @PostMapping("/transfer")
    public String postTransfer(@ModelAttribute("transactions") Transactions transaction){
        transactionService.doTransaction(transaction);
        return "redirect:/users/user?uid="+transaction.getFromAccount();
    }

    @GetMapping("/listTransactions")
    public String getTransactions(@RequestParam("uid") int uid, Model model){
        User temp=doAuthorization();
        if(temp.getAccount()!=uid) uid=temp.getAccount();
        List<Transactions> listTransactions=transactionService.listOfTransactions(uid);
        System.out.println(listTransactions);
        model.addAttribute("transactions",listTransactions);
        model.addAttribute("account",uid);
        return "listTransactions";
    }

}
