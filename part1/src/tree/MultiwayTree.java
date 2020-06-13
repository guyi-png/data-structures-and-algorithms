package tree;

/**
 * 2-3树：2-3树是最简单的B-树(B-tree, B树)（或-树）结构，
 * 其每个非叶节点都有两个或三个子节点，
 * 而且所有叶都在统一层上
 *
 * 2-3-4树： 和2-3树差不多
 *
 * B (可指balance 平衡，Bayer 人名，Boeing 波音)
 *
 * B树
 * 在B-树中查找给定关键字的方法是，首先把根结点取来，
 * 在根结点所包含的关键字K1,…,Kn查找给定的关键字（可用顺序查找或二分查找法），
 * 若找到等于给定值的关键字，则查找成功；否则，
 * 一定可以确定要查找的关键字在Ki与Ki+1之间，Pi为指向子树根节点的指针，
 * 此时取指针Pi所指的结点继续查找，直至找到，或指针Pi为空时查找失败。
 *
 * B+树
 * 比B树具有更广泛的应用，m阶B+树有如下特征:
 * (1)每个结点的关键字个数与孩子个数相等，
 * 所有非最下层的内层结点的关键字是对应子树上的最大关键字，
 * 最下层内部结点包含了全部关键字。
 * (2)除根结点以外，每个内部结点有到m个孩子。
 * (3)所有叶结点在树结构的同一层，
 * 并且不含任何信息(可看成是外部结点或查找失败的结点)，
 * 因此，树结构总是树高平衡的
 *
 * B*树
 * B*树是B+树的变体，在B+树的非根和非叶子结点再增加指向兄弟的指针；
 * B*树定义了非叶子结点关键字个数至少为(2/3)*M，
 * 即块的最低使用率为2/3（代替B+树的1/2）
 */
public class MultiwayTree {

}