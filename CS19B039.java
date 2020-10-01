
import java.util.*;

interface BufferInterface {
    // To add a message to the buffer
    public void add(Message m);
    // To retrieve message from the buffer
    public Message remove();
}

interface SocketInterface {
    // To release the socket after the client is done using the channel
    public void release();
    // To get to know if the socket is currently being used by a client
    public boolean isBusy();
    // Used when the socket is granted to the client to set its status to busy
    public void register();
    // To send the message to client
    public void send(Message m);
    // To retrieve the message from the channel
    public Message retrieve();
}

interface TwoWayChannelInterface {
    // To get a socket connection to be able to use the channel
    public void getSocket();
}

class Message {
    String text;
    int status;
    Message(String t, int s) {
        this.text = t;
        this.status = s;
    }
}


class Buffer implements BufferInterface{
    Message[] msg=new Message[50];
    int r=0,f=0;
    public void add(Message m){
        msg[this.r++]=m;
    }
    public Message remove(){
        return msg[this.f++];
    }

}


class Socket implements SocketInterface{
    boolean busy=false;
    Buffer obj=new Buffer();
    public void release()
    {
        this.busy=false;
    }
    public boolean isBusy()
    {
        return busy;
    }
    public void register()
    {
        if(isBusy())
            System.out.println("Can't register");
        else {
            System.out.println("Availing connection");
            this.busy = true;
        }
    }
    public void send(Message m)
    {
        obj.add(m);
    }
    public Message retrieve()
    {
            return obj.remove();
    }
}

class TwoWayChannel{
    Socket obj1=new Socket();
    public Socket getSocket(){
        return obj1;
    }
}
public class CS19B039
{

    public static void main(String []args){
        Scanner scan=new Scanner(System.in);
        int i,k,t1=0;
        String s1="null";
        Message m=new Message(s1,t1);
        System.out.print("Enter the no of operations required: ");
        int n=scan.nextInt();
        TwoWayChannel[] tc=new TwoWayChannel[2];
        
        tc[0]=new TwoWayChannel();
        tc[1]=new TwoWayChannel();
        
        Socket A= tc[0].getSocket();
        Socket B= tc[1].getSocket();
        
        
        for(i=0;i<n;i++)
        {
            System.out.print("Enter Operation code: ");
            k=scan.nextInt();
            
            if(k==1){
                tc[0].getSocket().register();
            }
            else if(k==2)
            {
                tc[0].getSocket().release();
                System.out.println("Socket A is free");
            }
            else if(k==3)
            {
                if(tc[0].getSocket().isBusy())
                    System.out.println("Socket A is busy");
                else
                    System.out.println("Socket A is busy");
            }
            else if(k==4)
            {
                if(A.isBusy()==false)
                {
                    System.out.println("Cant add a message");
                }
                else {
                    System.out.println("add a message to socket A");
                    s1 = scan.nextLine();
                    s1 = scan.nextLine();
                    t1 = scan.nextInt();
                    m = new Message(s1, t1);
                    tc[0].getSocket().send(m);
                }
            }
            else if(k==5)
            {
                System.out.println("Message Retrieved by Socket A: "+tc[1].getSocket().retrieve().text);
            }
            else if(k==6)
                tc[1].getSocket().register();
            else if(k==7)
            {
                tc[1].getSocket().release();
                System.out.println("Socket B is free");
            }
            else if(k==8)
            {
                if(tc[1].getSocket().isBusy())
                    System.out.println("Socket B is busy");
                else
                    System.out.println("Socket B is busy");
            }
            else if(k==9)
            {
                if(B.isBusy()==false)
                {
                    System.out.println("Cant add a message");
                }
                else {
                    System.out.println("add a message to socket B");
                    s1 = scan.nextLine();
                    s1 = scan.nextLine();
                    t1 = scan.nextInt();
                    m = new Message(s1, t1);
                    tc[1].getSocket().send(m);
                }
            }
            else if(k==10)
            {
                System.out.println("Message Retrieved by Socket B: "+tc[0].getSocket().retrieve().text);
            }
        }

    }

}
