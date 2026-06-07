import java.util.*;

class Notification{

    private String id;
    private String type;
    private String message;
    private String timestamp;

    public Notification(String id,String type,String message,String timestamp){
        this.id=id;
        this.type=type;
        this.message=message;
        this.timestamp=timestamp;
    }

    public String getType(){
        return type;
    }

    public String getMessage(){
        return message;
    }

    public String getTimestamp(){
        return timestamp;
    }
}

public class PriorityNotification {

    public static int getWeight(String type){

        if(type.equals("Placement")){
            return 3;
        }

        if(type.equals("Result")){
            return 2;
        }

        return 1;
    }

    public static void main(String[] args){

        List<Notification> notifications=new ArrayList<>();

        notifications.add(new Notification(
                "d146095a",
                "Result",
                "mid-sem",
                "2026-04-22 17:51:30"
        ));

        notifications.add(new Notification(
                "b283218f",
                "Placement",
                "CSX Corporation hiring",
                "2026-04-22 17:51:18"
        ));

        notifications.add(new Notification(
                "81589ada",
                "Event",
                "farewell",
                "2026-04-22 17:51:06"
        ));

        notifications.add(new Notification(
                "8a7412bd",
                "Placement",
                "Advanced Micro Devices Inc. hiring",
                "2026-04-22 17:49:42"
        ));

        PriorityQueue<Notification> pq=
                new PriorityQueue<>((a,b)->getWeight(a.getType())-getWeight(b.getType())
                );

        for(Notification n:notifications){

            pq.offer(n);

            if(pq.size()>10){
                pq.poll();
            }
        }

        List<Notification> result=new ArrayList<>();

        while(!pq.isEmpty()){
            result.add(pq.poll());
        }

        Collections.reverse(result);

        System.out.println("Top 10 Priority Notifications");

        for(Notification n:result){
            System.out.println(n.getType() +" | " +n.getMessage() +" | " +n.getTimestamp()
            );
        }
    }
}