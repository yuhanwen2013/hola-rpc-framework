package cn.yubutong.enumeration;

public enum RpcConfigProperties {

    RPC_CONFIG_PATH("rpc.properties"),
    ZK_ADDRESS("rpc.zookeeper.address"),
    NACOS_ADDRESS("rpc.nacos.address");

    private final String propertyValue;


    RpcConfigProperties(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

}
