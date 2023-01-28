package com.driver;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {
    @Autowired
    WhatsappRepository repo;

    public String createUser(String number,String name) throws Exception {
        return repo.adduser(number,name);


    }
    public Group createGroup(List<User> users){
        return repo.addGroup(users);
    }
    public int createMessage(String content){
        return repo.createMessage(content);
    }
    public int sendMessage(Message msg,User send,Group grp)throws Exception{

            return repo.sendmessage(msg, send, grp);


    }
    public String changeAdmin(User approver,User user,Group group) throws Exception{

            return repo.changeAdmin(approver, user, group);


    }
}
