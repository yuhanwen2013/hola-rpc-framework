package cn.yubutong.spidemo;

public interface ISpi<T> {

    boolean verify(T condition);
    /**
     * 排序，数字越小，优先级越高
     * @return
     */
    default int order() {
        return 10;
    }
}