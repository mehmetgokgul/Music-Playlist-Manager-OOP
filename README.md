# Music-Playlist-Manager-OOP

This project manages a music playlist using linked lists. Both singly and doubly linked lists are supported. The following methods are used to add, remove, and manage songs in the playlist.In this project, I developed a playlist manager application utilizing two types of linked lists: Singly Linked List and Doubly Linked List. 
The program enables users to add, remove, search, display, and reverse songs. It also allows users to navigate through the playlist and move songs to specific positions.
To streamline playlist management, I created a Node class for each linked list, ensuring the code is more modular and user-friendly. 

## Methods

- addSong: Adds a new song to the playlist. If the list is empty, the new node becomes the head node.
- removeSong: Removes the specified song from the playlist. If the song is not found, no action is taken.
- searchSong: Checks if a specific song is in the playlist. If found, its position is returned.
- displayPlaylist: Prints all songs in the playlist in order.
- reversePlaylist: Reverses the playlist. In a doubly linked list, this is done by changing the node connections.
- playNext: Plays the song after the current one. If called at the end of the list, it loops back to the beginning.
- playPrevious: Plays the song before the current one. If called at the beginning of the list, it loops to the last song.
- removeNode: Deletes a specific node in the linked list. Also handles cases where the song is the head node.
- insertAtPosition: Inserts a new song at the specified position. If the position is invalid, no action is taken.
