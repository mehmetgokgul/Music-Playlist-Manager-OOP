package playlistmanager;
import java.util.Scanner;


// Name Surname: Mehmet Gökgül
// Student ID : 22050151040
// Video Link : https://drive.google.com/file/d/1Uj0Waf4Bj6HPEhWoE2I2ZcR0FSHSQN-4/view?usp=sharing

/*
In this project, I developed a playlist manager application utilizing two types of linked lists: Singly Linked List and Doubly Linked List. 
The program enables users to add, remove, search, display, and reverse songs. It also allows users to navigate through the playlist and move songs to specific positions.
To streamline playlist management, I created a Node class for each linked list, ensuring the code is more modular and user-friendly. 
Additionally, I implemented optional methods for both list types, including Search Song and Reverse Playlist functions.
Initially, for the song-moving functionality, I utilized the element-swapping method to move a song to the desired position. 
However, to enhance the design while preserving the list’s integrity, I later optimized the approach by incorporating the removeNode and insertAtPosition methods.
*/


class Song {

    String title;
    String artist;
    int duration;

    public Song(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }
}


class SinglyLinkedList {

    class Node {

        Song song;
        Node next;

        public Node(Song song) {
            this.song = song;
            next = null;
        }
    }

    Node head;
    Node tail;

    public SinglyLinkedList() {
        head = tail = null;
    }

    public void addSong(String title, String artist, int duration) {
        //Checks is the song already in the list
        Node temp = head;
        while (temp != null) {
            if (temp.song.title.equals(title) && temp.song.artist.equals(artist)) {
                System.out.println("The song is already in the list!");
                return;
            }

            temp = temp.next;
        }

        Song newSong = new Song(title, artist, duration);
        Node newNode = new Node(newSong);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void removeSong(String title) {
        if (head == null) {
            System.out.println("The playlist is empty!");
            return; // If the playlist is empty, just return.
        }

        // if the song is at the head of the playlist
        if (head.song.title.equals(title)) {
            head = head.next; // Remove the song by moving the head pointer
            if (head == null) {
                tail = null; // If head becomes null, the playlist is empty, so set tail to null.
            }
            System.out.println(title + " is deleted!");
            return;
        }

        // checks for the song in the rest of the playlist
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.song.title.equals(title)) {
                temp.next = temp.next.next; // Remove the song by skipping it in the list

                if (temp.next == null) {
                    tail = temp; // If the song was the last one, update tail to the current node.
                }

                System.out.println(title + " is deleted!");
                return;
            }
            temp = temp.next;
        }
        System.out.println(title + " was not found!");
    }

    public void displayPlaylist() {
        if (head == null) {
            System.out.println("The playlist is empty!");
            return;
        }

        int index = 1;
        Node temp = head;
        System.out.println("");

        while (temp != null) {
            System.out.println(index + "- " + temp.song.title + ", " + temp.song.artist + ", " + temp.song.duration + " sec.");
            temp = temp.next;
            index++;
        }

    }

    public boolean searchSong(String title, String artist) {

        if (head == null) {
            System.out.println("The playlist is empty!");
            return false;
        }
        Node temp = head;
        while (temp != null) {
            if (temp.song.title.equals(title) && temp.song.artist.equals(artist)) {
                System.out.println("The song is found in the playlist!\n");
                System.out.println("Title: " + temp.song.title + ", Artist: " + temp.song.artist + ", Duration: " + temp.song.duration + " sec.");
                return true;
            }
            temp = temp.next;
        }
        System.out.println("The song was not found!");
        return false;
    }

    public void reversePlaylist() {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("The playlist is empty!");
            return;
        }
        Node current = head; // Start with the first song 
        Node prev = null;
        Node nextNode = null;

        // Traverse playlist and reverse links 
        while (current != null) {
            nextNode = current.next;  // Save the next song
            current.next = prev;      // Reverse the link, current song now points to the previous song
            prev = current;           // Move prev to current song
            current = nextNode;       // Move current to the next song (which was saved earlier)
        }

        head = prev; // After the loop, prev points to the new first song
        System.out.println("The playlist has been reversed!");
    }

}
// -----------------------------------------------------------------------------------------------------------------------------------------------------------


class DoublyLinkedList {

    class Node {

        Song song;
        Node next;
        Node prev;

        public Node(Song song) {
            this.song = song;
            next = null;
            prev = null;
        }
    }

    Node head;
    Node tail;
    Node currentSong;
    int totalSongs = 0;

    public DoublyLinkedList() {
        head = tail = null;
    }

