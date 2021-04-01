package com.jianglong.binaryTree;
import com.jianglong.binaryTree.binaryTreeCommonlyAL.TreeNode;

//二叉查找树增删查改算法
public class binarySearchTreeAL {
    //二叉查找树也可以叫二分查找树，不仅可以查找数据，还可以高效地插入删除数据。其特点是每个节点的key值大于左子节点，小于右子节点。
    //二叉查找树最小节点位于最顶端节点最左边子树行的末尾，最大节点位于最顶端节点最右边子树行的末尾
    int size=0;

    //添加二叉查找树节点
    TreeNode add(TreeNode node,int e){
        if(node==null){
            size++;
            return new TreeNode(e);
        }
        //上面的条件不满足，说明还得继续往下找左右子树为null可以挂载上的节点
        if(e-node.val<0){
            //如果小于，则继续往他的左子树添加节点
            node.left=add(node.left,e);//新节点赋值给node.left
        }else if(e-node.val>0){
            //如果大于，则往右子树添加
            node.right=add(node.right,e);
        }
        return node;
    }

    //删除二叉查找树的节点
    TreeNode deleteNode(TreeNode curNode,int val){
        if(curNode==null) return null;
        if(val<curNode.val){
            curNode.left=deleteNode(curNode.left,val);
        }else if(val>curNode.val){
            curNode.right=deleteNode(curNode.right,val);
        }else {
            //待删除节点左子树为空
            if(curNode.left==null){
                TreeNode rightNode= curNode.right;
                curNode.right=null;
                size--;
                return rightNode;
            }
            //待删除节点右子树为空
            if(curNode.right==null){
                TreeNode leftNode=curNode.left;
                curNode.left=null;
                size--;
                return leftNode;
            }
            //待删除节点左右子树均不为空的情况
            //找到比待删除节点大的最小节点，即删除节点右子树的最小节点
            //用这个节点顶替待删除节点的位置
            TreeNode successor=findMin(curNode.right);
            curNode.val=successor.val;
            curNode.right=deleteNode(curNode.right,curNode.val);
        }
        return curNode;
    }
    //获取二叉查找树最小节点
    TreeNode findMin(TreeNode node){
        while (node.left!=null){
            node=node.left;
        }
        return node;
    }

    //搜索二叉查找树节点
    boolean isContainsNode(TreeNode node,int val){
        boolean flag=false;
        if(node==null) return false;
        if(val<node.val){
            flag=isContainsNode(node.left,val);
        }else if(val>node.val){
            flag=isContainsNode(node.right,val);
        }else {
            flag=true;
        }
        return flag;
    }

}
