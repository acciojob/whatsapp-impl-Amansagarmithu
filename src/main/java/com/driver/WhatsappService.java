package com.driver;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {
    @Autowired
    WhatsappRepository repo;

    public String createUser(String number,String name) throws Exception {
        String ans = repo.adduser(number,name);
        if(ans.equals("SUCCESS")){
            return ans;
        }
        else{
            throw new Exception();
        }
    }
    public Group createGroup(List<User> users){
        return repo.addGroup(users);
    }
    public int createMessage(String content){
        return repo.createMessage(content);
    }
    public int sendMessage(Message msg,User send,Group grp)throws Exception{
        try {
            int ans = repo.sendmessage(msg, send, grp);
            return ans;
        }
        catch(Exception e){
            throw new Exception();
        }

    }
    public String changeAdmin(User approver,User user,Group group) throws Exception{
        try {
            String ans = repo.changeAdmin(approver, user, group);
            return ans;
        }
        catch(Exception e){
            throw new Exception();
        }
    }
}
