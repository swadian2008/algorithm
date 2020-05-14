package unionfind;

/**
 * @author swadian
 * @date 2020/05/11 21:47
 * @Version 1.0
 */
public class UnionFind5 implements UF {
    private int[] parent;
    private int[] rank; // rank[i]表示以i为根的集合所表示的树的层数
    public UnionFind5(int size){
        parent = new int[size];
        rank = new int[size];
        // 初始化的时候，所有的元素都是一个独立的树
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            // 每个集合一开始的层级都是1
            rank[i] = 1;
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
        // 将rank低的集合合并到rank高的集合上
        if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if(rank[pRoot] > rank[qRoot]){// qRoot->pRoot
            parent[qRoot] = pRoot;
        }else{// parent[pRoot] == parent[qRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
    // 查找元素p所对应的集合编号，时间复杂度为O（h）
    private int find(int p){
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        // 判断是不是根节点（根节点自己等于自己）
        while(p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
