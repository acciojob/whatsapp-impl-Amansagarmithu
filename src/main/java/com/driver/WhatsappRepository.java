package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private HashMap<String,User> users;
    private HashSet<Message> mesg;
    private int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 1;
        this.users = new HashMap<>();
        this.mesg = new HashSet<>();
    }
    public String adduser(String number,String name)throws Exception{
        if(userMobile.contains(number)){
            return "jaa";
        }
        User newuser = new User(name,number);
        users.put(number,newuser);
        userMobile.add(number);
        return "SUCCESS";
    }
    public Group addGroup(List<User> userss){
        String name = "";
        if(userss.size()>2){
            customGroupCount++;
            name = "Group "+customGroupCount;
        }
        else{
            name = userss.get(1).getName();
        }
        Group gr = new Group(name,customGroupCount);
        groupUserMap.put(gr,userss);
        adminMap.put(gr,userss.get(0));
        return gr;
    }
    public int createMessage(String content){
        Message message = new Message(messageId,content);
        messageId++;
        mesg.add(message);
        return message.getId();
    }
    public int sendmessage(Message msg,User ud,Group gp) throws Exception{
        if(!groupUserMap.containsKey(gp)){
            throw new Exception();
        }
        boolean tf = true;
        for(User u : groupUserMap.get(gp)){
            if(ud.getMobile().equals(u.getMobile())) tf = false;
        }
        if(tf) throw new Exception();
        return groupUserMap.get(gp).size();
    }
    public String changeAdmin(User absorver,User us,Group gp){
        if(!groupUserMap.containsKey(gp)) return "Group does not exist";
        User ass = adminMap.get(gp);
        if(!absorver.getMobile().equals(ass.getMobile())) return "Approver does not have rights";
        boolean tf = true;
        for(User u : groupUserMap.get(gp)){
            if(us.getMobile().equals(u.getMobile())){
                tf = false;
                break;
            }
        }
        if(tf) return "User is not a participant";
        adminMap.put(gp,us);
        return "SUCCESS";
    }
}
