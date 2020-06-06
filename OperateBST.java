import java.util.*;
import java.io.*;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
// Code by Mehrob Farhangmehr
// COP3505 Spring 2020

class Node
{	
	int data;
	Node left;
	Node right;
	
	Node(int data)
	{
		this.data = data;
		this.left = null;
		this.right = null;
	}

	// Makes it easier to read nodes
	public String toString()
	{
		return Integer.toString(data);
	} 
}

class BST
{
	public Node head = null;
	public static Boolean quitOption = true;
	public int statementSize = 25;
	int leftChildren;
	int rightChildren;
	int leftDepth;
	int rightDepth;

	public void insert(int data)
	{
		head = insertRecurisive(head,data);
	}
	
	public Node insertRecurisive(Node insertNode, int data)
	{
		if (insertNode == null)
		{
			//System.out.println("The head is currently " + head);
			insertNode = new Node(data);
			//System.out.println(insertNode + " has been inserted");
			return insertNode;
		}
		
		//System.out.println("The head is not null");
		if (data < insertNode.data)
			insertNode.left = insertRecurisive(insertNode.left, data);
		else if (data >= insertNode.data)
			insertNode.right = insertRecurisive(insertNode.right, data);
		
		//System.out.println("We have inserted " + insertNode);
		return insertNode;
	}		
	
	public Node print(Node current)
	{
		Node Traversal = current;
		if (head == null)
		{
			//System.out.println("There is no BST atm");
			return null;
		}
		//System.out.println("We are currently on " + current);
		if (Traversal.left != null)
		{
			//System.out.println("We went to the left");
			print(Traversal.left);
		}
		System.out.print(" " + current);
		if (Traversal.right != null)
		{
			//System.out.println("We went to the right");
			print(Traversal.right);
		}
		return current;
	}
	
	public void searchChecker(int key)
	{
		Node check = search(key,head);
		if (check == null)
		{
			System.out.println(key + ": NOT found");
			return;
		}
		else if (check.data == key)
		{
			System.out.println(key + ": found");
			return;
		}
		return;
	}
	
	public Node search(int key,Node current)
	{
		if (current == null || current.data == key)
		{
			//System.out.println("There is no BST atm");
			return current;
		}
		
		if (current.data > key)
		{
			//System.out.println("We are going left");
			return search(key,current.left);
		}
		
		return search(key,current.right);	
	}
	
	public void delete(int key)
	{
		//System.out.println("Deleting " + key + "...");
		head = deleteNode(key,head);
	}
	
	public Node deleteNode(int key,Node head)
	{
		if (head == null)
		{
			//System.out.println("integer " + key + " NOT found");
			return head;	
		}
		
		if (key < head.data)
			head.left = deleteNode(key, head.left);
		else if (key > head.data)
			head.right = deleteNode(key, head.right);
		
		//we can only get here if the key is the same as the data
		else
		{
			if (head.left == null)
				return head.right;
			else if (head.right == null)
				return head.left;
		
		head.data = minValue(head.right); 
		
		head.right = deleteNode(head.data, head.right); 	
		}
		return head;
	}
	
	public int minValue(Node head) 
    { 
        int minNodeValue = head.data; 
		//keep going left
        while (head.left != null) 
        { 
            minNodeValue = head.left.data; 
			//System.out.println(minNodeValue);
            head = head.left; 
        } 
        return minNodeValue; 
    }
	
	public void countChildren()
	{
		leftChildren = letsCountThem(head.left);
		rightChildren = letsCountThem(head.right);
		return;
	}
	
	public void getDepth()
	{
		leftDepth = letsGetDepth(head.left);
		rightDepth = letsGetDepth(head.right);
		return;
	}
	
	public int letsCountThem(Node branch)
	{
		if (branch == null)
		{
			//System.out.println("There is no BST atm");
			return 0;
		}
		int count = 1;
		return count = count + letsCountThem(branch.left) + letsCountThem(branch.right);
	}
	
	public static void complexityIndicator()
	{
		System.out.println("me217993;3.1;6");
	}
	
	public int letsGetDepth(Node branch)
	{
		if (branch == null)
		{
			return 0;
		}
		int leftBranch = letsGetDepth(branch.left);
		int rightBranch = letsGetDepth(branch.right);
		
		if (leftBranch > rightBranch)
			return (leftBranch + 1);
		else
			return (rightBranch + 1);
	}
	
	public void spacePrinter(String statement,int value)
	{
	System.out.print(statement);
	for (int i = 0; i < (statementSize-statement.length()-String.valueOf(value).length()); i++)
		System.out.print(" ");	
	System.out.println(value);
	}
}

public class OperateBST
{
	public static void main(String [] args) throws Exception
	{
		BST mytree = new BST();
		Scanner file = new Scanner(new File(args[0]));
		//print out the text file
		System.out.println(args[0] + " contains:");
		while (file.hasNext())
		{
		String reader = file.nextLine();
		System.out.println(reader);
		}
		//reset the "pointer" for file reading
		file = new Scanner(new File(args[0]));
		while (mytree.quitOption)
		{
			String reader = file.next();
			char choice = reader.charAt(0);
			
			if (choice == 'i')
			{
				//System.out.println("\nWe are now doing insert...");
				int inserting = Integer.parseInt(file.next());
				mytree.insert(inserting);
			}
			else if (choice == 's')
			{
				//System.out.println("\nWe are searching");
				int searching = Integer.parseInt(file.next());
				mytree.searchChecker(searching);
			}
			else if (choice == 'p')
			{
				//System.out.println("\nWe are printing");
				mytree.print(mytree.head);
				System.out.print("\n");
			}
			else if (choice == 'd')
			{
				//System.out.println("\nWe are deleting");
				int deleting = Integer.parseInt(file.next());
				mytree.delete(deleting);
			}
			else if (choice == 'q')
			{
				//System.out.println("\nWe are quitting");
				mytree.quitOption = false;
			}
		}
		mytree.countChildren();
		mytree.getDepth();
		mytree.spacePrinter("left children:",mytree.leftChildren);
		mytree.spacePrinter("left depth:",mytree.leftDepth);
		mytree.spacePrinter("right children:",mytree.rightChildren);
		mytree.spacePrinter("right depth:",mytree.rightDepth);
		
	}
}