package com.jianglong.binaryTree;

/*
* 字典树实际上是一个多叉树，因为英文中，如果不区分大小写的话，只有 26 个字母，因此每个节点最多也只有 26 个子节点。
* 因为是从上到下的关系，下一层的信息依赖于上一层的 children 数组，根节点并没有上一层，因此字典树的根节点并不代表任何字符，你可以把其仅仅当作是一个入口。
* 构建的时间复杂度其实是依赖于字符串的长度，也就是 O(L)，这里的 L 表示的是字符串的长度。
* 字典树的两大基本用法：（1）确认一个单词是否在字典中存在；（2）确认字典中是否含有某前缀的单词
* */
//实现一个前缀树，包含insert，search，startWith这三个操作
public class dictionaryTreeAL {
    private final int ALPHABET_SIZE=26;

    private class TrieNode{
        private TrieNode[] children=new TrieNode[ALPHABET_SIZE];
        private boolean isWordOrNot=false;
    }

    private TrieNode root;
    public dictionaryTreeAL(){
        root=new TrieNode();
    }

    //在字典树中插入一个单词
    public void insert(String word){
        TrieNode pointer=root;
        for(char c:word.toCharArray()){
            if(pointer.children[c-'a']==null){
                pointer.children[c-'a']=new TrieNode();
            }
            pointer=pointer.children[c-'a'];
        }
        pointer.isWordOrNot=true;
    }

    //判断字典树中是否存在该单词
    public boolean search(String word){
        TrieNode pointer=this.root;
        for(char c:word.toCharArray()){
            if(pointer.children[c-'a']==null){
                return false;
            }
            pointer=pointer.children[c-'a'];
        }
        return pointer.isWordOrNot;
    }

    //判断是否有单词与该前缀相同
    public boolean startsWith(String prefix){
        TrieNode pointer=this.root;
        for(char c:prefix.toCharArray()){
            if(pointer.children[c-'a']==null) return false;
            pointer=pointer.children[c-'a'];
        }
        return true;
    }

    //search可以搜索文字或正则表达式字符串，字符串只包含字母.或a-z。可以表示任何一个字母
    //即遇到字母.时，需要把当前节点的所有存在的节点都考虑一遍
    public boolean searchUpgrade(String word){
        return helper(0,word.toCharArray(),root);
    }
    public boolean helper(int index,char[] word,TrieNode root){
        if(index==word.length){
            return root.isWordOrNot;
        }
        char curC=word[index];

        if(curC!='.'){
            if(root.children[curC-'a']!=null){
                return helper(index+1,word,root.children[curC-'a']);
            }else {
                return false;
            }
        }
        if(curC=='.'){
            for (int i = 0; i < root.children.length; i++) {
                if(root.children[i]!=null&&helper(index+1,word,root.children[i])){
                    return true;
                }
            }
        }
        return false;
    }

}
