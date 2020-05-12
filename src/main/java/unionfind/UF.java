package unionfind;

/**
 * @author swadian
 * @date 2020/05/11 20:44
 * @Version 1.0
 */
public interface UF {
    int getSize();
    // 判断两个元素是否是相连的
    boolean isConnected(int p, int q);
    // 将两个元素合并在一起
    void unionElements(int p, int q);
}
