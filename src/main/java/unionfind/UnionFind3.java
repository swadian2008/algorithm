package unionfind;

/**
 * @author swadian
 * @date 2020/05/11 21:47
 * @Version 1.0
 */
public class UnionFind3 implements UF {
    private int[] parent;
    private int[] sz; // sz[i]表示以i为根的集合元素个数
    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];
        // 初始化的时候，所有的元素都是一个独立的树
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            // 每一集合一开始都只有一个元素
            sz[i] = 1;
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
        // 如果pRoot节点的数量小于qRoot节点的数量，pRoot->qRoot
        if(sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            // 树的节点数量维护
            sz[qRoot] += sz[pRoot];
        }else{// qRoot->pRoot
            parent[qRoot] = pRoot;
            // 树的节点数量维护
            sz[pRoot] += sz[qRoot];
        }
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
