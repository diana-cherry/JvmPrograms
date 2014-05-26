package dy.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Created by dongyuan on 14-5-20.
 */
public class SimpleTest {
    public static void main(String[] args){
        try {
            String hostport = "127.0.0.1:2181";
            ZooKeeper zooKeeper = new ZooKeeper(hostport, 300000, null);    //创建一个ZooKeeper实例,不设置默认watcher
            String path = "/zk_test";
            zooKeeper.create(path, path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);    //创建一个节点
            Stat stat = new Stat();
            byte[] b = zooKeeper.getData(path, false, stat);    //获取节点的信息及存储的数据
            System.out.println(stat);
            System.out.println(new String(b));
            stat = zooKeeper.exists(path, false);    //查看path所代表的节点是否存在
            zooKeeper.setData(path, "helloworld".getBytes(), stat.getVersion());    //设置节点的数据
            //zooKeeper.delete(path, -1);    //删除节点
            zooKeeper.close();    //关闭实例
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
