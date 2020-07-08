package consistent_hash;

public class ServerNode {
    private String IP;
    private String name;

    public ServerNode (String name, String IP) {
        this.name = name;
        this.IP = IP;
    }

    public String getIP() {
        return IP;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "真实服务器节点 ["+name+" ("+IP+") ]";
    }
}
