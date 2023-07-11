package trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Trie {
	private TrieNode root;
	public Trie() {
		this.root = new TrieNode();
	}
	
	
	/*
	public boolean add(String word) {
		Initialize p with trie root
		Initialize stringExist with true boolean value
		for(each character in string word) {
			Assign child node with trie node for character index
			if(child node belonging to current char is null)
        	{
            	child node = new Node();
            	stringExist = false
        	}
        	current_node = child_node;
		}
		Update the current_node field isLeaf as true
		return stringExist
	}
	 */
	
	public boolean add(String word) {
		TrieNode p = root;
		boolean stringExist = true;
		for(int i=0; i<word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if(p.arr[index] == null) {
				TrieNode temp = new TrieNode();
				p.arr[index] = temp;
				p = temp;
				stringExist = false;
			} else {
				p =  p.arr[index];
			}
		}
		p.isLeaf = true;
		return stringExist;
	}
	
	/*
	 public boolean contains(String word) {
	 	Initialize p with trie root
		{
    		for(every char in String s)
    		{
    			Assign child node with trie node for character index
        		if(child node is null)    
        		{
            		return false;
        		}
    		}
    		return true;
	 }
	 */
	public boolean contains(String word) {
		TrieNode p = root;
		int i=0;
		for(; i<word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if(p.arr[index] == null) {
				break;
			}
			p = p.arr[index];
		}
		if ((i==word.length()) && p.isLeaf == true) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 public List<String> getAllWords() {
	 	Initialize p with trie root
	 	allwords = new ArrayList<String>()
	 	getAllWordsUtil(p, allwords, "")
   		return allwords;
	 }
	 */
	public List<String> getAllWords() {
		TrieNode p = root;
		List<String> words = new ArrayList<String>();
		getAllWordsUtil(p, words, "");
		return words;
	}
	
	/*
	 private void getAllWordsUtil(TrieNode root, List<String> words, String word) {
	 	if root is null
	 		return
	 	if root is leaf node
	 		add that word in words list
	 	for(every node in trie node array) {
	 		if node is not null
	 			getAllWordsUtil(node, words, words+node character)
	 	}
	 	return
	 }
	 */
	
	private void getAllWordsUtil(TrieNode root, List<String> words, String word) {
		if(root == null)
			return;
		if(root.isLeaf) {
			words.add(word);
		}
		for(int i=0; i<root.arr.length; i++) {
			if(root.arr[i] != null)
				getAllWordsUtil(root.arr[i], words, word+(char)('a'+i));
		}
		return;
	}
	
	/*
	 public String outputBreadthFirstSearch() {
	 	Initialize word as empty string
	 	if trie root is null
	 		return ""
		Initialize queue to contain trie root
		while Q not empty do {
			p = Q.dequeue()
			for(every trie node in p array) {
				Q.enqueue(node)
				append trie character in word
			}
		}
		return word
	 }
	 */
	public String outputBreadthFirstSearch() {
		String word = "";
		if(root == null)
			return word;
		
		Queue<TrieNode> q = new LinkedList<TrieNode>();
		q.add(root);
		while(!q.isEmpty()) {
			TrieNode temp = q.remove();
			for(int i=0; i<26; i++) {
				if(temp.arr[i]!= null) {
					q.add(temp.arr[i]);
					word = word + (char)('a' + i);
				}
			}
		}
		return word;
	}
	
	/*
	 public String outputDepthFirstSearch() {
	 	Initialize p with trie root
	 	Initialize list of characters as res
	 	outputDepthFirstSearchUtil(res)
	 	convert res to string
	 	return the resulted string
	 }
	 */
	public String outputDepthFirstSearch() {
		List<Character> res = new ArrayList<Character>();
		outputDepthFirstSearchUtil(root, res);
		StringBuilder sb = new StringBuilder();
		for(Character c: res)
			sb.append(c);
		return sb.toString();
	}
	
	/*
	 private void outputDepthFirstSearchUtil(TrieNode root, List<Character> res) {
	 	if root is null
	 		return
	 	for(every node in trie node array) {
	 		if node is not null
	 			add the trie character in res
	 			outputDepthFirstSearchUtil(node, res)
	 	}
	 	return
	 }
	 */
	private void outputDepthFirstSearchUtil(TrieNode root, List<Character> res) {
		if(root == null)
			return;
		for(int i=0; i<root.arr.length; i++) {
			if(root.arr[i] != null)
				res.add((char) (i+'a'));
				outputDepthFirstSearchUtil(root.arr[i], res);
		}
	}
	
	/*
	 public Trie getSubTrie(String prefix) {
	 	Initialize temp with trie root
	 	for(every char in prefix) {
	 		if char is not present
	 			return null
	 		update temp to that node
	 	}
	 	Create a new trie root node
	 	copyTrie(temp, newTrie)
	 	return newTrie
	 }
	 */
	public Trie getSubTrie(String prefix) {
		TrieNode temp = root;
		for(int i=0; i<prefix.length(); i++) {
			int index = prefix.charAt(i) -  'a';
			if(temp.arr[index] == null) {
				return null;
			}
			temp = temp.arr[index];
		}
		Trie newTrie = new Trie();
		copyTrie(temp, newTrie.root);
		return newTrie;
	}
	
	/*
	 private void copyTrie(TrieNode node, TrieNode root) {
	 	if node is null
	 		return
	 	for(every node in trie node array) {
	 		if node is not null
	 			create a new trie node and update isLeaf if leaf node
	 			copyTrie(node, newNode)
	 	}
	 }
	 */
	private void copyTrie(TrieNode node, TrieNode root) {
		if (node == null)
			return;
		for(int i=0; i<node.arr.length; i++) {	
			if(node.arr[i] != null) {
				TrieNode newNode = new TrieNode();
				newNode.isLeaf = node.arr[i].isLeaf;
				root.arr[i] = newNode;
				copyTrie(node.arr[i], root.arr[i]);
			}
		}
	}
}
