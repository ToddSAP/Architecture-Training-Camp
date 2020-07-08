package consistent_hash;

public class VirtualServerNode {
    private ServerNode realServer;

    public VirtualServerNode (int hashCode, String name, ServerNode realServer) {
        this.realServer = realServer;
        //System.out.println("虚拟节点 ["+name+ " ("+hashCode+") "+" ] 被添加");
    }

    public ServerNode getRealServer() {
        return realServer;
    }
}
