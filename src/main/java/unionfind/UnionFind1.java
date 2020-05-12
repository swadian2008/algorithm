package unionfind;

/**
 * @author swadian
 * @date 2020/05/11 21:09
 * @Version 1.0
 */
public class UnionFind1 implements UF{
    private int[] id;
    public UnionFind1(int size){
        id = new int[size];
        // 初始化的时候，每个元素都属于不同的集合
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        // 两个元素如果本来就是属于相同的集合,就没有合并的并要
        if (pID == qID) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
    }

    // 查找元素p所对应的集合编号
    private int find(int p){
        if(p < 0 || p >= id.length){
            throw new IllegalArgumentException("p is out of bound.");
        }
        return id[p];
    }
}
