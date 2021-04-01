package com.jianglong.binaryTree;

import java.util.*;

/*二叉树高频算法*/
public class binaryTreeCommonlyAL {
    //树节点定义
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //求二叉树最大深度
    int maxDepth(TreeNode node){
        if(node==null) return 0;
        int left=maxDepth(node.left);
        int right=maxDepth(node.right);
        return Math.max(left,right)+1;
    }
    //求二叉树最小深度
    int minDepth(TreeNode node){
        if(node==null) return 0;
        if(node.left==null&&node.right!=null) return minDepth(node.right)+1;
        if(node.left!=null&&node.right==null) return minDepth(node.left)+1;
        return Math.min(minDepth(node.left)+1,minDepth(node.right)+1);//如果左右子树均存在，则递归左右子树并加上当前节点
    }

    //求二叉树中节点的个数
    int numOfTreeNode(TreeNode node){
        if(node==null) return 0;
        int left=numOfTreeNode(node.left);
        int right=numOfTreeNode(node.right);
        return left+right+1;
    }
    //求二叉树中叶子节点的个数
    int numsOfNoChildNode(TreeNode node){
        if(node==null) return 0;
        if(node.left==null&&node.right==null) return 1;
        return numsOfNoChildNode(node.left)+numsOfNoChildNode(node.right);
    }
    //求二叉树中第k层节点的个数
    int numsOfKLevelTreeNode(TreeNode node,int k){
        if(node==null||k<1) return 0;
        if(k==1) return 1;
        int numsLeft=numsOfKLevelTreeNode(node.left,k-1);
        int numsRight=numsOfKLevelTreeNode(node.right,k-1);
        return numsLeft+numsRight;
    }

    //判断两个二叉树是否完全相同
    boolean isSameTreeNode(TreeNode node1,TreeNode node2){
        if(node1==null&&node2==null) return true;
        else if(node1==null||node2==null) return false;
        else if(node1.val!=node2.val) return false;
        boolean left=isSameTreeNode(node1.left,node2.left);
        boolean right=isSameTreeNode(node1.right,node2.right);
        return left&&right;
    }
    //两个二叉树是否互为镜像
    boolean isMirror(TreeNode node1,TreeNode node2){
        if(node1==null&&node2==null) return true;
        if(node1==null||node2==null) return false;
        if(node1.val!=node2.val) return false;
        return isMirror(node1.left,node2.right)&&isMirror(node1.right,node2.left);
    }
    //翻转二叉树or镜像二叉树
    TreeNode mirrorTreeNode(TreeNode node){
        if(node==null) return null;
        TreeNode left=mirrorTreeNode(node.left);
        TreeNode right=mirrorTreeNode(node.right);
        node.left=right;
        node.right=left;
        return node;
    }

    //二叉树的前序遍历
    ArrayList<Integer> preOrder(TreeNode root){
        Stack<TreeNode> stack=new Stack<TreeNode>();
        ArrayList<Integer> list=new ArrayList<Integer>();
        if(root==null) return list;
        stack.push(root);
        while (!stack.empty()){
            TreeNode node=stack.pop();
            list.add(node.val);
            if(node.right!=null) stack.push(node.right);
            if(node.left!=null) stack.push(node.left);
        }
        return list;
    }
    //二叉树的中序遍历
    ArrayList<Integer> inOrder(TreeNode root){
        ArrayList<Integer> list=new ArrayList<Integer>();
        if(root==null) return list;
        queryNode(list,root);
        return list;
    }
    void queryNode(ArrayList<Integer> list,TreeNode node){
        if(node.left!=null){
            queryNode(list,node.left);
        }
        list.add(node.val);
        if(node.right!=null){
            queryNode(list,node.right);
        }
    }
    //二叉树的后序遍历
    ArrayList<Integer> postOrder(TreeNode root){
        ArrayList<Integer> list=new ArrayList<Integer>();
        if(root==null) return list;
        queryNode2(list,root);
        return list;
    }
    void queryNode2(ArrayList<Integer> list,TreeNode node){
        if(node.left!=null){
            queryNode(list,node.left);
        }
        if(node.right!=null){
            queryNode(list,node.right);
        }
        list.add(node.val);
    }

    //在二叉树中插入节点
    TreeNode insertNode(TreeNode root,TreeNode node){
        if(root==node) return node;
        TreeNode tmp=root;
        TreeNode last=null;
        while (tmp!=null){
            last=tmp;
            if(tmp.val>node.val){
                tmp=tmp.left;
            }else {
                tmp=tmp.right;
            }
        }
        if(last!=null){
            if(last.val>node.val){
                last.left=node;
            }else {
                last.right=node;
            }
        }
        return root;
    }

    //给定两个值 k1 和 k2（k1 < k2）和一个二叉查找树的根节点。找到树中所有值在 k1 到 k2 范围内的节点。即打印所有x (k1 <= x <= k2) 其中 x 是二叉查找树的中的节点值。返回所有升序的节点值。
    ArrayList<Integer> searchRange(TreeNode root,int k1,int k2){
        ArrayList<Integer> result=new ArrayList<Integer>();
        searchHelper(result,root,k1,k2);
        return result;
    }
    void searchHelper(ArrayList<Integer> result,TreeNode root,int k1,int k2){
        if(root==null) return;
        if(root.val>k1) searchHelper(result,root.left,k1,k2);
        if(root.val>=k1&&root.val<=k2) result.add(root.val);
        if(root.val<k2) searchHelper(result, root.right, k1, k2);
    }

    //二叉树的层次遍历
    ArrayList<ArrayList<Integer>> levelOrder(TreeNode root){
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        if(root==null) return result;
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            ArrayList<Integer> level=new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode node=queue.poll();
                level.add(node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    //判断二叉树是否是合法的二叉查找树（BST）
    /*
    * BST树的定义为：节点的左子树中的值要严格小于该节点的值，节点的右子树中的值要严格大于该节点的值。
    *               左右子树也必须是二叉查找树，一个节点的树也是二叉查找树。
    * */
    boolean flag=true;
    boolean isValidBST(TreeNode root){
        TreeNode pre=null;//保存前驱节点
        dfs(root,pre);
        return flag;
    }
    //采用中序遍历，如果前节点不小于当前节点的值，则不符合BST条件
    private void dfs(TreeNode root,TreeNode pre){
        if(root!=null){
            dfs(root.left, pre);
            if(pre!=null&&root.val<=pre.val) flag=false;
            pre=root;//更新前驱节点为当前根节点
            dfs(root.right, pre);
        }
    }

    public static void main(String[] args) {
        binaryTreeCommonlyAL ba=new binaryTreeCommonlyAL();
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);
        a.left = b;
        a.right = c;
        b.right = d;
        c.left = e;
        c.right = f;
        f.left = g;
        ArrayList<Integer> list=new ArrayList<Integer>();
        list=ba.inOrder(a);
        for(int aa:list){
            System.out.println(aa);
        }
    }

}
