package unionfind;

/**
 * @author swadian
 * @date 2020/05/11 21:47
 * @Version 1.0
 */
public class UnionFind2 implements UF {
    private int[] parent;
    public UnionFind2(int size){
        parent = new int[size];
        // 初始化的时候，所有的元素都是一个独立的树
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        // 查找根节点所属集合
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot){
            return;
        }
        // 把根节点的指针指向q进行合并
        parent[pRoot] = qRoot;
    }
    // 查找元素p所对应的集合编号，时间复杂度为O（h）
    private int find(int p){
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        // 判断是不是根节点（根节点自己等于自己）
        while(p != parent[p]){
            p = parent[p];
        }
        return p;
    }
}
