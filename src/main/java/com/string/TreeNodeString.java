package com.string;

import java.util.ArrayDeque;
import java.util.ArrayList;

class TreeNodeString {
    public int data;
    public String text;
    public ArrayList<TreeNodeString> Children = new ArrayList<>();
    public TreeNodeString(int data){
        this.data = data;
    }

    public TreeNodeString(String text){
        this.text = text;
    }

    public static void display_level_order(TreeNodeString root) {
        if(root == null)
            return;

        ArrayDeque<TreeNodeString> queue = new ArrayDeque<>();
        queue.addLast(root);
        TreeNodeString tempNode = new TreeNodeString(100);
        queue.addLast(tempNode);

        while(!queue.isEmpty()){
            TreeNodeString temp = queue.removeFirst();

            if(temp == tempNode) {
                System.out.println();
                if(!queue.isEmpty()){
                    queue.addLast(tempNode);
                    continue;
                }
                else {
                    return;
                }

            }

            System.out.print(temp.data + ", ");

            for(int i = 0 ; i < temp.Children.size() ; i++) {
                queue.addLast(temp.Children.get(i));
            }
        }
        System.out.println();
    }
}