    public void addSong(String title, String artist, int duration) {
        // Check if the song is already in the playlist
        Node temp = head;
        while (temp != null) {
            if (temp.song.title.equals(title) && temp.song.artist.equals(artist)) {
                System.out.println("The song is already in the list!");
                return;
            }

            temp = temp.next;
        }

        Song newSong = new Song(title, artist, duration);
        Node newNode = new Node(newSong);

        // If the playlist is empty, the new song becomes both the head and tail
        if (head == null) {
            head = tail = newNode;

        } else {
            // Otherwise, add the new song to the end
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        totalSongs++;
    }

    public void removeSong(String title) {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        // If the song to be removed is the first song (head)
        if (head.song.title.equals(title)) {
            head = head.next;

            // If the playlist becomes empty after removing the song, set tail to null
            if (head == null) {
                tail = null;
            }

            System.out.println(title + " is deleted!");
            totalSongs--;
            return;
        }

        // Traverse the playlist
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.song.title.equals(title)) {
                temp.next = temp.next.next; // Remove the song from the playlist

                // If the song removed is the last one, update the tail
                if (temp.next == null) {
                    tail.prev = temp.prev;
                    tail = temp;
                }

                System.out.println(title + " is deleted!");
                totalSongs--;
                return;
            }
            temp = temp.next;
        }
        System.out.println(title + " was not found!");
    }

    public void displayPlaylist() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        int index = 1;
        Node temp = head;
        System.out.println("");
        while (temp != null) {
            System.out.println(index + "- " + temp.song.title + ", " + temp.song.artist + ", " + temp.song.duration + " sec.");
            temp = temp.next;
            index++;
        }

    }

    public void searchSong(String title, String artist) {

        if (head == null) {
            System.out.println("The playlist is empty!");
            return;
        }
        Node temp = head;
        while (temp != null) {
            if (temp.song.title.equals(title) && temp.song.artist.equals(artist)) {
                System.out.println("The song is found in the playlist!\n");
                System.out.println("Title: " + temp.song.title + ", Artist: " + temp.song.artist + ", Duration: " + temp.song.duration + " sec.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("The song was not found!");

    }

    public void reversePlaylist() {
        if (head == null) {
            System.out.println("The playlist is empty!");
            return;
        }
        Node current = head;
        Node previous = null;
        Node nextNode = null;

        while (current != null) {
            nextNode = current.next;
            current.next = previous;
            current.prev = nextNode;
            previous = current;
            current = nextNode;

        }
        head = previous;
        System.out.println("The playlist has been reversed!");

    }

    public void moveSong(String title, int newPosition) {
        // Check if the playlist is empty or has only one song
        if (head == null || head.next == null) {
            System.out.println("The playlist is empty or has only one song!");
            return;
        }

        // Check if the new position is valid
        if (newPosition < 1 || newPosition > totalSongs) {
            System.out.println("The position value is wrong! Should be between 1-" + totalSongs);
            return;
        }

        // Traverse the playlist 
        Node current = head;
        int index = 1;
        while (current != null && !current.song.title.equals(title)) {
            current = current.next;
            index++;
        }

        // If the song wasn't found
        if (current == null) {
            System.out.println("The song was not found!");
            return;
        }

        // Check if the song is already at the new position
        if (index == newPosition) {
            System.out.println("The song is already at that position!");
            return;
        }

        // Remove the song from the current position and insert it at the new position
        removeNode(current);
        insertAtPosition(current, newPosition);
    }

    private void removeNode(Node node) {
        // Update the previous node to point to the next node, if the previous node exists
        if (node.prev != null) {
            node.prev.next = node.next;
        }

        // Update the next node to point to the previous node, if the next node exists
        if (node.next != null) {
            node.next.prev = node.prev;
        }

        // If the node is the head, move the head pointer to the next node
        if (node == head) {
            head = node.next;
        }

        // If the node is the tail, move the tail pointer to the previous node
        if (node == tail) {
            tail = node.prev;
        }

        // Clear the next and prev pointers of the node to fully disconnect it
        node.next = node.prev = null;
    }

    private void insertAtPosition(Node node, int newPosition) {
        // If the new position is at the start of the list (1st position)
        if (newPosition <= 1) {
            node.next = head; // Set the node’s next to current head
            if (head != null) {
                head.prev = node; // Update head's previous to point to the new node
            }
            head = node; // Set the head to the new node

            // If the list was empty, set the tail to the new node as well
            if (tail == null) {
                tail = node;
            }
            return;
        }

        // Otherwise, find the node at the current position
        Node currentNode = head;
        int currentIndex = 1;
        while (currentNode.next != null && currentIndex < newPosition - 1) {
            currentNode = currentNode.next; // Traverse the list to the current position
            currentIndex++; // Increment the current index
        }

        // Insert the node at the new position
        node.next = currentNode.next; // Set the node's next to the current node's next
        if (currentNode.next != null) {
            currentNode.next.prev = node; // Update the current node's next to point to the new node
        }
        currentNode.next = node; // Set the current node's next to the new node
        node.prev = currentNode; // Set the new node's previous to the current node

        // If the new node is at the end of the list, update the tail
        if (node.next == null) {
            tail = node;
        }
    }
    
    private Scanner scanner = new Scanner(System.in); 
    
    public void playNext() {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("The playlist is empty!"); 
            return;
        } else if (currentSong == null) {
            // If currentSong is null, start playing the first song
            currentSong = head;
            System.out.println(currentSong.song.title + " is playing...");
            return;
        }

        // If there is no next song (reached the end of the playlist)
        if (currentSong.next == null) {
            System.out.println("Reached the end of the playlist!"); 
            System.out.println("Do you want to play first song ? (Y/N)"); 
            char choice = scanner.next().charAt(0);
            if (Character.toLowerCase(choice) == 'y') {
                currentSong = head; // Set to first song in the playlist
                System.out.println(currentSong.song.title + " is playing...");
            }
            return;
        }

        // Move to the next song in the playlist and play it
        currentSong = currentSong.next;
        System.out.println(currentSong.song.title + " is playing...");
    }

    public void playPrevious() {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("The playlist is empty!"); 
            return;
        } else if (currentSong == null) {
            // If currentSong is null, start playing the first song
            currentSong = head;
            System.out.println(currentSong.song.title + " is playing...");
            return;
        }

        // If we are at the beginning of the playlist, ask if user wants to loop to the last song
        if (currentSong.prev == null) {
            System.out.println("Already at the beginning of the playlist!"); 
            System.out.println("Do you want to play last song ? (Y/N)"); 
            char choice = scanner.next().charAt(0);
            if (Character.toLowerCase(choice) == 'y' && tail != null) {
                currentSong = tail; // Set to the last song in the playlist
                System.out.println(currentSong.song.title + " is playing...");
            }
            return;
        }

        // Move to the previous song and play it
        currentSong = currentSong.prev;
        System.out.println(currentSong.song.title + " is playing...");
    }

}
