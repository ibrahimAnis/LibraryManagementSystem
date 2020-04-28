package com.thinking.machines.library.bl;
import java.util.*;
class IndexTree
{
public class IndexTreeNode
{
private IndexTreeNode left,right;
private AuthorInterface data;
private int index;
private IndexTreeNode(AuthorInterface data,int index)
{
this.left=null;
this.right=null;
this.data=data;
this.index=index;
}
}
private IndexTreeNode start;
private int size;
public boolean add(AuthorInterface data)
{
Stack<IndexTreeNode> stack=new Stack<IndexTreeNode>();
IndexTreeNode node=null;
if(this.start==null)
{
node=new IndexTreeNode(data,1);
this.start=node;
this.size++;
return true;
}
int weight;
IndexTreeNode t=this.start;
while(true)
{
weight=data.compareTo(t.data);
if(weight==0)
{
return false;
}
stack.push(t);
if(weight<0)
{
if(t.left==null)
{
node=new IndexTreeNode(data,1);
t.left=node;
break;
}
t=t.left;
}
else
{
if(t.right==null)
{
node=new IndexTreeNode(data,1);
t.right=node;
break;
}
t=t.right;
}
}
this.size++;
IndexTreeNode root,leftChild,rightChild,grandChild;
IndexTreeNode parentOfRoot=null;
int leftChildHeight,rightChildHeight,heightDiff,prevIndex=0;
while(stack.isEmpty()==false)
{
root=stack.pop();
if(stack.isEmpty()==false)
{
parentOfRoot=stack.peek();
}
leftChild=root.left;
rightChild=root.right;
leftChildHeight=this.getHeight(leftChild);
rightChildHeight=this.getHeight(rightChild);
heightDiff=leftChildHeight-rightChildHeight;
if(heightDiff>=-1 && heightDiff<=1)
{
prevIndex=root.index;
root.index++;
continue;
}
if(heightDiff>1)
{
// left side is heavy
if(this.getHeight(leftChild.right)>this.getHeight(leftChild.left))
{
// left is right heavy
grandChild=leftChild.right;
leftChild.right=grandChild.left;
grandChild.left=leftChild;
root.left=grandChild;
grandChild=leftChild;
leftChild=root.left;
grandChild.index--;
prevIndex=leftChild.index;
}
root.left=leftChild.right;
leftChild.right=root;
if(root==this.start) this.start=leftChild;
else
{ 
if(parentOfRoot.left==root) parentOfRoot.left=leftChild;
else parentOfRoot.right=leftChild;
}
leftChild.index=root.index;
root.index=prevIndex;
leftChild.index++;
}
else
{
if(this.getHeight(rightChild.left)>this.getHeight(rightChild.right))
{
// right is left heavy
grandChild=rightChild.left;
rightChild.left=grandChild.right;
grandChild.right=rightChild;
root.right=grandChild;
grandChild=rightChild;
rightChild=root.right;
grandChild.index--;
prevIndex=rightChild.index;
}
root.right=rightChild.left;
rightChild.left=root;
if(root==this.start) this.start=rightChild;
else 
{
if(parentOfRoot.right==root) parentOfRoot.right=rightChild;
else parentOfRoot.left=rightChild;
}
rightChild.index=root.index;
root.index=prevIndex;
rightChild.index++;
}
}
return true;
}
public int getHeight(IndexTreeNode node)
{
int leftHeight,rightHeight;
if(node==null) return 0;
leftHeight=getHeight(node.left);
rightHeight=getHeight(node.right);
if(leftHeight>rightHeight) return leftHeight+1;
else return rightHeight+1;
}
public void traverseInOrder()
{
Stack<IndexTreeNode> stack=new Stack<IndexTreeNode>();
IndexTreeNode t=this.start;
while(true)
{
if(t!=null)
{
stack.push(t);
t=t.left;
}
else
{
if(stack.isEmpty()==true) break;
t=stack.pop();
System.out.println(t.data+" index:"+t.index);
t=t.right;
}
}
}
public void levelOrderTraversal()
{
Queue<IndexTreeNode> queue=new LinkedList<IndexTreeNode>();
IndexTreeNode t=this.start;
while(t!=null)
{
System.out.println(t.data);
if(t.left!=null) queue.add(t.left);
if(t.right!=null) queue.add(t.right);
if(queue.isEmpty()) break;
t=queue.remove();
}
}
public AuthorInterface get(int i)
{
int index=i+1;
IndexTreeNode t1=this.start;
IndexTreeNode t2=this.start.left;
while(t2!=null)
{
if(index==t2.index+1) return t1.data;
if(index<t2.index+1) 
{
t1=t2;
t2=t2.left;
continue;
}
else
{
index=index-(t2.index+1);
t2=t1.right;
}
}
return t1.data;
}
public int size()
{
return this.size;
}
public AuthorInterface remove(String name)
{
IndexTreeNode t=this.start;
Stack<IndexTreeNode> stack=new Stack<IndexTreeNode>();
AuthorInterface data;
int weight;
while(t!=null)
{
weight=name.compareTo(t.data.getName());
if(weight==0) break;
stack.push(t);
if(weight>0) t=t.right;
else t=t.left;
}
if(t==null) return null;
data=t.data;
IndexTreeNode parent=null;
if(stack.isEmpty()==true)
{
parent=this.start;
}
else
{
parent=stack.peek();
}

if(t.left==null && t.right==null) 
{
if(parent.left==t) parent.left=null;
if(parent.right==t) parent.right=null;
}
else if(t.right==null)
{
if(parent.left==t) parent.left=t.left;
if(parent.right==t) parent.right=t.left;
if(parent==this.start) this.start=parent.right;
}
else if(t.left==null)
{
if(parent.left==t) parent.left=t.left;
if(parent.right==t) parent.right=t.left;
if(parent==this.start) this.start=parent.left;
}
else
{
IndexTreeNode f;
IndexTreeNode parent2;
Queue<IndexTreeNode> queue=new LinkedList<IndexTreeNode>();
parent2=t;
f=t.right;
while(f.left!=null)
{
queue.add(f);
parent2=f;
f=f.left;
}
stack.push(f);
while(queue.size()>0)
{
stack.push(queue.remove()); 
}
if(parent2.left==f)
{
parent2.left=f.right;

}
else
{
parent2.right=f.right;
}
f.left=t.left;
f.right=t.right;
f.index=t.index;
if(parent.left==t) parent.left=f;
if(parent.right==t) parent.right=f;
if(parent==this.start) this.start=f;
this.size--;
IndexTreeNode root,parentOfRoot=null,leftChild,rightChild,grandChild;
int leftChildHeight,rightChildHeight,heightDiff,tmp=0;
while(stack.isEmpty()==false)
{
root=stack.pop();
if(stack.isEmpty()!=true)
{
parentOfRoot=stack.peek();
}
leftChild=root.left;
rightChild=root.right;
leftChildHeight=this.getHeight(leftChild);
rightChildHeight=this.getHeight(rightChild);
heightDiff=leftChildHeight-rightChildHeight;
if(heightDiff>=-1 && heightDiff<=1)
{
root.index--;
continue;
}
if(heightDiff>1)
{
// left side is heavy
if(getHeight(leftChild.right)>getHeight(leftChild.left))
{
// left is right heavy
grandChild=leftChild.right;
leftChild.right=grandChild.left;
grandChild.left=leftChild;
root.left=grandChild;
grandChild=leftChild;
leftChild=root.left;
grandChild.index--;
}
root.left=leftChild.right;
leftChild.right=root;
if(root==this.start) this.start=leftChild;
if(parentOfRoot.left==root) parentOfRoot.left=leftChild;
if(parentOfRoot.right==root) parentOfRoot.right=leftChild;
tmp=leftChild.index;
leftChild.index=root.index;
root.index=tmp;
leftChild.index--;
}
else
{
// right is heavy
if(getHeight(rightChild.left)>getHeight(rightChild.right))
{
// right is left heavy
grandChild=rightChild.left;
rightChild.left=grandChild.right;
grandChild.right=rightChild;
root.right=grandChild;
grandChild=rightChild;
rightChild=root.right;
grandChild.index--;
}
root.right=rightChild.left;
rightChild.left=root;
if(root==this.start) this.start=rightChild;
if(parentOfRoot.right==root) parentOfRoot.right=rightChild;
if(parentOfRoot.left==root) parentOfRoot.left=rightChild;
tmp=rightChild.index;
rightChild.index=root.index;
root.index=tmp;
rightChild.index--;
}
}
}
while(stack.isEmpty()==false) stack.pop().index--;
return data;
}
}


