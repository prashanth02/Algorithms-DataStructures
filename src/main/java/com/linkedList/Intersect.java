package com.linkedList;

import java.util.HashSet;
import java.util.Set;

class Intersect {

    /*
        This will result in O(m + n).
        Collect first list to a set & then in next list loop, verify if data exists.
     */
    public static LinkedListNode intersect(LinkedListNode head1, LinkedListNode head2) {
        Set<Integer> set1 = new HashSet<>();

        while (head1.next != null) {
            set1.add(head1.data);
            head1 = head1.next;
        }
        set1.add(head1.data);

        while (head2.next != null) {
            if (set1.contains(head2.data)) {
                return head2;
            }
            head2 = head2.next;
        }

        return set1.contains(head2.data) ? head2 : null;
    }

    /*
        In O(n) there is a bug.
     */
    public static LinkedListNode intersectDoesntWork(LinkedListNode head1, LinkedListNode head2) {
        LinkedListNode list1node = null;
        int list1length = get_length(head1);
        LinkedListNode list2node = null;
        int list2length = get_length(head2);

        int length_difference = 0;
        if (list1length >= list2length) {
            length_difference = list1length - list2length;
            list1node = head1;
            list2node = head2;
        } else {
            length_difference = list2length - list1length;
            list1node = head2;
            list2node = head1;
        }

        while (length_difference > 0) {
            list1node = list1node.next;
            length_difference--;
        }

        while (list1node != null) {
            if (list1node == list2node) {
                return list1node;
            }

            list1node = list1node.next;
            list2node = list2node.next;
        }
        return null;
    }

    private static int get_length(
            LinkedListNode head) {
        int list_length = 0;
        while (head != null) {
            head = head.next;
            list_length++;
        }
        return list_length;
    }

    public static void main(String[] args) {
        int[] a1 = {10, 5, 3, 4, 13};
        int[] a2 = {11, 23, 82, 13};
        LinkedListNode list_head_1 = LinkedList.createLinkedList(a1);
        LinkedListNode list_head_2 = LinkedList.createLinkedList(a2);

        System.out.print("List 1: ");
        LinkedList.display(list_head_1);
        System.out.print("List 2: ");
        LinkedList.display(list_head_2);

        LinkedListNode intersect_node = intersect(list_head_1, list_head_2);
        System.out.println("Intersect at " + intersect_node.data);
    }
}
