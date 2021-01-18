package com.linkedList;

class Reverse{
    /* Function to reverse the linked list */
    public static LinkedListNode reverse(LinkedListNode head)
    {
        LinkedListNode prev = null;
        LinkedListNode next = null;
        LinkedListNode current = head;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;
    }

    public static LinkedListNode reverseDidntLike(LinkedListNode head) {
        // no need to reverse if head is null
        // or there is only 1 node.
        if (head == null || head.next == null) {
            return head;
        }

        LinkedListNode listToDo = head.next;
        LinkedListNode reversedList = head;

        reversedList.next = null;

        while (listToDo != null) {
            LinkedListNode temp = listToDo;
            listToDo = listToDo.next;

            temp.next = reversedList;
            reversedList = temp;
        }

        return reversedList;
    }
    public static void main(String[] args) {
        LinkedListNode listHead = null;
        int [] arr = {7,14,21,28};
        listHead = LinkedList.createLinkedList(arr);

        System.out.print("Original: ");
        LinkedList.display(listHead);

        listHead = reverse(listHead);
        System.out.print("After Reverse: ");
        LinkedList.display(listHead);
    }
}
