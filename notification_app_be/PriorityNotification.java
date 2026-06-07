import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Notification{

    private String id;
    private String type;
    private String message;
    private long timestamp;

    public Notification(String id,String type,String message,long timestamp){
        this.id=id;
        this.type=type;
        this.message=message;
        this.timestamp=timestamp;
    }

    public String getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public String getMessage(){
        return message;
    }

    public long getTimestamp(){
        return timestamp;
    }
}

public class PriorityNotification{

    public static int getWeight(String type){

        if(type.equals("Placement")){
            return 3;
        }

        if(type.equals("Result")){
            return 2;
        }

        return 1;
    }

    public static int getScore(Notification notification){
        return getWeight(notification.getType())+(int)notification.getTimestamp();
    }

    public static void main(String[] args){

        List<Notification> notifications=new ArrayList<>();

        notifications.add(new Notification("1","Placement","CSX Corporation Hiring",100));
        notifications.add(new Notification("2","Result","Mid Sem Results",90));
        notifications.add(new Notification("3","Event","Farewell Event",80));
        notifications.add(new Notification("4","Placement","AMD Hiring",110));
        notifications.add(new Notification("5","Result","Project Review",95));

        PriorityQueue<Notification> pq=new PriorityQueue<>(new java.util.Comparator<Notification>(){
                    @Override
                    public int compare(Notification a,Notification b){
                        return getScore(a)-getScore(b);
                    }
                }
        );

        for(Notification notification:notifications){
            pq.offer(notification);

            if(pq.size()>10){
                pq.poll();
            }
        }

        List<Notification> topNotifications=new ArrayList<>();

        while(!pq.isEmpty()){
            topNotifications.add(pq.poll());
        }

        Collections.reverse(topNotifications);

        System.out.println("Top 10 Priority Notifications");

        for(Notification notification:topNotifications){
            System.out.println(notification.getType()+" | "+notification.getMessage());
        }
    }
}