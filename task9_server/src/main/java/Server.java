import org.apache.tuscany.sca.node.Node;
import org.apache.tuscany.sca.node.NodeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Maibenben on 2018/11/3.
 */
public class Server {
    private static Logger log= LoggerFactory.getLogger(Server.class);

    public void start(){
        log.info("服务器端正在连接");
        try {
//            Node node= NodeFactory.getInstance().createNode("tuscany.composite");
//            node.start();

            Node node1=NodeFactory.newInstance().createNode("tuscany.composite");
            node1.start();
            log.info("服务器连接成功");
        } catch (Exception e) {
            log.error("服务器端连接失败"+e.getMessage());

            e.printStackTrace();
        }

    }

    public void run() throws InterruptedException {
        while (true){
            Thread.sleep(Long.MAX_VALUE);
        }

    }


    public static void main(String[] args) {
        Server server=new Server();
        server.start();
        try {
            server.run();
            log.info("服务器端连接成功");
        } catch (InterruptedException e) {
            log.error("线程休眠失败");
            e.printStackTrace();
        }
    }



}
